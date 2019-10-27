package contracttesting.catalog

import org.springframework.stereotype.Repository

@Repository
class ProductRepository {
    private val products: MutableList<Product> = mutableListOf(
            Product(1, "Codeur en Seine Ticket", 0),
            Product(2, "Shoes", 100),
            Product(3, "T-shirt", 50),
            Product(4, "Jeans", 110)
    )

    fun all(): List<Product> = products

    fun one(id: Int): Product? = products.find { it.id == id }
}