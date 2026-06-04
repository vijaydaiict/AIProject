package com.nwayset.service.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import java.time.Clock

@Configuration
class ServiceConfiguration {

    @Bean
    fun clock(): Clock = Clock.systemUTC()

    @Bean
    fun cacheProperties(): CacheProperties = CacheProperties()
}

data class CacheProperties(
    val maxSize: Int = 10000,
    val ttlSeconds: Long = 3600,
    val evictionPolicy: String = "LRU"
)
