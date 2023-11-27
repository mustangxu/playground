package com.jayxu.playground.spring.dao;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.jayxu.playground.spring.model.BroadcastRecord;
import com.jayxu.playground.spring.model.BuiltTransactionType;

/**
 * @author jayxu
 */
public interface BroadcastRecordDAO
        extends CrudRepository<BroadcastRecord, Long>,
        JpaSpecificationExecutor<BroadcastRecord> {
    default BroadcastRecord getByUniqueKey(long taskId, String assetName,
            String platformName, short builtTransactionType) {
        var spec = Specification.where(withColumn("taskId", taskId))
            .and(withColumn("assetName", assetName))
            .and(withColumn("builtTransactionType", builtTransactionType));

        if (builtTransactionType != BuiltTransactionType.WITHDRAW.getCode()) {
            spec = spec.and(withColumn("platformName", platformName));
        }

        return this.findOne(spec).orElse(null);
    }

    static Specification<BroadcastRecord> withColumn(String col, Object value) {
        return (root, q, b) -> value == null ? b.isNull(root.get(col))
            : b.equal(root.get(col), b.literal(value));
    }

}
