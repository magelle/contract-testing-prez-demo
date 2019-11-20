package contracttesting.search

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class SearchApplication

/**
 * Consumer
 * Production Code
 */
fun main(args: Array<String>) {
	runApplication<SearchApplication>(*args)
}
