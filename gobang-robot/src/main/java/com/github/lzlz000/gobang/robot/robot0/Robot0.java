package com.github.lzlz000.gobang.robot.robot0;

import com.github.lzlz000.gobang.common.robot.GobangRobot;
import com.github.lzlz000.gobang.robot.AbstractRobot;
import com.github.lzlz000.gobang.robot.MoveGenerator;

public class Robot0 extends AbstractRobot implements GobangRobot {
    private MoveGenerator moveGenerator;

    public Robot0(){
        moveGenerator = new AlphaBetaTreeMoveGenerator(new BoardEvaluatorImpl(myColor), new NeighborMoveIterator());
    }

    @Override
    protected MoveGenerator moveGenerator() {
        return moveGenerator;
    }
}
