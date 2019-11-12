package com.github.lzlz000.gobang.common.robot;

import com.github.lzlz000.gobang.common.game.*;
import com.github.lzlz000.gobang.common.game.referee.SimpleGameReferee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 机器人调度器
 */
public class SimpleRobotDispatcher implements RobotDispatcher {
    private Logger log = LoggerFactory.getLogger(SimpleRobotDispatcher.class);

    private GobangRobot winnerRobot;
    private static final int MAX_RETRY = 3;

    public GameResult startGame(GobangRobot blackRobot, GobangRobot whiteRobot){
        GobangGame game = new GobangGameImpl(15,new SimpleGameReferee(),null);
        blackRobot.start(Board.Color.Black.getValue(),game.getBoard().size());
        whiteRobot.start(Board.Color.White.getValue(),game.getBoard().size());
        // 有可能游戏没结束但是在调度器中对胡闹的机器人判负
        while (!game.isGameOver() && winnerRobot == null){
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            turn(blackRobot, whiteRobot, game);
            if (!game.isGameOver() && winnerRobot == null) {
                turn(whiteRobot, blackRobot, game);
            }
        }
        Winner winner = game.getWinner();
        if (winner == null) {
            winner = this.winnerRobot == blackRobot?Winner.Black:Winner.White;
        } else {
            this.winnerRobot = winner == Winner.Black?blackRobot:whiteRobot;
        }
        if (winner == Winner.Draw) {
            log.info("游戏结束 平局" + game.getBoard().toString());
        }else {
            log.info("游戏结束 胜利者:"+ winner+":"+this.winnerRobot.name() + game.getBoard().toString());

        }
        return new GameResult(blackRobot.name(),whiteRobot.name(),winner,game.getBoard().getTrace());
    }

    private void turn(GobangRobot active, GobangRobot another, GobangGame game) {
        Point point;
        int retry = 0;
        do {
            // 这里已经事先判断过游戏没有结束，也保证是他的回合才会给他处理 所以只有可能是他下在了不能下的位置，
            // 重试三次如果机器人继续胡闹就判负
            PathNode latest = game.getBoard().getLatest();

            point = active.yourTurn(latest!=null?latest.getX():-1,latest!=null?latest.getY():-1);
            if(retry>=MAX_RETRY){
                winnerRobot = another;
                log.info(active.getColor()+":" + active.name()+"持续返回不可用的结果，判负");
                break;
            }
            retry ++;

        } while (point!=null && !game.move(Board.Color.valueOf(active.getColor()),point.getX(),point.getY()));
        if (point != null) {
            log.debug(active.getColor()+" x:"+point.getX()+" y:"+point.getY());
        } else {
            winnerRobot = another;
            log.info(active.getColor()+":" + active.name()+" 认输");
        }
    }
}
