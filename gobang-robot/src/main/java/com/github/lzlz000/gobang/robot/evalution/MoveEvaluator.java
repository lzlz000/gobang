package com.github.lzlz000.gobang.robot.evalution;

import com.github.lzlz000.gobang.common.game.BoardImpl;
import com.github.lzlz000.gobang.common.game.Point;

public interface MoveEvaluator {
    int evaluate(Point p, BoardImpl board);
}
