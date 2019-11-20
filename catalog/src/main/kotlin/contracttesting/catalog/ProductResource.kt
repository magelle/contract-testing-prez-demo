package contracttesting.catalog
import org.springframework.web.bind.annotation.*

/**
 * Provider
 * Production Code
 */
@RestController
@RequestMapping("/products")
class ProductResource(private val productRepository: ProductRepository) {

    @GetMapping()
    fun one() = productRepository.all()

    @GetMapping("/{id}")
    fun one(@PathVariable id: Int) = productRepository.one(id)

}
