package com.example.streamdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SpringBootApplication
public class StreamdemoApplication {

    private static final Pattern PATTERN_NUMBER = Pattern.compile("^\\d+$");//Beginn Digit klasse quantifzierer + =1-n
    private static final Pattern PATTERN_TIME_INTERVALL = Pattern.compile("^\\d{1,9}:\\d{2}.\\d{3} --> \\d{1,9}:\\d{2}.\\d{3}$");

    public static void main(String[] args) throws Exception {
        SpringApplication.run(StreamdemoApplication.class, args);

        //Path / Files + Paths
        //Hilfsklassen mit s also Mehrzahl
        URI uriDirectory =ClassLoader.getSystemClassLoader().getResource("vtts").toURI();
        Files.list(Paths.get(uriDirectory))//Stream<Path> each representing a file path
        .flatMap(filePath->getFile(filePath)) //Stream<String>>
        .forEach(System.out::println);
                                           //Methodereferenz
        //getFile(filePath).forEach(System.out::println);


        }


    //classic Lambda pattern - catch checked exception , throw unchecked  instead
    private static Stream<String> getFile(Path filePath) {
        try {
            return Stream.concat(
                    getTitleOfFile(filePath),
                    getTextsOfFile(filePath)
            );
        } catch(Exception e) {
            throw new RuntimeException(e);

        }
    }

    private static Stream<String> getTitleOfFile(Path filePath) throws Exception {

        return Stream.of(

                "--------------------",
                filePath.getFileName().toString(),
                "--------------------"

        );

    }

        private static Stream<String> getTextsOfFile(Path filePath) throws Exception {

        return Files.lines(filePath)
                        .skip(1) //Stream<String>
                        .filter(line->!(isEmpty(line) || isNumber(line) || isTimeIntervall(line)));

        }

        private static boolean isEmpty(String value){
            return value.trim().isEmpty();
        }

        private static boolean isNumber(String value) {

            return PATTERN_NUMBER.matcher(value).matches();
        }

        private static boolean isTimeIntervall(String value){
            return PATTERN_TIME_INTERVALL.matcher(value).matches();
        }




    }





