package lzlz000.gobang.common.robot;

import lzlz000.gobang.common.Board;
import lzlz000.gobang.common.GobangGame;
import lzlz000.gobang.common.Winner;
import lzlz000.gobang.common.Point;

/**
 * 下棋的策略为随机找一点 如果有空就下在这里 否则就依次向后搜索有任何空位就下下去
 */
public class ErrorRobot implements GobangRobot {
    private Board.Color player;

    @Override
    public String name() {
        return "错误机器人";
    }

    @Override
    public void start(GobangGame gobangGame, Board.Color player) {
        this.player = player;
    }

    @Override
    public Point yourTurn(long restTime) {
        return new Point(-1,0);
    }

    @Override
    public Board.Color getPlayer() {
        return player;
    }
}
