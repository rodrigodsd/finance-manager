package com.frederico.investiments.user;

import com.frederico.investiments.user.domain.Investor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvestorRepository extends CrudRepository<Investor, Long> {
    Optional<Investor> findByPersonalId(Integer personalId);
}