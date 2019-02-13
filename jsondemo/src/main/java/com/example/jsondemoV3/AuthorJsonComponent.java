package com.example.jsondemoV3;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import org.springframework.boot.jackson.JsonComponent;
import sun.reflect.generics.tree.Tree;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


//JSONCOMPONENT um in Formattierung einzugreifen , Umwandlung

@JsonComponent
public class AuthorJsonComponent {


    public static class myJsonSerializer extends JsonSerializer<Author>{


        @Override
        public void serialize(Author author, JsonGenerator gen, SerializerProvider serializer) throws IOException {

            gen.writeStartObject();

            JsonComponentHelpers helpers = new JsonComponentHelpers(gen);

            /*if (author.getId() != null) {
                gen.writeNumberField("id", author.getId());
            }

            //..
            gen.writeObjectField("birth-date",author.getBirthDate());*/

            helpers.writeField("id",author.getId());
            helpers.writeField("sex",author.getSex());
            helpers.writeField("first-name",author.getFirstName());
            helpers.writeField("last-name",author.getLastName());
            helpers.writeField("birth-date",author.getBirthDate());
            // Bei nicht gültigem json wird Fehler ausgeworfen

            gen.writeEndObject();
        }
    }

    public static class myJsonDeserializer extends JsonDeserializer<Author>{


        @Override
        public Author deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

                ObjectNode nodeAuthor = (ObjectNode) jsonParser.getCodec().readTree(jsonParser);
                /*System.out.println(treeNode.getClass().getName());
                System.out.println(treeNode);*/


              /*  IntNode nodeId = (IntNode) nodeAuthor.get("id");
                TextNode nodeSex = (TextNode) nodeAuthor.get("sex");


                System.out.println(nodeId.getClass().getName());
                System.out.println(nodeId);*/


            JavaType type = new ObjectMapper().getTypeFactory().constructType(LocalDate.class);

              JsonComponentHelpers helpers = new JsonComponentHelpers(nodeAuthor,deserializationContext,jsonParser.getCodec());



                return new Author(
                        helpers.getField("id", valueNode -> valueNode.asLong()),
                        helpers.getField("first-name",valueNode -> valueNode.asText()),
                        helpers.getField("last-name",valueNode -> valueNode.asText()),
                        helpers.getField("sex", valueNode -> Sex.valueOf(valueNode.asText())),
                        helpers.deserializeField("birth-date",type)
                );
        }
    }

}
