package com.onebill.rating.service.services;

import com.onebill.rating.service.entities.Rating;
import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);

    // get all ratings
    List<Rating> getRatings();


    //get all by userId
    List<Rating> getRatingsByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingsByHotelId(String hotelId);

//    @PutMapping("/api/ratings/{ratingId}")
//    Rating updateRating(@PathVariable String ratingId, Rating rating);

//    @DeleteMapping("/api/ratings/{ratingId}")
//    void deleteRating(@PathVariable String ratingId);
}
