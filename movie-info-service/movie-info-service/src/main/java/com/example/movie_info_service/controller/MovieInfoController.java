package com.example.movie_info_service.controller;

import com.example.movie_info_service.model.MovieInfo;
import com.example.movie_info_service.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/movie-info")
public class MovieInfoController {

    @Autowired
    MovieInfoService movieInfoService;

    List<MovieInfo> movieInfoList = Arrays.asList(
            new MovieInfo("mov1_dummy","Ghajini","Master piece"),
            new MovieInfo("mov2_dummy","3 idiots","All time best"),
            new MovieInfo("mov3_dummy","Talash","Underrated"),
            new MovieInfo("mov4_dummy","PK","Super")
    );

    @GetMapping("/{movieId}")
    MovieInfo getMovieInfo(@PathVariable("movieId") String movieId) {
        return movieInfoList.stream()
                .filter(movieInfo -> movieInfo.getMovieId().equals(movieId))
                .findFirst().get();
    }
}
