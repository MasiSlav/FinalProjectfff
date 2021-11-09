package com.newsPub.newsPub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import com.newsPub.newsPub.repository.NewsRepository;
import com.newsPub.newsPub.entity.NewsEnt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContentController {

    @Autowired
    private NewsRepository newsRepository;
/*
    @GetMapping("/templates/news.html")
    public String newsMain(Model model) {
        Iterable<NewsEnt> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "news";
    }
*/
    @GetMapping("/templates/news-finances.html")
    public String newsFinance(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page ){
        int size = 5;
        model.addAttribute("newsPage", newsRepository.findAllByCategoryOrderByIdDesc("Финансы",PageRequest.of(page-1, size)));
        return "news-finances";
    }

    @GetMapping("/templates/news-sport.html")
    public String newsSport(Model model,
                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page ){
        int size = 5;
        model.addAttribute("newsPage", newsRepository.findAllByCategoryOrderByIdDesc("Спорт",PageRequest.of(page-1, size)));
        return "news-sport";
    }

    @GetMapping("/templates/news-politics.html")
    public String newsPolitics(Model model,
                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer page ){
        int size = 5;
        model.addAttribute("newsPage", newsRepository.findAllByCategoryOrderByIdDesc("Политика",PageRequest.of(page-1, size)));
        return "news-politics";
    }

    @GetMapping(value = {"/templates/article/{id}"})
    public String newsArticle(@PathVariable(value = "id") Integer id, Model model) {
        Optional<NewsEnt> news = newsRepository.findById(id);
        ArrayList<NewsEnt> result = new ArrayList<>();
        news.ifPresent(result::add);
        model.addAttribute("art", result);
        return "article";
    }

    @GetMapping("/templates/news.html")
    public String paginated(Model model,
                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page ){
        int size = 5;
        model.addAttribute("newsPage", newsRepository.findAllByOrderByIdDesc(PageRequest.of(page-1, size)));
        return "news";
    }

}
