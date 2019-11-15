package com.github.lzlz000.gobang.robot.robot0;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.PathNode;
import com.github.lzlz000.gobang.common.game.Point;
import com.github.lzlz000.gobang.robot.BoardEvaluator;
import com.github.lzlz000.gobang.robot.MoveGenerator;
import com.github.lzlz000.gobang.robot.MoveIterator;
import com.github.lzlz000.gobang.robot.ZobristHashBoard;

import java.util.List;

/**
 * alpha-beta博弈树 得到下一步的点位
 */
public class AlphaBetaTreeMoveGenerator implements MoveGenerator {
    private static final int maxDepth = 2;
    private final BoardEvaluator evaluator;
    private final MoveIterator moveIterator;

    public AlphaBetaTreeMoveGenerator(BoardEvaluator evaluator, MoveIterator moveIterator){
        this.evaluator = evaluator;
        this.moveIterator = moveIterator;
    }

    public Point get(ZobristHashBoard board){
        System.out.println("当前棋局"+board);
        PathNode latest = board.getLatest();
        Board.Color myColor = latest!=null?latest.getColor():Board.Color.Black;
        Board.Color oppoColor = Board.Color.exchange(myColor);
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        Point best = null;
        int maxScore = Integer.MIN_VALUE;
        List<Point> nextMoves = moveIterator.next(board);
        for (Point next : nextMoves) {
            board.put(oppoColor, next);
            int score = min(board, alpha, beta, 0);
            board.cancel(1); // 把模拟下的棋子弹出棋盘
            if (score > maxScore || best == null) {
                maxScore = score;
                best = next;
            }
        }
        return best;
    }


    private int max(ZobristHashBoard board,int alpha, int beta,int depth) {
        System.out.println("AlphaBetaTreeMoveGenerator.max"+depth);
        Evaluation evaluate = evaluator.evaluate(board);
        // 如果迭代到达最大层数或者游戏结束 则返回
        if (depth >= maxDepth || evaluate.getFinishStatus() > 0){
            return evaluate.getScore();
        }
        Board.Color color = board.getLatest().getColor();
        List<Point> nextMoves = moveIterator.next(board);
        for (Point next : nextMoves) {
            board.put(Board.Color.exchange(color),next);
            int score = min(board, alpha, beta, depth+1);
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

    private int min(ZobristHashBoard board, int alpha, int beta, int depth) {
        System.out.println("AlphaBetaTreeMoveGenerator.min"+depth);
        Evaluation evaluate = evaluator.evaluate(board);
        // 如果迭代到达最大层数或者游戏结束 则返回
        if (depth >= maxDepth || evaluate.getFinishStatus() > 0){
            return evaluate.getScore();
        }
        Board.Color color = board.getLatest().getColor();
        List<Point> nextMoves = moveIterator.next(board);
        for (Point next : nextMoves) {
            board.put(Board.Color.exchange(color),next);
            int score = max(board, alpha, beta, depth+1);
            board.cancel(1); // 把模拟下的棋子弹出棋盘
            if (score > beta){
                break;
            }
            if (score < alpha) {
                alpha = score;
            }
        }
        return alpha;
    }


}
