package com.jayxu.playground.spring.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "responses", indexes = { @Index(columnList = "resource"),
    @Index(columnList = "account"), @Index(columnList = "date") })
public class Response {
    @Id
    private Long id;
    @Transient
    private Boolean result;
    @Transient
    private Integer bk_error_code;
    @Transient
    private String bk_error_msg;
    @Transient
    private String permission;
    @Transient
    private List<ResponseData> data;
    @Column(length = 32, nullable = false)
    @NotNull
    private String resource;
    @Column(length = 32, nullable = false)
    @NotNull
    private String account;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;
    @Column(precision = 16, scale = 2, nullable = false)
    @NotNull
    private BigDecimal amount;

    @Data
    public static class ResponseData {
        private Map<String, String> labels;
        private List<Double[]> values;
    }
}
