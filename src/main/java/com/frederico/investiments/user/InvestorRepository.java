package com.frederico.investiments.user;

import com.frederico.investiments.user.domain.Investor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvestorRepository extends CrudRepository<Investor, Long> {

    String INVESTOR_CACHE_KEY = "investor";

    @Override
    @CacheEvict(value = INVESTOR_CACHE_KEY, key = "#result.id")
    <S extends Investor> S save(S entity);

    @Override
    @Cacheable(INVESTOR_CACHE_KEY)
    Optional<Investor> findById(Long id);

    @Cacheable(INVESTOR_CACHE_KEY)
    Optional<Investor> findByPersonalId(Integer personalId);

    @Cacheable(INVESTOR_CACHE_KEY)
    Optional<Investor> findByUserlogin(String username);
}