package com.exercise.catmash.business;

public class SimpleRankingPolicy implements RankingPolicy {
  @Override
  public Integer looserNewRank(Integer looserCurrentRank, Integer winnerCurrentRank) {
    return looserCurrentRank - 100;
  }

  @Override
  public Integer winnerNewRank(Integer winnerCurrentRank, Integer looserCurrentRank) {
    return winnerCurrentRank + 100;
  }
}
