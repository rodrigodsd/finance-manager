package com.frederico.investiments.portfolio;

import com.frederico.investiments.portfolio.domain.Portfolio;
import org.springframework.data.repository.ListCrudRepository;

public interface PortfolioRepository extends ListCrudRepository<Portfolio, Long> {
}
