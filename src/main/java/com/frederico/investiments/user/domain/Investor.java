package com.frederico.investiments.user.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public record Investor(@Id Long id, Integer personalId, String name)  implements Serializable {
}
