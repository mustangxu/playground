package com.jayxu.playground.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "broadcast_record", indexes = {
    @Index(columnList = "asset_name, task_id, transaction_hash") })
public class BroadcastRecord {

    @Id
    @Column(nullable = false)
    private Long id;

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
}
