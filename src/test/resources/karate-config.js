function fn() {
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    return {
      demoBaseUrl: 'http://localhost:8080'
    };
}