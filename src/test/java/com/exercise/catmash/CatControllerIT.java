package com.exercise.catmash;

import com.exercise.catmash.model.RankedCat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatControllerIT {

  @LocalServerPort
  private int port;

  private URL baseUrl;

  @Autowired
  private TestRestTemplate template;

  @Before
  public void setUp() throws Exception {
    this.baseUrl = new URL(String.format("http://localhost:%d/", port));
  }

  private String serviceUrl(String path) {
    return baseUrl.toString() + path;
  }

  @Test public void twoRandomCats_should_return_2_distinct_cats() throws Exception {
    ResponseEntity<RankedCat[]> response = template.getForEntity(serviceUrl("twoRandomCats"), RankedCat[].class);
    RankedCat[] cats = response.getBody();
    assertThat(cats.length, equalTo(2));
    assertNotEquals(cats[0], cats[1]);
  }

}