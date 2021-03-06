package com.example.restdemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {

    List<News> findAllByOrderByTitleAsc();

}
