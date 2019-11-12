package com.github.lzlz000.gobang.common.robot;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.GobangGame;
import com.github.lzlz000.gobang.common.game.Point;

/**
 * 下棋的策略为随机找一点 如果有空就下在这里 否则就依次向后搜索有任何空位就下下去
 */
public class GiveupRobot implements GobangRobot {
    private Board.Color player;

    @Override
    public void start(GobangGame gobangGame, Board.Color player) {
        this.player = player;
    }

    @Override
    public Point yourTurn(long restTime) {
        return null;
    }

    @Override
    public Board.Color getPlayer() {
        return player;
    }
}
