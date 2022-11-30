package com.aditz.tennisgame.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Player {

    private final String name;
    private Score score;

      public record Score(int points, int games,boolean advantage){}

    public void updateScore(Score s){
        this.score = s;
    }

}
