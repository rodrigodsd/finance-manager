package com.frederico.investiments.portfolio.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("portfolio_position")
public record Position(@Id Long id, Long portfolioId, String code, String codeIsin, BigDecimal quantity, BigDecimal priceAvarage) {
}
