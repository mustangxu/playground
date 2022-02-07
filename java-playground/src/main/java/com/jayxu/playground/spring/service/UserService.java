/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.service;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jayxu.playground.spring.dao.UserRepository;
import com.jayxu.playground.spring.model.User;

/**
 * @author xujiajing
 */
@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory
            .getLogger(UserService.class);
    @Autowired
    private UserRepository dao;

    public User getOrAddUser(long id) {
        var ts = "" + System.currentTimeMillis();

        return this.dao.findById(id).orElseGet(() -> {
            var user = new User(id, "jayxu" + ts,
                Md5Crypt.md5Crypt(ts.getBytes()), 18);

            user = this.dao.save(user);
            UserService.log.info("User {} added", id);

            return user;
        });
    }

    public long getCount() {
        return this.dao.count();
    }

    public Page<User> getUsersPage(int page, int size, String orderby) {
        var p = PageRequest.of(page, size);
        if (orderby != null) {
            p = p.withSort(Sort.Direction.ASC, orderby);
        }

        return this.dao.findAll(p);
    }
}
