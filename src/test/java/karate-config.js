function() {
  var port = 8080;
  if (karate.env == 'mock-servlet') {
    var Mock = Java.type('example.ExampleControllerAPIKarateTest');
    karate.configure('httpClientInstance', Mock.getMock());
  } else if (karate.env == 'mock-e2e') {
    port = karate.properties['example.server.port'];
  } else if (karate.env == 'mock-weather') {
    port = karate.properties['weather.server.port'];
  }
  return { baseUrl: 'http://localhost:' + port };
}
