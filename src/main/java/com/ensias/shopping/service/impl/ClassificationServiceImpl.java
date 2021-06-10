package com.ensias.shopping.service.impl;

import com.ensias.shopping.dao.ClassificationDao;
import com.ensias.shopping.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ensias.shopping.entity.Classification;

import java.util.List;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationDao classificationDao;

    @Override
    public Classification findById(int id) {
        return classificationDao.getOne(id);
    }

    @Override
    public List<Classification> findAll(int type) {
        return classificationDao.findByType(type);
    }


    @Override
    public Page<Classification> findAll(int type, Pageable pageable) {
        return classificationDao.findByType(type, pageable);
    }

    @Override
    public List<Classification> findAllExample(Example<Classification> example) {
        return classificationDao.findAll(example);
    }

    @Override
    public void update(Classification classification) {
        classificationDao.save(classification);
    }

    @Override
    public int create(Classification classification) {
        Classification classification1 = classificationDao.save(classification);
        return classification.getId();
    }

    @Override
    public void delById(int id) {
        classificationDao.delete(id);
    }


    @Override
    public List<Classification> findByParentId(int cid) {
        return classificationDao.findByParentId(cid);
    }
}
