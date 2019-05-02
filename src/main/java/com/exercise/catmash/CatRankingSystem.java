package com.exercise.catmash;

import com.exercise.catmash.model.RankedCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Consumer;

@Service
public class CatRankingSystem {

  @Autowired
  private CatProvider catProvider;

  @Autowired
  private RankingPolicy rankingPolicy;

  private Map<String, RankedCat> catsById =  new HashMap<>();

  private static Consumer<RankedCat> generateRandomRank =
    new Consumer<RankedCat>() {
      private Random random = new Random();

      @Override
      public void accept(RankedCat cat) {
        Integer i = random.nextInt(1000);
        if (i % 2 == 0)
          i = -i;
        cat.setRank(cat.getRank() + i);
      }
    };


  @PostConstruct
  private void init() {
    catProvider.allCats().forEach(cat -> catsById.put(cat.getId(), cat));
    catsById.values().forEach(generateRandomRank);
  }

  private boolean exists(String id) {
    return catsById.containsKey(id);
  }

  public void vote(String winner, String looser) {
    if (exists(winner) && exists(looser))
      updateRanks(winner, looser);
  }

  private void saveRank(String id, Integer rank) {
    catsById.get(id).setRank(rank);
  }

  private Integer getRank(String id) {
    return catsById.get(id).getRank();
  }

  private synchronized void updateRanks(String winner, String looser) {
    Integer winnerCurrentRank = getRank(winner);
    Integer looserCurrentRank = getRank(looser);
    saveRank(winner, rankingPolicy.winnerNewRank(winnerCurrentRank, looserCurrentRank));
    saveRank(looser, rankingPolicy.looserNewRank(looserCurrentRank, winnerCurrentRank));
  }

  public List<RankedCat> allCatsByDescendingRank() {
    List<RankedCat> cats = new ArrayList<>(catsById.values());
    cats.sort(Comparator.comparing(RankedCat::getRank).reversed());
    return cats;
  }


}
