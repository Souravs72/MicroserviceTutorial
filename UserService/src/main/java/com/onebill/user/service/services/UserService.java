package com.onebill.user.service.services;

import com.onebill.user.service.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    void deleteUser(String userId);

}
