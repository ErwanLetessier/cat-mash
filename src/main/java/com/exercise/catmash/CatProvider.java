package com.exercise.catmash;

import com.exercise.catmash.model.CatImages;
import com.exercise.catmash.model.RankedCat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CatProvider {

  private final List<RankedCat> rankedCats;

  public CatProvider() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      InputStream inputStream = CatProvider.class.getClassLoader().getResourceAsStream("cats.json");
      CatImages catImages = mapper.readValue(inputStream, CatImages.class);
      rankedCats = catImages.getImages();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Set<RankedCat> pickTwoRandomCats() {
    Random random = new Random();
    Set<RankedCat> randomCats = new HashSet<>(2);
    while (randomCats.size() < 2)
      randomCats.add(rankedCats.get(random.nextInt(rankedCats.size())));
    return randomCats;
  }

  public List<RankedCat> allCats() {
    return rankedCats;
  }
}
