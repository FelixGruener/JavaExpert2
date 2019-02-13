package com.example.jsonpath;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;

@Controller
public class JsonPathController {

    @Autowired
    private ObjectMapper om;

    public void demoObjectMapperUsingPath() throws Exception {
        System.out.println(System.lineSeparator() + "=========demoObjectMapperUsingPath=========");

        URL urlFile =ClassLoader.getSystemClassLoader().getResource("Data.json");

        JsonNode jsonNode = om.readTree(urlFile);

        String location = jsonNode.path("body")
                        .path("incident")
                        .path("tibco_itsm_replicate_incident.incident")
                        .path("involvedResources")
                        .path("array").get(0)
                        .path("tibco_itsm_replicate_incident.incident.involvedResources")
                        .path("location").asText();

        int numberOfAttributes = jsonNode.path("body")
                .path("incident")
                .path("tibco_itsm_replicate_incident.incident")
                .path("attributes")
                .path("array").size();

        System.out.println("location=" + location );
        System.out.println("numberOfAttributes=" + numberOfAttributes );
        System.out.println(jsonNode.getClass().getName());
        System.out.println(jsonNode);



    }

    public void demoObjectMapperUsingAt() throws Exception {
        System.out.println(System.lineSeparator() + "=========demoObjectMapperUsingAt=========");


        URL urlFile =ClassLoader.getSystemClassLoader().getResource("Data.json");

        JsonNode jsonNode = om.readTree(urlFile);

        String location = jsonNode
                .at(
                        JsonPointer.compile("/body/incident/tibco_itsm_replicate_incident.incident/involvedResources/array/0/tibco_itsm_replicate_incident.incident.involvedResources/location")
                ).asText();
           ;

        int numberOfAttributes = jsonNode
                .at(
                        JsonPointer.compile("/body/incident/tibco_itsm_replicate_incident.incident/attributes/array")
                ).size();


        System.out.println("location=" + location );
        System.out.println("numberOfAttributes=" + numberOfAttributes );


        System.out.println(jsonNode.getClass().getName());
        System.out.println(jsonNode);





    }


    public void demoJsonPath() throws Exception {
        System.out.println(System.lineSeparator() + "=========demoJsonPath=========");


        //Baeldung.com
        //jsonpath spring
        //Nummer 3  https://www.baeldung.com/guide-to-jayway-jsonpath

        URL urlFile =ClassLoader.getSystemClassLoader().getResource("Data.json");

        DocumentContext documentContext = JsonPath.parse(urlFile);

        String location = documentContext.read(
                      "$.body.incident.['tibco_itsm_replicate_incident.incident'].involvedResources.array[0].['tibco_itsm_replicate_incident.incident.involvedResources'].location"
                );


       int numberOfAttributes = documentContext.read(
                      "$.body.incident.['tibco_itsm_replicate_incident.incident'].attributes.array.length()"
                );


        System.out.println("location=" + location );
        System.out.println("numberOfAttributes=" + numberOfAttributes );
        //app.quicktype.io
        //Json Generator generate model to json
    }

}
