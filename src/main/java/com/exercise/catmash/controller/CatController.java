package com.exercise.catmash.controller;

import com.exercise.catmash.business.CatProvider;
import com.exercise.catmash.business.CatRankingSystem;
import com.exercise.catmash.model.RankedCat;
import com.exercise.catmash.model.VoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CatController {

  @Autowired
  private CatProvider catProvider;

  @Autowired
  private CatRankingSystem catRankingSystem;

  @GetMapping("/match")
  public Set<RankedCat> pickTwoRandomCats() {
    return catProvider.pickTwoRandomCats();
  }

  @PostMapping("/vote") public void vote(@RequestBody VoteResult voteResult) {
    catRankingSystem.vote(voteResult.getWinner(), voteResult.getLooser());
  }

  @GetMapping("/ranking")
  public List<RankedCat> allCatsRanking() {
    return catRankingSystem.allCatsByDescendingRank();
  }

}