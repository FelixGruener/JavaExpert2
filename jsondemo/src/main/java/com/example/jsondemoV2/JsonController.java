package com.example.jsondemoV2;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class JsonController {
    //Jackson mit Objectmapper
    //GSON
    //JSON-B  in Java EE ähnlich Jakcson

    //srping-boot-starter-web muss in pom.xml vorhanden sein
    @Autowired
    private ObjectMapper om;




    public String toJson(Object object) throws Exception {

        return om.writeValueAsString(object);

    }

    public <T> T fromJson(String json, Class<T> valueType) throws Exception {

        return om.readValue(json, valueType);
    }


}
