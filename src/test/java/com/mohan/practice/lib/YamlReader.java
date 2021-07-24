package com.mohan.practice.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public interface YamlReader {

    static Object readYamlAsObject(String filePath, Class <?> PojoClass, Object pojoObject){

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        objectMapper.findAndRegisterModules();

        try {
            pojoObject = objectMapper.readValue(new File(filePath),PojoClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pojoObject;

    }
}
