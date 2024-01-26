package com.onebill.user.service.impl;

import com.onebill.user.service.exceptions.ResourceNotFoundException;
import com.onebill.user.service.model.Hotel;
import com.onebill.user.service.model.Rating;
import com.onebill.user.service.model.User;
import com.onebill.user.service.repositories.UserRepositories;
import com.onebill.user.service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepositories userRepository;
    private final RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING service call: Using REST TEMPLATE
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id : " +userId+ " does not exists "));
        //fetch rating of the above user from RATING service
//        http://localhost:8083/api/ratings/users/56b4bea9-beed-4761-b5dd-1d4d8ab8ab52
        Rating[] ratingsOfUser = restTemplate.getForObject("http://rating-service/api/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{} ", (Object[]) ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {

            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://hotel-service/api/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            logger.info("response status code {} ", forEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
