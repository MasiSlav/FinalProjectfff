package com.newsPub.newsPub.service;

import com.newsPub.newsPub.entity.NewsEnt;
import com.newsPub.newsPub.repository.NewsRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    public void save (NewsEnt newsEnt){
    newsRepository.save(newsEnt);
    }
}
