package com.github.lzlz000.gobang.robot;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.robot.robot0.Evaluation;

public interface BoardEvaluator {
    Evaluation evaluate(ZobristHashBoard board);

    void setMyColor(Board.Color color);
}
