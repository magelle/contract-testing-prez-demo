package contracttesting.search

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
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

    private val client: ProductRepository = ProductRepository()

    @Pact(provider = "Catalog_Provider", consumer = "Search_Consumer")
    fun getAProduct(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .uponReceiving("Get a product")
                .method("GET")
                .path("/products/1")
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .headers(mapOf("Content-Type" to "application/json"))
                .body(PactDslJsonBody()
                        .numberType("id", 1)
                        .stringType("name", "Shoes")
                        .asBody())
                .toPact()
    }

    @Test
    fun shouldRetrieveAProduct() {
        val products = client.getProduct(1)
        assertThat(products).isEqualTo(Product(1, "Shoes"))
    }

}