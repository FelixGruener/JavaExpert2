package com.example.jsondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class JsondemoApplication {

    private static final Author AUTHOR1 = new Author(1L, "Mustermann" , "Markus", Sex.MALE, LocalDate.of(1990,1,1));
    private static final Author AUTHOR2 = new Author(1L, "Musterfrau" , "Martina", Sex.FEMALE, null);

    private static final News NEWS = new News(1L, "HEllo World", "HErzlich wilkommen", Arrays.asList(AUTHOR1,AUTHOR2));
    //Vorsicht asList immutable List !!

    public static void main(String[] args) throws Exception {



        ConfigurableApplicationContext ctx = SpringApplication.run(JsondemoApplication.class, args);

        JsonController jsonController = ctx.getBean(JsonController.class);

        //TODO add examples
        System.out.println(System.lineSeparator()+ "=== AUTHOR1 ====");
        String json = jsonController.toJson(AUTHOR1);
        System.out.println(json);
        Author author = jsonController.fromJson(json,Author.class);
        System.out.println(jsonController.fromJson(json,Author.class)); // prints Author object

        System.out.println(System.lineSeparator()+ "=== AUTHOR2 ====");
        json = jsonController.toJson(AUTHOR2);
        System.out.println(json);
        author = jsonController.fromJson(json,Author.class);
        System.out.println(jsonController.fromJson(json,Author.class)); // prints Author object


        System.out.println(System.lineSeparator()+ "=== NEWS ====");
        json = jsonController.toJson(NEWS);
        System.out.println(json);
        News news = jsonController.fromJson(json,News.class);
        System.out.println(jsonController.fromJson(json,News.class)); // prints Author object


        ctx.close();

    }

}

