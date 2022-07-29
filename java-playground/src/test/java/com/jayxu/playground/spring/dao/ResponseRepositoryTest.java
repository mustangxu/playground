/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.spring.dao;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.google.gson.Gson;
import com.jayxu.playground.spring.model.Response;

/**
 * @author xujiajing
 */
@SpringBootTest
@ActiveProfiles("test")
class ResponseRepositoryTest {
    @Autowired
    private ResponseRepository dao;

    @Test
    void test() throws Exception {
        this.dao.deleteAll();

        try (var res = this.getClass().getClassLoader()
            .getResourceAsStream("response.json");) {

            var map = new Gson().fromJson(new InputStreamReader(res),
                Response.class);
            var list = map.getData().stream().map(d -> {
                System.out.println(d.getLabels());

                return d.getValues().stream().map(obj -> {
                    var resp = new Response();
                    resp.setAccount(d.getLabels().get("cost_account"));
                    resp.setResource(d.getLabels().get("cost_resource_type"));
                    resp.setDate(new Date(obj[0].longValue() * 1000));
                    resp.setAmount(new BigDecimal(obj[1]));

                    return resp;
                }).toList();
            }).flatMap(List::stream).toList();

            this.dao.saveAll(list);
        }
    }
}
