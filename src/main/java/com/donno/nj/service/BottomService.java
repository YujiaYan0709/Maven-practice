package com.donno.nj.service;

import com.donno.nj.domain.Bottom;
import com.google.common.base.Optional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface BottomService {

    Optional<Bottom> findBottomByName(String name);

    List<Bottom> retrieveBottoms(Map params);

    Integer count(Map params);

    Bottom createBottom(Bottom bottom);

    Optional<Bottom> findById(Long id);

    void updateBottom(Bottom bottom, Bottom newBottom);

    void deleteBottom(Bottom bottom);
}
