package com.ensias.shopping.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ensias.shopping.entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> findByCsid(int csid, Pageable pageable);

    List<Product> findByCsidIn(List<Integer> csids,Pageable pageable);


    List<Product> findByTitleIsLike(String keyword, Pageable pageable);

    List<Product> findByPdateAfter(Date date, Pageable pageable);


    List<Product> findByIsHot(int isHot,Pageable pageable);


    @Query(value = "SELECT * FROM (SELECT  * FROM product ORDER BY pdate DESC limit 0,24) a /*#pageable*/",nativeQuery = true)
    List<Product> findNew(Pageable pageable);
}
