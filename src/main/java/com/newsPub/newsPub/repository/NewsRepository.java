package com.newsPub.newsPub.repository;

import com.newsPub.newsPub.entity.NewsEnt;
import org.hibernate.annotations.OrderBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEnt,Integer> {

    Page<NewsEnt> findAllByOrderByIdDesc(Pageable pageable);
    Page<NewsEnt> findAllByCategoryOrderByIdDesc(String category, Pageable pageable);
}
