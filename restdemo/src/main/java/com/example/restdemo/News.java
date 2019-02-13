package com.example.restdemo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;

import javax.persistence.*;

//JPA
//query="SELECT n from News n WHERE n.id=:id ORDER BY n.title ASC"
@SuppressWarnings("ALL")
@Entity
@Table(name="t_news")
// NOT REQUIRED ANYMORE
/*@NamedQuery(
        name="News.findAll",
        query="SELECT n FROM News n "
)*/
//Lombok
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="newsid")
    private Long id;
    @Column(length=100,nullable = false)
    @NonNull
    private String title;
    @Column(length=1000,nullable = false)
    @NonNull
    @JsonProperty("txt")
    private String text;

    //lombok java
}
