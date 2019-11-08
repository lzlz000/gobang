package lzlz000.gobang.robot;

import lzlz000.gobang.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 机器人调度器
 */
public class RobotDispatcher {
    Logger log = LoggerFactory.getLogger(RobotDispatcher.class);

    private GobangRobot winner;
    private static final int MAX_RETRY = 3;

    public void start(GobangRobot blackRobot, GobangRobot whiteRobot){
        GobangGame game = new GobangGameImpl(15,null);
        blackRobot.start(game, Player.Black);
        whiteRobot.start(game, Player.White);
        // 有可能游戏没结束但是在调度器中对胡闹的机器人判负
        while (!game.isGameOver() && winner == null){
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            turn(blackRobot, whiteRobot, game);
            if (!game.isGameOver() && winner == null) {
                turn(whiteRobot, blackRobot, game);
            }
        }
        Player winner = game.getWinner();
        if (winner == null) {
            winner = this.winner == blackRobot?Player.Black:Player.White;
        }
        if (winner == Player.Draw) {
            log.info("游戏结束 平局" + game.getBoard().toString());
        }else {
            log.info("游戏结束 胜利者:"+ winner + game.getBoard().toString());

        }
    }

    private void turn(GobangRobot active, GobangRobot another, GobangGame game) {
        Point point = active.yourTurn(Long.MAX_VALUE);
        // 这里已经事先判断过游戏没有结束，也保证是他的回合才会给他处理 所以只有可能是他下在了不能下的位置，
        // 重试三次如果机器人继续胡闹就判负
        int retry = 0;
        while (!game.move(active.getPlayer(),point.getX(),point.getY())) {
            point = active.yourTurn(Long.MAX_VALUE);
            retry ++;
            if(retry>=MAX_RETRY){
                winner = another;
            }
        }
        log.debug(active.getPlayer()+" x:"+point.getX()+" y:"+point.getY());
//        Future<?> submit = executor.submit(() -> {
//            // todo 有时间限制的回合
//            Board.Point point = active.yourTurn(Long.MAX_VALUE);
//            // 这里已经事先判断过游戏没有结束，也保证是他的回合才会给他处理 所以只有可能是他下在了不能下的位置，
//            // 重试三次如果机器人继续胡闹就判负
//            int retry = 0;
//            while (!game.move(active.getPlayer(),point.getX(),point.getY())) {
//                point = active.yourTurn(Long.MAX_VALUE);
//                retry ++;
//                if(retry>=MAX_RETRY){
//                    winner = another;
//                }
//            }
//        });
//        try {
//            submit.get(MAX_PROCESS_MS,TimeUnit.MILLISECONDS);
//        } catch (InterruptedException | ExecutionException | TimeoutException e) {
//            e.printStackTrace();
//            submit.cancel(true);
//            // 运行超时 判负
//            winner = another;
//        }
    }
}
