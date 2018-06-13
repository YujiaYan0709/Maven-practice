package com.donno.nj.service.impl;

import com.donno.nj.dao.BottomDao;
import com.donno.nj.domain.Bottom;
import com.donno.nj.service.BottomService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BottomServiceImpl implements BottomService {

    @Autowired
    private BottomDao bottomDao;

    @Override
    public Optional<Bottom> findBottomByName(String name) {
        return Optional.fromNullable(bottomDao.findByName(name));
    }

    @Override
    public List<Bottom> retrieveBottoms(Map params) {
        return bottomDao.getList(params);
    }

    @Override
    public Integer count(Map params) {
        return bottomDao.count(params);
    }

    @Override
    public Bottom createBottom(Bottom bottom) {
        bottomDao.insert(bottom);
        return bottom;
    }

    @Override
    public Optional<Bottom> findById(Long id) {
        return Optional.fromNullable(bottomDao.findById(id));
    }

    @Override
    public void updateBottom(Bottom bottom, Bottom newBottom) {
        bottom.setName(newBottom.getName());
        bottomDao.update(bottom);
    }

    @Override
    public void deleteBottom(Bottom bottom) {
        bottomDao.delete(bottom.getId());
    }

}
