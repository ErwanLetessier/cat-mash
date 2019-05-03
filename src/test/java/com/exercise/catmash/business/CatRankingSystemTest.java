package com.exercise.catmash.business;

import com.exercise.catmash.model.RankedCat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatRankingSystemTest {

  private static final Integer PRIZE = 150;

  @Mock private CatProvider catProvider = mock(CatProvider.class);

  private SimpleRankingPolicy rankingPolicy = new SimpleRankingPolicy(PRIZE);

  private final List<RankedCat> allCats = Arrays.asList(
    new RankedCat("i", "http://first.cat", 1000),
    new RankedCat("j", "http://second.cat", 800),
    new RankedCat("k", "http://third.cat", 1300)
  );

  private CatRankingSystem rankingSystem;

  @Before public void init() {
    when(catProvider.allCats()).thenReturn(allCats);
    rankingSystem = new CatRankingSystem(catProvider, rankingPolicy);
  }

  @Test public void should_increase_rank_of_winner_cat() {
    rankingSystem.vote("i", "j");
    assertThat(rankingSystem.getRank("i"), equalTo(1150));
  }

  @Test public void should_decrease_rank_of_looser_cat() {
    rankingSystem.vote("i", "j");
    assertThat(rankingSystem.getRank("j"), equalTo(650));
  }

  @Test public void should_not_change_rank_of_any_cat_that_is_not_involved_in_the_vote() {
    rankingSystem.vote("i", "j");
    assertThat(rankingSystem.getRank("k"), equalTo(1300));
  }

}
