package com.exercise.catmash.business;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleRankingPolicy implements RankingPolicy {

  private final int prize;

  public SimpleRankingPolicy() {
    this(100);
  }

  @Override
  public Integer looserNewRank(Integer looserCurrentRank, Integer winnerCurrentRank) {
    return looserCurrentRank - prize;
  }

  @Override
  public Integer winnerNewRank(Integer winnerCurrentRank, Integer looserCurrentRank) {
    return winnerCurrentRank + prize;
  }
}
