package com.jayxu.playground.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.time.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqliteTableFiller {
    private static final ExecutorService pool =
            Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());

    static void main(String[] args) throws Exception {
        var watch = new StopWatch();
        watch.start();
        var list = new LinkedList<Future<Integer>>();

        try (var conn = DriverManager.getConnection("jdbc:sqlite:/Users/xujiajing/mysqlite.db")) {
            for (var j = 0; j < SqliteTableFiller.J; j++) {
                var f = pool.submit(() -> SqliteTableFiller.doStatement(conn));

                list.add(f);
                log.info("{} added", f);
            }

            var count = 0;
            for (var f : list) {
                log.info("{} added, {}%", count += f.get(), (double) count * 100 / SqliteTableFiller.TOTAL);
            }
        } finally {
            pool.shutdown();
            watch.stop();

            log.info("DONE in [{}]", watch);
        }
    }

    private static int doStatement(Connection conn) throws SQLException {
        try (var ps = conn.prepareStatement("insert into keystore values (NULL,?,?,?,?)")) {
            for (var i = 0; i < I; i++) {
                ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2, "eth");
                ps.setString(3, UUID.randomUUID().toString() + UUID.randomUUID());
                ps.setString(4, UUID.randomUUID().toString() + UUID.randomUUID());

                ps.addBatch();
            }

            var res = ps.executeBatch();
            //                var errCount = Arrays.stream(res).filter(i -> i != 1)
            //                    .count();

            //                SqliteTableFiller.log.info(
            //                    "count: {}, error count: {}",
            //                    res.length, errCount);

            return res.length;
        }
    }

    private static final int I = 100, J = 1_000, TOTAL = I * J;

}
