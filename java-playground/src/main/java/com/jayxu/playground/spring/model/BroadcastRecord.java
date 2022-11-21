package com.jayxu.playground.spring.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "broadcast_record", indexes = {
    @Index(columnList = "asset_name, task_id, transaction_hash") })
public class BroadcastRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(name = "built_transaction_id")
    private long builtTransactionId;

    @Column(name = "asset_name", length = 255)
    private String assetName;

    @Column(name = "platform_name", length = 255)
    private String platformName;

    @Column(nullable = false)
    private int state;

    @Column(name = "task_id", nullable = false)
    private long taskId;

    @Column(name = "transaction_hash", length = 255)
    private String transactionHash;

    @Column(name = "built_transaction_type")
    private short builtTransactionType;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;
}
