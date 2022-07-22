/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.spring.model;

import java.sql.Types;

import org.hibernate.usertype.UserTypeSupport;

public class AgeType extends UserTypeSupport<Long> {
    public AgeType() {
        super(Long.class, Types.INTEGER);
    }
}
