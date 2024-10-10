package com.example.rating_data_service.controller;

import com.example.rating_data_service.model.Rating;
import com.example.rating_data_service.model.UserRating;
import com.example.rating_data_service.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-rating")
public class UserRatingController {

    @Autowired
    UserRatingService userRatingService;

    List<UserRating> userRatings = Arrays.asList(
            new UserRating("user_1",
                    new ArrayList<>(Arrays.asList(
                            new Rating("mov1_dummy",4),
                            new Rating("mov2_dummy",5))
            )),
            new UserRating("user_2",
                    new ArrayList<>(Arrays.asList(
                            new Rating("mov3_dummy",2),
                            new Rating("mov4_dummy",3))
                    ))
    );


    @GetMapping("/{userId}")
    UserRating getMovieRatingByUserId(@PathVariable String userId) {
        return userRatings.stream()
                .filter(userRating -> userId.equals(userRating.getUserId()))
                .toList().get(0);
    }

}
