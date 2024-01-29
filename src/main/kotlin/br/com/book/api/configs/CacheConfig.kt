package br.com.book.api.configs

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Slf4j
@Configuration
class CacheConfig {

    companion object {
        val logger = LoggerFactory.getLogger(CacheConfig::class.java)
    }

    @Value("\${redis.host}")
    private lateinit var host : String

    @Value("\${redis.port}")
    private lateinit var port : String

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(host, port.toInt())
        val jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build()
        val factory = JedisConnectionFactory(config, jedisClientConfiguration)
        factory.afterPropertiesSet()
        return factory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        var redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(jedisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        redisTemplate.afterPropertiesSet()
        logger.info("Created redisTemplate")
        return redisTemplate
    }
}