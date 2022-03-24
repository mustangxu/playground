package com.jayxu.playground.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayxu.playground.spring.model.BuiltTransactionType;

@SpringBootTest
class BroadcastRecordDAOTest {
    @Autowired
    private BroadcastRecordDAO dao;

    @Test
    void test() {
        this.dao.getByUniqueKey(0, null, null,
            BuiltTransactionType.WITHDRAW.getCode());

        this.dao.getByUniqueKey(0, null, null,
            BuiltTransactionType.CONTRACT.getCode());
    }
}
