/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.XSlf4j;

/**
 * @author xujiajing
 */
@SpringBootTest
@XSlf4j
@ActiveProfiles("test")
class UserServiceTest {
    @Autowired
    private UserService service;

    /**
     * Test method for
     * {@link com.jayxu.playground.spring.service.UserService#getUsersPage(int, int, org.springframework.data.domain.Sort.Direction, java.lang.String)}.
     */
    @Test
    void testGetUsersPage() {
        var res = this.service.getUsersPage(0, 10, Direction.DESC,
            "createTime");
        log.info("{}", res.getContent().get(0));
    }

}
