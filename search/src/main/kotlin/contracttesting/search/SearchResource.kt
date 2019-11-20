package contracttesting.search

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Consumer
 * Production Code
 */
@RestController
class SearchResource(private val productRepository: ProductRepository) {

    @GetMapping("/search")
    fun search(@RequestParam name: String) =
        productRepository.allProducts()
                .filter { it.name.contains(name) }

}