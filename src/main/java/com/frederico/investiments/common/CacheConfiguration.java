package com.frederico.investiments.common;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfiguration {

    @Bean
    ConcurrentMapCacheManager concurrentMapCacheManager() {
        var cmch = new ConcurrentMapCacheManager();
        cmch.setStoreByValue(true);
        // forces Spring to serialize the objects and
        // thus recreate them from deserialization
        return cmch;
    }
}
