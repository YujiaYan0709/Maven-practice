package com.donno.nj.dao;


import com.donno.nj.dao.base.BaseDao;
import com.donno.nj.domain.Bottom;

public interface BottomDao extends BaseDao<Bottom> {

    Bottom findByName(String bottomName);

}
