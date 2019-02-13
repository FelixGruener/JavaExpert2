package com.example.restdemo;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

//NOT REQUIRED ANYMORE

@SuppressWarnings("ALL")
@Service
public class NewsService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long save(News news) {
        em.persist(news);
        return news.getId();
    }


    public List<News> findAll(){


        return em.createNamedQuery("News.findAll", News.class).getResultList();

    }



}
