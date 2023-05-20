package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.exampl.demo.model.CatalogItem;
import com.exampl.demo.model.Movie;
import com.exampl.demo.model.UserMovieRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder builder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		
		//Get all rated movie IDs
		UserMovieRating ratings = restTemplate.getForObject("http://MOVIE-RATING/rating/"+userId, UserMovieRating.class);
		
		//For each movie Id, call movie info service and get details
		return ratings.getUserMovieRating().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://MOVIE-INFO/movies/"+rating.getMovieId(), Movie.class);
			
//			Movie movie = builder.build()
//				.get()
//				.uri("http://localhost:8081/movies/"+rating.getMovieId())
//				.retrieve()
//				.bodyToMono(Movie.class)
//				.block();
			return new CatalogItem(movie.getName(),"Test",rating.getRating());
		
		}).collect(Collectors.toList());
		
		//Put them all together
	
	}

}
