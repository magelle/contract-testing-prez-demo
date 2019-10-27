import pact from "@pact-foundation/pact-node";
let project = require('./package.json');

let options = {
  pactFilesOrDirs: ['/pacts'],
  pactBroker: "pactBrokerUrl",
  consumerVersion: project.version,
  tags: ['latest'],
  pactBrokerUsername: "pactBrokerUsername",
  pactBrokerPassword: "pactBrokerPassword"
};


pact.publishPacts(options).then(function () {
  console.log("Pacts successfully published!");
});
