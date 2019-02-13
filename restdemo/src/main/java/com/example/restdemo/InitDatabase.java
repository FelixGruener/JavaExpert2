package com.example.restdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitDatabase implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private NewsRepository newsRepository;

    //@Autowired
    //private NewsService newsService;


    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        System.out.println("Bin GESTARTET!!!!");

        if (newsRepository.count() == 0) {
            newsRepository.saveAll(

                    Arrays.asList(
                    new News("Hello World!", "Herzlich Willkommen am Planeten Erde"),
                    new News("Newsportal online", "Unser neues Newsportal")
            )
            );
        }

    }

}      // newsService.save(new News("Newsportal online", "Unser neues Newsportal"));

