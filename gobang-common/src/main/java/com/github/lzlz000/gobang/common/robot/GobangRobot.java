package com.github.lzlz000.gobang.common.robot;

import com.github.lzlz000.gobang.common.game.Point;

public interface GobangRobot {
    int BLACK  = 1;
    int WHITE = 2;

    default String name(){
        return getClass().getSimpleName();
    }

    void start(int color, int boardSize);

    /**
     * @param x 对手落子位置，-1代表第一步
     * @param y 对手落子位置，-1代表第一步
     * @return null 代表认输
     */
    Point yourTurn(int x,int y);

    int getMyColor();

}
