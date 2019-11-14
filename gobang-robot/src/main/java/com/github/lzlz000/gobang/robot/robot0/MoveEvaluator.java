package com.github.lzlz000.gobang.robot.robot0;

import com.github.lzlz000.gobang.common.game.Board;

public interface MoveEvaluator {
    Evaluation evaluate(Board board);
}
