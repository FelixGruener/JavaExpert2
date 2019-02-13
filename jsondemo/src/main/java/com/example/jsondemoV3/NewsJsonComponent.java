package com.example.jsondemoV3;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


//JSONCOMPONENT um in Formattierung einzugreifen , Umwandlung

@JsonComponent
public class NewsJsonComponent {

    private static final boolean USE_AUTHORS_IDS = false;


    public static class myJsonSerializer extends JsonSerializer<News>{


        @Override
        public void serialize(News news, JsonGenerator gen, SerializerProvider serializer) throws IOException {

            gen.writeStartObject();

            JsonComponentHelpers helpers = new JsonComponentHelpers(gen);
            helpers.writeField("id", news.getId());
            helpers.writeField("title", news.getTitle());
            helpers.writeField("text",news.getText());
            if (USE_AUTHORS_IDS){

                gen.writeFieldName("authorsids");
                long[] authorsids = news.getAuthorsIds();
                gen.writeArray(authorsids,0,authorsids.length);

            } else {

                helpers.writeField("authors", news.getAuthors());

            }


            gen.writeEndObject();
        }
    }

    public static class myJsonDeserializer extends JsonDeserializer<News>{


        @Override
        public News deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

            ObjectNode nodeAuthor = (ObjectNode) jsonParser.getCodec().readTree(jsonParser);


            JsonComponentHelpers helpers = new JsonComponentHelpers(nodeAuthor, deserializationContext, jsonParser.getCodec());

            News news = new News(
                    helpers.getField("id", valueNode -> valueNode.asLong()),
                    helpers.getField("title", valueNode -> valueNode.asText()),
                    helpers.getField("text", valueNode -> valueNode.asText()),
                    null


            );


            if (USE_AUTHORS_IDS) {

                //required  casue we cant use List of Long.class as type
                JavaType type = new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Long.class);
                List<Long> authorsIds = helpers.deserializeField("authorsids", type);
                //Jackson bietet Hilfsklasse an List von primitive gibts nicht
                if (authorsIds != null){
                news.setAuthorsIds(authorsIds.stream().mapToLong(i -> i).toArray());}

            } else {

                JavaType type = new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Author.class);
                List<Author> authors = helpers.deserializeField("authors", type);
                //Jackson bietet Hilfsklasse an List von primitive gibts nicht
                news.setAuthors(authors);
            }


            return news;

        }
        }

}
