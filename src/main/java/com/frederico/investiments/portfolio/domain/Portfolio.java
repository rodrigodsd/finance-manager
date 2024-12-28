package com.frederico.investiments.portfolio.domain;

import org.springframework.data.annotation.Id;

public record Portfolio(@Id Long id, Long investorId, String name, String description) {
}
