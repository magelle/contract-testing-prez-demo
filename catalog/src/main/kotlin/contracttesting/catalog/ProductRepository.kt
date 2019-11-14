package contracttesting.catalog

import org.springframework.stereotype.Repository

@Repository
class ProductRepository {
    private val products: MutableList<Product> = mutableListOf(
            Product(1, "Codeur en Seine Ticket", 0)
    )

    fun all(): List<Product> = products

    fun one(id: Int): Product? = products.find { it.id == id }
}