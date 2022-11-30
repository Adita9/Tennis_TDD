package com.aditz.tennisgame;

import com.aditz.tennisgame.model.Player;
import com.aditz.tennisgame.service.RefereeSystem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
About this Kata
This Kata is about implementing a simple tennis game. I came up with it while thinking about Wii tennis, where they have simplified tennis, so each set is one game.

The scoring system is rather simple:

Each player can have either of these points in one game “love” “15” “30” “40”
If you have 40 and you win the point you win the game, however there are special rules.
If both have 40 the players are “deuce”.
If the game is in deuce, the winner of a point will have advantage
If the player with advantage wins the ball he wins the game
If the player without advantage wins they are back at deuce.
Alternate description of the rules per Wikipedia (http://en.wikipedia.org/wiki/Tennis#Scoring ):

A game is won by the first player to have won at least four points in total and at least two points more than the opponent.
The running score of each game is described in a manner peculiar to tennis: scores from zero to three points are described as “love”, “15”, “30”, and “40” respectively.
If at least three points have been scored by each player, and the scores are equal, the score is “deuce”.
If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is “advantage” for the player in the lead.

 */
class TennisTest {
    
    
    @Test
    void given_2PlayerWith0score_When_Player1WinsServe_Expect_Score15to0PointsAnd0to0Games() {

        Player player1 = Player.builder().name("Adrian")
                .score(new Player.Score(0,0,false)).build();
        Player player2 = Player.builder().name("Alex")
                .score(new Player.Score(0,0,false)).build();

        RefereeSystem refereeSystem = new RefereeSystem(List.of(player1, player2));

        refereeSystem.win(player1);

        assertEquals(15,player1.getScore().points());
        assertEquals(0,player1.getScore().games());

    }

    @Test
    void given_2Player_When_Player1WinsServe_Expect_Score30to0PointsAnd0to0Games() {

        Player player1 = Player.builder().name("Adrian")
                .score(new Player.Score(15,0,false)).build();
        Player player2 = Player.builder().name("Alex")
                .score(new Player.Score(0,0,false)).build();

        RefereeSystem refereeSystem = new RefereeSystem(List.of(player1, player2));

        refereeSystem.win(player1);

        assertEquals(30,player1.getScore().points());
        assertEquals(0,player1.getScore().games());

    }

    @Test
    void given_2Player_When_Player1WinsServe_Expect_Score40to0PointsAnd0to0Games() {

        Player player1 = Player.builder().name("Adrian")
                .score(new Player.Score(30,0,false)).build();
        Player player2 = Player.builder().name("Alex")
                .score(new Player.Score(0,0,false)).build();

        RefereeSystem refereeSystem = new RefereeSystem(List.of(player1, player2));

        refereeSystem.win(player1);

        assertEquals(40,player1.getScore().points());
        assertEquals(0,player1.getScore().games());

    }

    @Test
    void given_2Player_When_Player1WinsServe_Expect_Score0to0PointsAnd1to0Games() {

        Player player1 = Player.builder().name("Adrian")
                .score(new Player.Score(40,0,false)).build();
        Player player2 = Player.builder().name("Alex")
                .score(new Player.Score(0,0,false)).build();

        RefereeSystem refereeSystem = new RefereeSystem(List.of(player1, player2));

        refereeSystem.win(player1);

        assertEquals(0,player1.getScore().points());
        assertEquals(1,player1.getScore().games());
    }

    @Test
    void given_2Player_When_Player1WinsServe_Expect_Score40to40PointsAnd0to0GamesAndAdvantage() {

        Player player1 = Player.builder().name("Adrian")
                .score(new Player.Score(40,0,false)).build();
        Player player2 = Player.builder().name("Alex")
                .score(new Player.Score(40,0,false)).build();

        RefereeSystem refereeSystem = new RefereeSystem(List.of(player1, player2));

        refereeSystem.win(player1);

        assertEquals(40,player1.getScore().points());
        assertEquals(0,player1.getScore().games());
        assertEquals(true,player1.getScore().advantage());
    }


    @Test
    void given_2Player_When_Player1WinsServeAndHasAdvantage_Expect_Score40to40PointsAnd0to0GamesAndAdvantage() {

        Player player1 = Player.builder().name("Adrian")
                .score(new Player.Score(40,0,true)).build();
        Player player2 = Player.builder().name("Alex")
                .score(new Player.Score(40,0,true)).build();

        RefereeSystem refereeSystem = new RefereeSystem(List.of(player1, player2));

        refereeSystem.win(player1);

        assertEquals(0,player1.getScore().points());
        assertEquals(1,player1.getScore().games());
        assertEquals(false,player1.getScore().advantage());

        assertEquals(0,player2.getScore().points());
        assertEquals(0,player2.getScore().games());
        assertEquals(false,player2.getScore().advantage());
    }


}
