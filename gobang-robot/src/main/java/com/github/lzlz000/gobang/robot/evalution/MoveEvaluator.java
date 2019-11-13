package com.github.lzlz000.gobang.robot.evalution;

import com.github.lzlz000.gobang.common.game.Board;

public interface MoveEvaluator {
    int evaluate(Board board);
}
