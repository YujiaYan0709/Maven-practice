package com.donno.nj.service;

import com.donno.nj.domain.User;
import com.google.common.base.Optional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface UserService {

    Optional<User> findUserByName(String name);

    List<User> retrieveUsers(Map params);

    Integer count(Map params);

    User createUser(User user);

    Optional<User> findById(Long id);

    void updateUser(User user, User newUser);

    void deleteUser(User user);
}
