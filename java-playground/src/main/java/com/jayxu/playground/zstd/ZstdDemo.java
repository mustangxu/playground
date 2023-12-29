/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.zstd;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StopWatch;

import com.github.luben.zstd.Zstd;

import lombok.SneakyThrows;

/**
 * @author jayxu
 */
public class ZstdDemo {
    private static final ByteBuffer buf = ByteBuffer.allocateDirect(64 * 1024); // 64K buffer

    @SneakyThrows
    public void compress(int level) {
        var outFile = File.createTempFile("compressed", ".zstd").toPath();
        System.out.println(outFile);

        try (var in = FileChannel.open(
            Paths.get(ZstdDemo.class.getClassLoader()
                .getResource("large-file.json").toURI()),
            StandardOpenOption.READ);
                var out = FileChannel.open(outFile,
                    StandardOpenOption.WRITE);) {
            while (in.read(buf) != -1) {
                var compressed = Zstd.compress(buf, level);
                out.write(compressed);
                buf.clear();
            }

            System.out.println("levle: " + level + ", original: " + in.size()
                + ", compressed: " + out.size() + ", ratio: "
                + out.size() * 100. / in.size() + "%");
        }
    }

    public static void main(String[] args) {
        var watch = new StopWatch("zstd");
        var demo = new ZstdDemo();
        demo.compress(0); // warm up

        for (var i = 1; i <= 22; i++) {
            watch.start("level " + i);
            demo.compress(i);
            watch.stop();
        }

        System.out.println(watch.prettyPrint(TimeUnit.MILLISECONDS));
    }
}
