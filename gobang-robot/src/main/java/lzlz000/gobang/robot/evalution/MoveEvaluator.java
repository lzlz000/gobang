package lzlz000.gobang.robot.evalution;

import lzlz000.gobang.common.Board;
import lzlz000.gobang.common.Point;

public interface MoveEvaluator {
    int evaluate(Point p, Board board);
}
