package contracttesting.search

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "productRepository", url = "http://localhost:8080")
interface ProductRepository {

    @GetMapping("/products")
    fun allProducts(): List<Product>

}