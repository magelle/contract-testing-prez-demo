package contracttesting.search

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.client.RestTemplate

/**
 * Consumer
 * Production Code
 */
@Service
class ProductRepository {

    val catalogServiceUrl = "http://localhost:8080/products"
    private val restTemplate: RestTemplate = RestTemplate()

    @GetMapping("/products")
    fun allProducts(): List<Product> =
            restTemplate.exchange(
                    catalogServiceUrl,
                    HttpMethod.GET,
                    null,
                    ListOfProduct())
                    .body ?: listOf();

    fun getProduct(id: Int): Product? {
        return restTemplate.getForObject("$catalogServiceUrl/$id", Product::class.java);
    }

}

class ListOfProduct : ParameterizedTypeReference<List<Product>>()