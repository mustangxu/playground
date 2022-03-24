package com.jayxu.playground.spring.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(
        name = "broadcast_record",
        indexes = {
            @Index(columnList = "asset_name, task_id, transaction_hash")
        })
public class BroadcastRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    private long id;

    @Column(name = "built_transaction_id", columnDefinition = "INT UNSIGNED")
    private long builtTransactionId;

    @Column(name = "asset_name", columnDefinition = "VARCHAR(255)")
    private String assetName;

    @Column(name = "platform_name", columnDefinition = "VARCHAR(255)")
    private String platformName;

    @Column(columnDefinition = "INT UNSIGNED DEFAULT 0")
    private int state;

    @Column(name = "task_id", columnDefinition = "BIGINT UNSIGNED DEFAULT 0")
    private long taskId;

    @Column(name = "transaction_hash", columnDefinition = "VARCHAR(255)")
    private String transactionHash;

    @Column(name = "built_transaction_type",
            columnDefinition = "SMALLINT(5) UNSIGNED")
    private short builtTransactionType;

    @Column(name = "created",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false, updatable = false)
    private Timestamp created;

    @Column(name = "updated",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
            insertable = false, updatable = false)
    private Timestamp updated;
}
