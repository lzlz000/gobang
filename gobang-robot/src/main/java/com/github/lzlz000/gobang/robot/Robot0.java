package com.github.lzlz000.gobang.robot;

import com.github.lzlz000.gobang.common.game.*;
import com.github.lzlz000.gobang.common.robot.GobangRobot;
import com.github.lzlz000.gobang.robot.evalution.MoveGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Robot0 implements GobangRobot {
    private Logger logger = LoggerFactory.getLogger(Robot0.class);

    private Board.Color myColor;
    private Board.Color opponent;
    private Board mirror;

    protected abstract MoveGenerator moveGenerator();

    @Override
    public void start(int color, int boardSize) {
        this.myColor = Board.Color.valueOf(color);
        this.opponent = Board.Color.exchange(this.myColor);
        mirror = new BoardImpl(boardSize);
    }

    @Override
    public Point yourTurn(int x, int y) {
        if (x>=0 && y>=0) {
            if (!mirror.put(opponent, new Point(x, y))) {
                logger.error(String.format("无法在点位落子： x:%s-y:%s\n%s", x,y,mirror));
            }
        }
        Point myChoice = moveGenerator().get(mirror);
        if (!mirror.put(myColor, myChoice)) {
            logger.error(String.format("机器人给出的下棋点位无法落子： x:%s-y:%s\n%s", x,y,mirror));
        }
        return myChoice;
    }

    @Override
    public int getColor() {
        return myColor.getValue();
    }
}
