package com.donno.nj.service.impl;

import com.donno.nj.dao.UserDao;
import com.donno.nj.domain.User;
import com.donno.nj.service.UserService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<User> findUserByName(String name) {
        return Optional.fromNullable(userDao.findByName(name));
    }

    @Override
    public List<User> retrieveUsers(Map params) {
        return userDao.getList(params);
    }

    @Override
    public Integer count(Map params) {
        return userDao.count(params);
    }

    @Override
    public User createUser(User user) {
        userDao.insert(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.fromNullable(userDao.findById(id));
    }

    @Override
    public void updateUser(User user, User newUser) {
        user.setName(newUser.getName());
        userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user.getId());
    }

}
