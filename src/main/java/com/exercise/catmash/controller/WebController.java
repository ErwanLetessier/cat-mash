package com.exercise.catmash.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class WebController {

  private String index = loadFromClasspathFile("static/index.html");

  private String javascript = loadFromClasspathFile("static/s.js");

  private String loadFromClasspathFile(String filename) {
    try {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length;
      while ((length = inputStream.read(buffer)) != -1) {
        result.write(buffer, 0, length);
      }
      return result.toString(StandardCharsets.UTF_8.name());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
  public String index() {
    return index;
  }

  @GetMapping(value = "/s.js", produces = "application/javascript")
  public String javascript() {
    return javascript;
  }

}