package com.frederico.investiments.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table
public record Resource(@Id Long id, String code, String description) implements Serializable {
}

