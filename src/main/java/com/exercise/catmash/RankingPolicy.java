package com.exercise.catmash;

public interface RankingPolicy {

  Integer looserNewRank(Integer looserCurrentRank, Integer winnerCurrentRank);

  Integer winnerNewRank(Integer winnerCurrentRank, Integer looserCurrentRank);
}
