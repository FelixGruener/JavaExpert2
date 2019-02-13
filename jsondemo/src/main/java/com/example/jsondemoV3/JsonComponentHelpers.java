package com.example.jsondemoV3;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.Function;

public class JsonComponentHelpers {

    private JsonGenerator jsonGenerator;

    private ObjectNode objectNode;

    private DeserializationContext deserializationContext;

    private ObjectCodec objectCodec;

    public JsonComponentHelpers(final ObjectNode objectNode, final DeserializationContext deserializationContext, final ObjectCodec objectCodec) {
        this.objectNode = objectNode;
        this.deserializationContext = deserializationContext;
        this.objectCodec = objectCodec;
    }

    public JsonComponentHelpers(final ObjectNode objectNode) {
        this.objectNode = objectNode;
    }


    public JsonComponentHelpers(final JsonGenerator jsonGenerator) {
        this.jsonGenerator = jsonGenerator;
    }

    public void writeField(String fieldName, Object value) throws IOException{
        if (value != null) {
            jsonGenerator.writeObjectField(fieldName, value);
        }
    }

    //weil Generic müssen wir es binden sonst glaubt Compiler es ist Klasse
    public <T> T getField( String fieldName, Function<ValueNode, T> extractor){

        ValueNode valueNode = (ValueNode) objectNode.get(fieldName);
        if (valueNode == null){
            return null;
        }

        //Funktionales Interface , LAMDA wird übergeben valueNode
        return extractor.apply(valueNode);

/*        IntNode nodeId = (IntNode) nodeAuthor.get("id");
        TextNode nodeSex = (TextNode) nodeAuthor.get("sex");


        return new Author(
                nodeId.asLong(),
                null,
                null,
                Sex.valueOf(nodeSex.asText()),
                null
    }*/
    }

    public <T> T deserializeField(String fieldName, JavaType type) throws IOException{

        TreeNode treeNode =  objectNode.get(fieldName);
        if (treeNode == null){
            return null;
        }
        JsonParser jsonParser = treeNode.traverse(objectCodec);
        jsonParser.nextToken();//das ganze wird eingelesen und bereitgestellt (EndofInput Error wenn
        //nicht alles eingelesen
//        LocalDate birthDate = deserializationContext.readValue(jsonParser, LocalDate.class);
        return deserializationContext.readValue(jsonParser,type);
    }
}
