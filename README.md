# SpringBootServiceDiscovery
Spring boot service discovery implementation ->
In this implementation, we have created 3 Microservices as follows and all has been registered as a client in Eureka Discovery server.

Microservices - 
1) moviecatalog
2) movieinfo
3) movieratingdata

Maven dependancy used for registering application as client in eureka disocvery server

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
    
Created a Eureka server with Spring boot and below maven dependancies.
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>

To see the eureka server UI browse http://localhost:8761/ in your system's browser

Instead of hardcoding API hostname and IP port in Spring boot code to call other microservices hosted in your system 
you can use application name used to register a client in eureka discovery server as shown below

E.g. - MOVIE-INFO is an application name used for registering movieinfo microservice.
API URL - http://MOVIE-INFO/movies/1


