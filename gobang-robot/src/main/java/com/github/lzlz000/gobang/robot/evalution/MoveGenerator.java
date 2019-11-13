package com.github.lzlz000.gobang.robot.evalution;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.PathNode;
import com.github.lzlz000.gobang.common.game.Point;

public class MoveGenerator {
    private static final int maxDepth = 3;
    private final BoardEvaluator evaluator;
    private final MoveIterator moveIterator;

    public MoveGenerator(BoardEvaluator evaluator, MoveIterator moveIterator){
        this.evaluator = evaluator;
        this.moveIterator = moveIterator;
    }

    public Point get(Board board){
        PathNode latest = board.getLatest();
        Board.Color myColor = latest!=null?latest.getColor():Board.Color.Black;
        Board.Color oppoColor = Board.Color.exchange(myColor);
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        evaluator.setMyColor(myColor);
        Point best = null;
        Point next;
        int maxScore = Integer.MIN_VALUE;
        while ((next = moveIterator.next(board))!=null){
            board.put(oppoColor, next);
            int score = min(board, alpha, beta, maxDepth);
            board.cancel(1); // 把模拟下的棋子弹出棋盘
            if (score > maxScore) {
                maxScore = score;
                best = next;
            }
        }
        return best;
    }


    private int max(Board board,int alpha, int beta,int depth) {
        Evaluation evaluate = evaluator.evaluate(board);
        // 如果迭代到达最大层数或者游戏结束 则返回
        if (depth >= maxDepth || evaluate.getFinishStatus() > 0){
            return evaluate.getScore();
        }
        Point next;
        Board.Color color = board.getLatest().getColor();
        while ((next = moveIterator.next(board))!=null){
            board.put(Board.Color.exchange(color),next);
            int score = min(board, alpha, beta, depth);
            board.cancel(1); // 把模拟下的棋子弹出棋盘
            if (score < alpha){
                break;
            }
            if (score > beta) {
                beta = score;
            }
        }
        return beta;
    }

    private int min(Board board, int alpha, int beta, int depth) {
        Evaluation evaluate = evaluator.evaluate(board);
        // 如果迭代到达最大层数或者游戏结束 则返回
        if (depth >= maxDepth || evaluate.getFinishStatus() > 0){
            return evaluate.getScore();
        }
        Point next;
        Board.Color color = board.getLatest().getColor();
        while ((next = moveIterator.next(board))!=null) {
            board.put(Board.Color.exchange(color),next);
            int score = max(board, alpha, beta, depth);
            board.cancel(1); // 把模拟下的棋子弹出棋盘
            if (score > beta){
                break;
            }
            if (score < alpha) {
                beta = score;
            }
        }
        return beta;
    }


}
