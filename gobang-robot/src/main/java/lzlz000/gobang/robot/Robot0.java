package lzlz000.gobang.robot;

import lzlz000.gobang.common.*;
import lzlz000.gobang.common.robot.GobangRobot;

public class Robot0 implements GobangRobot {

    private GobangGame gobangGame;
    private Player player;
    private Board mirror;
    private int currentStep = 0;

    @Override
    public void start(GobangGame gobangGame, Player player) {
        this.gobangGame = gobangGame;
        this.player = player;
        this.mirror = new Board(gobangGame.getBoard().getSize());
    }

    @Override
    public Point yourTurn(long restTime) {
        Board board = gobangGame.getBoard();
        PathNode last = board.getLast(); // 最后一次肯定是对手走的
        mirror.put(last.getPlayer(),new Point(last.getX(),last.getY())); // 把最后一步棋子加入当前镜像棋盘中，
        currentStep = mirror.getSteps(); // 记录当前


        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
}
