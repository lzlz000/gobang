package com.github.lzlz000.gobang.robot;

import com.github.lzlz000.gobang.common.game.*;
import com.github.lzlz000.gobang.common.robot.GobangRobot;

public class Robot0 implements GobangRobot {

    private GobangGame gobangGame;
    private Board.Color player;
    private BoardImpl mirror;
    private int currentStep = 0;

    @Override
    public void start(int color, int boardSize) {

    }

    @Override
    public Point yourTurn(int x, int y) {
        return null;
    }

    @Override
    public int getColor() {
        return 0;
    }
}
