package com.aditz.tennisgame.service;

import com.aditz.tennisgame.model.Player;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Builder
@Slf4j
public record RefereeSystem(List<Player> players) {


    //Can be done as Observer  - Referee system can be the publisher and Players are the subscribers.
    public void win(Player player) {
        Player theOtherPlayer = players.stream().filter(e ->
                !e.getName().equals(player.getName())).findFirst().get();

             boolean duece = !theOtherPlayer.getName().equals(player.getName()) &&
                      player.getScore().points() == theOtherPlayer.getScore().points()
                              && theOtherPlayer.getScore().points() == 40;
             if(duece && player.getScore().advantage()){
                 theOtherPlayer.updateScore(
                         new Player.Score(0,theOtherPlayer.getScore().games(),false));
                 player.updateScore(new Player.Score(0,player.getScore().games()+1,false));

             }
             else {
                 Player.Score score = computeScore(player.getScore(), duece);
                 player.updateScore(score);
             }
             logScore();
             }

    private void logScore() {
        players.forEach(e-> {
            log.info("Player {} won the serve", e.getName());
            log.info("Score for {}, games, {}, {} points ,{} advantage",
                    e.getName(), e.getScore().games(), e.getScore().points(), e.getScore().advantage());
        });
    }

    private Player.Score computeScore(Player.Score e, boolean potentialDuece) {

        return switch (e.points()) {
            case 0 -> new Player.Score(15, e.games(),false);
            case 15 -> new Player.Score(30, e.games(),false);
            case 30 -> new Player.Score(40, e.games(),false);
            case 40 -> potentialDuece?new Player.Score(40,0,true):
                new Player.Score(0,e.games()+1,false);
            default -> new Player.Score(0, 0,false);
        };
    }


}
