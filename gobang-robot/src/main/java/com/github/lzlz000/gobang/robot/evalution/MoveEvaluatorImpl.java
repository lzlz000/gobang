package com.github.lzlz000.gobang.robot.evalution;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.Point;

import java.util.function.Function;

/**
 * 计算每一步的得分
 */
public class MoveEvaluatorImpl implements MoveEvaluator {

    private final LineEvaluation lineEvaluation = new LineEvaluation();

    public int evaluate(Point p, Board board){
        Line[] lines = {direction1(p, board),direction2(p, board),direction3(p, board), direction4(p, board)};
        int score = 0;
        for (Line line : lines) {
            score += lineEvaluation.getScore(line);
        }
        return score;
    }
    /*
     四个方向,约定编号
        2↘  3↓  4↙
             上
      1→  左  右
            下
     */
    /** 左右 */
    private Line direction1(Point p, Board board){
        Line line = new Line();
        line.addMine();
        spliceLine(p, board, line, true,  p0 -> new Point(p.getX()-1 ,p.getY()));
        spliceLine(p, board, line, false,  p0 -> new Point(p.getX()+1 ,p.getY()));
        return line;
    }

    /** 左上右下 */
    private Line direction2(Point p, Board board){
        Line line = new Line();
        line.addMine();
        spliceLine(p, board, line, true,  p0 -> new Point(p.getX()-1 ,p.getY()-1));
        spliceLine(p, board, line, false,  p0 -> new Point(p.getX()+1 ,p.getY()+1));
        return line;
    }

    /** 上下 */
    private Line direction3(Point p, Board board){
        Line line = new Line();
        line.addMine();
        spliceLine(p, board, line, true,  p0 -> new Point(p.getX() ,p.getY()-1));
        spliceLine(p, board, line, false,  p0 -> new Point(p.getX() ,p.getY()+1));
        return line;
    }

    /** 右上左下 */
    private Line direction4(Point p, Board board){
        Line line = new Line();
        line.addMine();
        spliceLine(p, board, line, true,  p0 -> new Point(p.getX()+1 ,p.getY()-1));
        spliceLine(p, board, line, false,  p0 -> new Point(p.getX()-1 ,p.getY()+1));
        return line;
    }

    private void spliceLine(Point p, Board board, Line line , boolean leftOrRight, Function<Point,Point> move){
        int x0 = p.getX();
        int y0 = p.getY();
        Point nextPoint = move.apply(new Point(x0, y0));
        int mineVal = board.get(x0, y0).getValue();
        int x = nextPoint.getX();
        int y = nextPoint.getY();
        int zeroCount = 0;
        int val;
        while (x >= 0 && y >= 0 && x<board.size() && y<board.size()
                && ((val = board.get(x, y).getValue()) == mineVal || val == 0)) {
            if (val == 0) {
                zeroCount++;
                if (leftOrRight) {
                    line.leftAddEmpty();
                } else {
                    line.addEmpty();
                }
            } else {
                zeroCount = 0;
                if (leftOrRight) {
                    line.leftAddMine();
                } else {
                    line.addMine();
                }
            }
            nextPoint = move.apply(nextPoint);
            x = nextPoint.getX();
            y = nextPoint.getY();
            if (zeroCount == 2) {
                break;
            }
        }
    }


}
