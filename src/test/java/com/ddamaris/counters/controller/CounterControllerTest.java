package com.ddamaris.counters.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CounterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional
    void createCounter() throws Exception {
        mvc.perform(MockMvcRequestBuilders
        .post("/create?name=test1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    void createCounterExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/create?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .post("/create?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Transactional
    void incrementCounterNoCounter() throws Exception {
        MvcResult res = mvc.perform(MockMvcRequestBuilders
                .put("/increment?name=test1"))
                .andReturn();

        String tmp = res.getResponse().getContentAsString();
        assertEquals("-1", res.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    void incrementCounterExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/create?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult res = mvc.perform(MockMvcRequestBuilders
                .put("/increment?name=test1"))
                .andReturn();

        String tmp = res.getResponse().getContentAsString();
        assertEquals("1", res.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    void getCounterValueNoCounter() throws Exception {
        MvcResult res = mvc.perform(MockMvcRequestBuilders
                .get("/getvalue?name=test1"))
                .andReturn();

        assertEquals("-1", res.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    void getCounterValueCounterExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/create?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .put("/increment?name=test1"));

        MvcResult res = mvc.perform(MockMvcRequestBuilders
                .get("/getvalue?name=test1"))
                .andReturn();

        assertEquals("1", res.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    void deleteCounterNoCounter() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/delete?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteCounterCounterExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/create?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .delete("/delete?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    void getTotalNoCounters() throws Exception {
        MvcResult res = mvc.perform(MockMvcRequestBuilders
                .get("/gettotal"))
                .andReturn();

        String tmp = res.getResponse().getContentAsString();
        assertEquals("-1", res.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    void getTotalCountersExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/create?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .put("/increment?name=test1"));

        mvc.perform(MockMvcRequestBuilders
                .post("/create?name=test2"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .put("/increment?name=test2"));

        MvcResult res = mvc.perform(MockMvcRequestBuilders
                .get("/gettotal"))
                .andReturn();

        String tmp = res.getResponse().getContentAsString();
        assertEquals("2", res.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    void getSetNoCounters() throws Exception {
        MvcResult res = mvc.perform(MockMvcRequestBuilders
                .get("/getset"))
                .andReturn();

        String tmp = res.getResponse().getContentAsString();
        assertEquals("[]", res.getResponse().getContentAsString());
    }

    @Test
    @Transactional
    void getSetCounterExists() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/create?name=test1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult res = mvc.perform(MockMvcRequestBuilders
                .get("/getset"))
                .andReturn();

        String tmp = res.getResponse().getContentAsString();
        assertEquals("[\"test1\"]", res.getResponse().getContentAsString());
    }
}