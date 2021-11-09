package com.newsPub.newsPub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.newsPub.newsPub.repository.NewsRepository;
import com.newsPub.newsPub.entity.NewsEnt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ContentController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/templates/news.html")
    public String newsmain(Model model) {
        Iterable<NewsEnt> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "news";
    }

}
