let publisher = require('@pact-foundation/pact-node');
let path = require('path');

let options = {
  pactFilesOrDirs: ['/pacts'],
  pactBroker: 'https://magelle.pact.dius.com.au',
  consumerVersion: '1.0.0',
  tags: ['latest'],
  pactBrokerToken: '1gX52gsVt9iwwVuD9ciMaA'
};


publisher.publishPacts(options).then(function () {
  console.log('Pacts successfully published!');
});
