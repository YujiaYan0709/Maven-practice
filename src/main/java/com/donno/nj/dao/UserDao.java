package com.donno.nj.dao;


import com.donno.nj.dao.base.BaseDao;
import com.donno.nj.domain.User;

public interface UserDao extends BaseDao<User> {

    User findByName(String userName);

}
