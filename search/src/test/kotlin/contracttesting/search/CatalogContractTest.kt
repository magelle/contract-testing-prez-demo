package contracttesting.search

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.model.RequestResponsePact
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus

@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "Catalog_Provider", port = "8080")
class CatalogContractTest {

    private val HEADERS = mapOf("Content-Type" to "application/json")

    private val client: ProductRepository = ProductRepository()

    @Pact(provider = "Catalog_Provider", consumer = "Search_Consumer")
    fun getAllProducts(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .uponReceiving("Get All products")
                .path("/products")
                .method("GET")
                .willRespondWith()
                .headers(HEADERS)
                .status(HttpStatus.OK.value())
                .body(all_products)
                .toPact()
    }

    @Test
    fun shouldFindAllAccounts() {
        val products = client.allProducts()
        assertThat(products).isEqualTo(listOf(
                Product(1, "Codeur en Seine Ticket"),
                Product(2, "Shoes"),
                Product(3, "T-shirt"),
                Product(4, "Jeans")
        ))
    }

    private val all_products = """[
  {
    "id": 1,
    "name": "Codeur en Seine Ticket"
  },
  {
    "id": 2,
    "name": "Shoes"
  },
  {
    "id": 3,
    "name": "T-shirt"
  },
  {
    "id": 4,
    "name": "Jeans"
  }
]"""
}