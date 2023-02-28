/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.eclipse.persistence.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.jayxu.playground.util.RandomUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Data;

/**
 * @author xujiajing
 */
@Entity
@Table(name = "orders",
        indexes = { @Index(columnList = "userId"),
            @Index(columnList = "createTime"), @Index(columnList = "state") })
@Data
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator(name = "uuid")
    @Column(length = 36)
    private String id;
    @ManyToOne
    private User user;
    @Version
    private Integer version;
    private OrderState state;
    @Column(precision = 18, scale = 2)
    private BigDecimal price;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public static Order buildOrder(User user) {
        var r = new Random();
        var order = new Order();
        order.setUser(user);
        order.setPrice(new BigDecimal(r.nextDouble(1_000_000)));
        order.setState(RandomUtils.randomEnum(OrderState.class));

        return order;
    }
}
