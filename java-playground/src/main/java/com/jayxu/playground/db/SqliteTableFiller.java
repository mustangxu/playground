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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqliteTableFiller {
    private static final Logger log = LoggerFactory
        .getLogger(SqliteTableFiller.class);
    private static final int I = 100, J = 1_000,
            TOTAL = SqliteTableFiller.I * SqliteTableFiller.J;
    private static ExecutorService pool = Executors
        .newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws Exception {
        var watch = new StopWatch();
        watch.start();
        var list = new LinkedList<Future<Integer>>();

        try (var conn = DriverManager
            .getConnection("jdbc:sqlite:/Users/xujiajing/mysqlite.db");) {
            for (var j = 0; j < SqliteTableFiller.J; j++) {
                var f = SqliteTableFiller.pool
                    .submit(() -> SqliteTableFiller.doStatement(conn));

                list.add(f);
                SqliteTableFiller.log.info("{} added", f);
            }

            var count = 0;
            for (var f : list) {
                SqliteTableFiller.log.info("{} added, {}%", count += f.get(),
                    (double) count * 100 / SqliteTableFiller.TOTAL);
            }
        } finally {
            SqliteTableFiller.pool.shutdown();
            watch.stop();

            SqliteTableFiller.log.info("DONE in [{}]", watch);
        }
    }

    private static int doStatement(Connection conn) throws SQLException {
        try (var ps = conn
            .prepareStatement(
                "insert into keystore values (NULL,?,?,?,?)");) {
            for (var i = 0; i < SqliteTableFiller.I; i++) {
                ps.setString(1, UUID.randomUUID().toString());
                ps.setString(2, "eth");
                ps.setString(3,
                    UUID.randomUUID().toString()
                        + UUID.randomUUID().toString());
                ps.setString(4,
                    UUID.randomUUID().toString()
                        + UUID.randomUUID().toString());

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
}
