package lzlz000.gobang.robot.evalution;

import lzlz000.gobang.common.game.BoardImpl;
import lzlz000.gobang.common.game.Point;

public interface MoveEvaluator {
    int evaluate(Point p, BoardImpl board);
}
