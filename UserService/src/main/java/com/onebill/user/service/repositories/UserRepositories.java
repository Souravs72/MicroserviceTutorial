package com.onebill.user.service.repositories;

import com.onebill.user.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User, String> {
}
