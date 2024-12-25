package com.frederico.investiments.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Set;

@Table
public record Investor(@Id Long id, Integer personalId, String userlogin, String username, String password,
                       @Column("investor_id") Set<Resource> resources) implements Serializable {
}
