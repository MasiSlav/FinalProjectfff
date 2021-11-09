package com.newsPub.newsPub.repository;

import com.newsPub.newsPub.entity.NewsEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEnt,Integer> {

}
