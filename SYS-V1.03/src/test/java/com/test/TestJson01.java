package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class TestJson01 {
    @Test
    public void TestJson() throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1001);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        System.out.println(s);
    }
}
