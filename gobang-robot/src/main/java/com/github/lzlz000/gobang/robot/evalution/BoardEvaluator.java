package com.github.lzlz000.gobang.robot.evalution;

import com.github.lzlz000.gobang.common.game.Board;

public interface BoardEvaluator {
    Evaluation evaluate(Board board);

    void setMyColor(Board.Color color);
}
