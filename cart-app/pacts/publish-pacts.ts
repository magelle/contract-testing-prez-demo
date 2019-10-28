let publisher = require('@pact-foundation/pact-node');
let path = require('path');

let options = {
  provider: 'Catalog_Provider',
  pactFilesOrDirs: [path.resolve(__dirname)],
  pactBroker: 'https://magelle.pact.dius.com.au',
  pactBrokerToken: '1gX52gsVt9iwwVuD9ciMaA',
  consumerVersion: '1.0.0'
};


publisher.publishPacts(options)
.then(function () {
  console.log('Pacts successfully published!');
})
.catch(e => {
  console.log('Pact contract publishing failed: ', e)
})
