package com.exercise.catmash;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void index() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
        .andExpect(status().isOk())
        .andExpect(content().string(startsWith("<html>")));
  }

  @Test
  public void javascript() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/s.js"))
      .andExpect(status().isOk());
  }
}