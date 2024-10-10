package com.example.movie_catalog_service.controller;

import com.example.movie_catalog_service.model.MovieCatalog;
import com.example.movie_catalog_service.model.MovieInfo;
import com.example.movie_catalog_service.model.Rating;
import com.example.movie_catalog_service.model.UserRating;
import com.example.movie_catalog_service.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie-catalog")
public class MovieCatalogController {

    @Autowired
    MovieCatalogService movieCatalogService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<MovieCatalog> getMovieCatalog(@PathVariable("userId") String userId) {

        UserRating userRatings = restTemplate.getForObject("http://localhost:8083/user-rating/"+userId, UserRating.class);

        return userRatings.getRatings().stream().map(rating -> {
            MovieInfo movieInfo = restTemplate.getForObject("http://localhost:8082/movie-info/" + rating.getMovieId(), MovieInfo.class);
            return new MovieCatalog(movieInfo.getMovieName(), movieInfo.getDescription(), rating.getRating());
        }).collect(Collectors.toList());
    }
}
