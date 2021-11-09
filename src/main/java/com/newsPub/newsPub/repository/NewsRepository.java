package com.newsPub.newsPub.repository;

import com.newsPub.newsPub.entity.NewsEnt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEnt,Integer> {

    Iterable <NewsEnt> findAllByCategory(String category);
    Page<NewsEnt> findAllByCategory(String category, Pageable pageable);
}
