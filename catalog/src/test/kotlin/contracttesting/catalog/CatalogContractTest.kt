package contracttesting.catalog

import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth
import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.net.URL

/**
 * Provider
 * Test Code
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [CatalogApplication::class],
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = ["server.port=8080"])

@Provider("Catalog_Provider")
@PactBroker(scheme = "https", host = "magelle.pact.dius.com.au", port="443",
        authentication = PactBrokerAuth(scheme = "Bearer", username = "1gX52gsVt9iwwVuD9ciMaA", password = "")
)
class CatalogContractTest {

    private val providerUrl = "http://localhost:8080"

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @BeforeEach
    @Throws(Exception::class)
    fun before(context: PactVerificationContext) {
        context.target = HttpTestTarget.fromUrl(URL(providerUrl))
    }
}