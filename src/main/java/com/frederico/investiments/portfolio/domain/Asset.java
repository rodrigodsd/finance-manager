package com.frederico.investiments.portfolio.domain;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record Asset(@Id Long id, String code, String codeIsin, String description, String industry, String sector,
                    String segment, String type, BigDecimal price) {
}
