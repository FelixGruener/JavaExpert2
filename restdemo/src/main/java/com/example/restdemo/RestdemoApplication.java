package com.example.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestdemoApplication {

    public static void main(String[] args) {


        ApplicationContext ctx= SpringApplication.run(RestdemoApplication.class, args);
        //NewsService newsService = ctx.getBean(NewsService.class);
        NewsRepository newsRepository = ctx.getBean(NewsRepository.class);
        newsRepository.findAll().forEach(System.out::println);

        newsRepository.findAllByOrderByTitleAsc().forEach(System.out::println);
        //newsService.findAll().forEach(System.out::println);

        //https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-three-custom-queries-with-query-methods/

        /**
         * Finds a person by using the last name as a search criteria.
         * @param lastName
         * @return  A list of persons whose last name is an exact match with the given last name.
         *          If no persons is found, this method returns an empty list.
         */
        //@Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
        //public List<Person> find(@Param("lastName") String lastName);

    }

}

