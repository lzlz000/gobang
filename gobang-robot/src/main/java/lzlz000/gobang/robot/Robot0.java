package lzlz000.gobang.robot;

import lzlz000.gobang.common.*;
import lzlz000.gobang.common.game.BoardImpl;
import lzlz000.gobang.common.game.GobangGame;
import lzlz000.gobang.common.game.PathNode;
import lzlz000.gobang.common.game.Point;
import lzlz000.gobang.common.robot.GobangRobot;

public class Robot0 implements GobangRobot {

    private GobangGame gobangGame;
    private Winner player;
    private BoardImpl mirror;
    private int currentStep = 0;

    @Override
    public void start(GobangGame gobangGame, Winner player) {
        this.gobangGame = gobangGame;
        this.player = player;
        this.mirror = new BoardImpl(gobangGame.getBoard().size());
    }

    @Override
    public Point yourTurn(long restTime) {
        BoardImpl board = gobangGame.getBoard();
        PathNode last = board.getLast(); // 最后一次肯定是对手走的
        mirror.put(last.getPlayer(),new Point(last.getX(),last.getY())); // 把最后一步棋子加入当前镜像棋盘中，
        currentStep = mirror.steps(); // 记录当前


        return null;
    }

    @Override
    public Winner getPlayer() {
        return null;
    }
}
