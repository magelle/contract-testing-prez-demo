package contracttesting.search

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class SearchApplication

fun main(args: Array<String>) {
	runApplication<SearchApplication>(*args)
}
