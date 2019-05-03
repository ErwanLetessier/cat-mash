package com.exercise.catmash.business;

import com.exercise.catmash.model.RankedCat;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CatRankingSystem {

  private final RankingPolicy rankingPolicy;

  private final Map<String, RankedCat> catsById;

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

  public CatRankingSystem(CatProvider catProvider, RankingPolicy rankingPolicy) {
    this.rankingPolicy = rankingPolicy;
    catsById = catProvider.allCats().stream()
      .collect( Collectors.toMap(RankedCat::getId, Function.identity()) );
  }

  @PostConstruct private void init() {
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

  protected Integer getRank(String id) {
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
