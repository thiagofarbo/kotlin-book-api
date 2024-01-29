package br.com.book.api.repository

import br.com.book.api.configs.CacheConfig
import br.com.book.api.domain.Customer
import br.com.book.api.util.JsonUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class RedisRepository(private val cacheConfig: CacheConfig){

    val jsonUtil = JsonUtil()
    fun findByCpf(cpf: String): Optional<Customer> {
        val customerJson = cacheConfig.redisTemplate().opsForValue().get(cpf)
        println("Cache value : $customerJson")
        if(customerJson != null){
            return Optional.of(jsonUtil.toObject(customerJson.toString(), Customer::class.java))
        }
        return Optional.empty()
    }
    fun save(customer: Customer) {
        val objectMapper = ObjectMapper()
        val customerJson = objectMapper.writeValueAsString(customer)
        cacheConfig.redisTemplate().opsForValue().set(customer.cpf, customerJson)
    }
}