package com.frederico.investiments.portfolio;

import com.frederico.investiments.portfolio.domain.Position;
import org.springframework.data.repository.ListCrudRepository;


public interface PositionRepository extends ListCrudRepository<Position, Long> {
}
