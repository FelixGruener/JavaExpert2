package com.example.jsondemoV3;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;


//JSONCOMPONENT um in Formattierung einzugreifen , Umwandlung

@JsonComponent
public class LocalDateJsonComponent {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static class myJsonSerializer extends JsonSerializer<LocalDate>{




        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializer) throws IOException {
            System.out.println("serialize local date "); //bekommen Datum schreiben das JsonGenerator raus

            String datTimeAsString = FORMATTER.format(value);
            gen.writeString(datTimeAsString);

        }
    }

    public static class myJsonDeserializer extends JsonDeserializer<LocalDate>{


        @Override
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

            TextNode textNode = (TextNode) jsonParser.getCodec().readTree(jsonParser);
            //System.out.println(jsonParser);

            //über FORMATTER in LocalDate
            return LocalDate.parse(textNode.asText(), FORMATTER);
        }
    }

}
