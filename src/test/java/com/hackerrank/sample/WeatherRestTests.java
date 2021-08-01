package com.hackerrank.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class WeatherRestTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertsRecord() throws Exception {
         String requestContent = "{\n" +
                "\"date\": \"1985-01-01\",\n" +
                "\"firstName\": \"Foo\",\n" +
                "\"lastName\": \"Bar\",\n" +
                "\"phoneNumber\": 3876542098\n" +
                "}";
        String expectedResult = "{\n" +
                "\"id\": 1,\n" +
                "\"date\": \"1985-01-01T00:00:00.000+0000\",\n" +
                "\"firstName\": \"Foo\",\n" +
                "\"lastName\": \"Bar\",\n" +
                "\"phoneNumber\": 3876542098\n" +
                "}";
        mockMvc.perform(post("/endpoint/insert")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void statusIsBadRequestWhenParametersAreMissing() throws Exception {
        String requestContent = "{\n" +
                "\"lastName\": \"Bar\",\n" +
                "\"phoneNumber\": 3876542098\n" +
                "}";

        mockMvc.perform(post("/endpoint/insert")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void selectsAllRecords() throws Exception {
        String requestContent = "{\n" +
                "\"date\": \"1985-01-01\",\n" +
                "\"firstName\": \"Foo\",\n" +
                "\"lastName\": \"Bar\",\n" +
                "\"phoneNumber\": 3876542098\n" +
                "}";
        String expectedResult = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"date\": \"1985-01-01T00:00:00.000+0000\",\n" +
                "        \"firstName\": \"Foo\",\n" +
                "        \"lastName\": \"Bar\",\n" +
                "        \"phoneNumber\": 3876542098\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"date\": \"1985-01-01T00:00:00.000+0000\",\n" +
                "        \"firstName\": \"Foo\",\n" +
                "        \"lastName\": \"Bar\",\n" +
                "        \"phoneNumber\": 3876542098\n" +
                "    }\n" +
                "]";
        mockMvc.perform(post("/endpoint/insert")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(post("/endpoint/insert")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/endpoint/select"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void selectById() throws Exception {
        String requestContent = "{\n" +
                "\"date\": \"1985-01-01\",\n" +
                "\"firstName\": \"Foo\",\n" +
                "\"lastName\": \"Bar\",\n" +
                "\"phoneNumber\": 3876542098\n" +
                "}";
        String expectedResult = "{\n" +
                "    \"id\": 1,\n" +
                "    \"date\": \"1985-01-01T00:00:00.000+0000\",\n" +
                "    \"firstName\": \"Foo\",\n" +
                "    \"lastName\": \"Bar\",\n" +
                "    \"phoneNumber\": 3876542098\n" +
                "}";
        mockMvc.perform(post("/endpoint/insert")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/endpoint/select/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @Test
    public void selectByIdStatusIsNotFoundWhenEntityDoesNotExists() throws Exception {
        mockMvc.perform(get("/endpoint/select/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletesRecord() throws Exception {
        String requestContent = "{\n" +
                "\"date\": \"1985-01-01\",\n" +
                "\"firstName\": \"Foo\",\n" +
                "\"lastName\": \"Bar\",\n" +
                "\"phoneNumber\": 3876542098\n" +
                "}";

        mockMvc.perform(post("/endpoint/insert")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/endpoint/select/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/endpoint/delete/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/endpoint/select/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteByIdStatusIsNotFoundWhenEntityDoesNotExists() throws Exception {
        mockMvc.perform(delete("/endpoint/delete/1"))
                .andExpect(status().isNotFound());
    }
}
