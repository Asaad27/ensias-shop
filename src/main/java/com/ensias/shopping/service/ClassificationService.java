package com.ensias.shopping.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ensias.shopping.entity.Classification;

import java.util.List;

public interface ClassificationService {

    Classification findById(int id);


    List<Classification> findAll(int type);


    Page<Classification> findAll(int type,Pageable pageable);


    List<Classification> findAllExample(Example<Classification> example);


    void update(Classification Classification);


    int create(Classification Classification);


    void delById(int id);


    List<Classification> findByParentId(int cid);
}
