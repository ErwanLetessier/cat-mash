package com.exercise.catmash.business;

public interface RankingPolicy {

  Integer looserNewRank(Integer looserCurrentRank, Integer winnerCurrentRank);

  Integer winnerNewRank(Integer winnerCurrentRank, Integer looserCurrentRank);
}
