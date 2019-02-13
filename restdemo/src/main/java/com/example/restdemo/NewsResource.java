package com.example.restdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resources/news")
@CrossOrigin
public class NewsResource {

    @Autowired
    private NewsRepository newsRepository;

    @PostMapping
    public News create(@RequestBody News news){
        return newsRepository.save(news);
    }

    /*@GetMapping("/{id}")
    public Optional<News> retrieve(@PathVariable("id") long id){

        return newsRepository.findById(id);

    }*/

    @GetMapping("/{id}")
    public News retrieve(@PathVariable("id") long id){

        return newsRepository.findById(id).orElseThrow(()->new RuntimeException("news with id =" + id + " not found"));

    }

    @PutMapping ("/{id}")
    public void update(@PathVariable("id") long id,@RequestBody News news){

        news.setId(id);
        newsRepository.save(news);

    }

    @GetMapping
    public List<News> retrieveAll(){

        return newsRepository.findAll();

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){

      newsRepository.deleteById(id);
    }

    //http://localhost:8080/resources/news
    //[{"id":1,"title":"Hello World!","txt":"Herzlich Willkommen am Planeten Erde"},{"id":2,"title":"Newsportal online","txt":"Unser neues Newsportal"}]
    //Spring Data

    //Paging & Sorting Repository Spring Data
}
