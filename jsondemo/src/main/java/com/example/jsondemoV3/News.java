package com.example.jsondemoV3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class News {

    private Long id;
    private String title;
    private String text;
    private List<Author> authors;

    public News(){

    }

    public News(final Long id, final String title, final String text, final List<Author> authors) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(final List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", authors=" + authors +
                '}';
    }

    public long[] getAuthorsIds(){

        //CLEAN CODE AUSNAHMEN VORHER PRüfen
        if(authors == null || authors.isEmpty()){
            return new long[0];
        }
        //stream vs. For
        return authors.stream() //Stream<Author>
                .filter(author->author.getId()!= null)
                //was kommt herein was schicken wir weiter
                .map(author-> author.getId()) //Stream<Long>
                .mapToLong(id -> id.longValue()) //LongStream - primitiv
                .toArray();

/*        long[] authorsIds = new long[authors.size()];
        int i = 0;
        for (Author author: authors){ //stream
            if (author.getId() != null) { //filter
                authorsIds[++i] = author.getId(); //map , was anderes ausser autoren zurückgeben
            }
        }*/

       /* //Resize Array
        return authorsIds;*/
    }


    public void setAuthorsIds(long[] authorsIds) {

        if (authorsIds !=null || authorsIds.length == 0){

            authors = null;

        }

        authors = Arrays.stream(authorsIds) //LongStream
                .mapToObj(authorId -> new Author(authorId)) //Stream<Author>
                .collect(Collectors.toList()); // returns List<Author>

    }
}
