package com.frederico.investiments.user;

import com.frederico.investiments.user.domain.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNullApi;

import java.util.Optional;
import java.util.Set;

public interface ResourceRepository extends ListCrudRepository<Resource, Long> {

    String RESOURCE_CACHE_KEY = "resource";

    @Override
    @CacheEvict(value = RESOURCE_CACHE_KEY, key = "#result.id")
    <S extends Resource> S save( S entity);

    @Cacheable(RESOURCE_CACHE_KEY)
    @Query("SELECT resource.id AS id, resource.code AS code, resource.description AS description FROM resource WHERE resource.investor_id = ?")
    Optional<Set<Resource>> findByInvestorId(Integer personalId);
}