import {TestBed} from '@angular/core/testing';
import {Matchers, PactWeb} from '@pact-foundation/pact-web';
import {Product} from "./typings";
import {CatalogService} from "./catalog.service";
import {HttpClientModule} from "@angular/common/http";

describe('CatalogService', () => {
  let provider;

  beforeAll(function (done) {
    provider = new PactWeb({
      consumer: 'cart',
      provider: 'Catalog_Provider',
      port: 1234,
      host: '127.0.0.1',
    });


    // required for slower CI environments
    setTimeout(done, 2000);

    // Required if run with `singleRun: false`
    provider.removeInteractions();
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ],
      providers: [
        CatalogService
      ],
    });
  });

  afterEach((done) => {
    provider.verify().then(done, e => done.fail(e));
  });


  afterAll(function (done) {
    provider.finalize()
      .then(function () {
        done();
      }, function (err) {
        done.fail(err);
      });
  });


  describe('get Product', () => {
    const productId = 1;
    const expectedProduct: Product = {
      name: 'MyProduct',
      id: productId,
      price: 12
    };

    beforeAll((done) => {
      provider.addInteraction({
        state: ``,
        uponReceiving: 'a request to GET a Product',
        withRequest: {
          method: 'GET',
          path: '/products/' + productId
        },
        willRespondWith: {
          status: 200,
          body: Matchers.somethingLike(expectedProduct),
          headers: {
            'Content-Type': 'application/json'
          }
        }
      }).then(done, error => done.fail(error));
    });

    it('should get Product informations', (done) => {
      const catalogService: CatalogService = TestBed.get(CatalogService);
      catalogService.getProduct(productId).subscribe(response => {
        expect(response).toEqual(expectedProduct);
        done();
      }, error => {
        done.fail(error);
      });
    });

  });

});
