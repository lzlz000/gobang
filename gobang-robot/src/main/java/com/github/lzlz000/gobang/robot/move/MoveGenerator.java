package com.github.lzlz000.gobang.robot.move;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.BoardImpl;
import com.github.lzlz000.gobang.common.game.Point;
import com.github.lzlz000.gobang.common.game.Winner;
import com.github.lzlz000.gobang.robot.evalution.MoveEvaluator;

public class MoveGenerator {
    private final MoveEvaluator moveEvaluator;
    // 最大搜索深度应设为奇数
    private final int maxDepth;

    public MoveGenerator(MoveEvaluator moveEvaluator, int maxDepth) {
        this.moveEvaluator = moveEvaluator;
        this.maxDepth = maxDepth;
    }

    public void generate(Board board, Winner player){
    }

    /**
     *
     * @param board 当前棋局
     * @param color 当前玩加 黑/白
     * @param depth 当前搜索深度 从0开始 则偶数是自己的回合，奇数是对首回合
     */
    private int battle(Board board, Board.Color color, int depth, int alpha, int beta){
        if (depth >= maxDepth){
            return 0;
        }
        int size = board.size();
        int indexLength = size * size;
        boolean alphaOrBeta = (depth ^ 1) == 0; // 0 2 4 是alpha 1 3 5 beta
        Point maxScorePoint = null;
        for (int i = 0; i < indexLength; i++) {
            Board.Color val = board.get(i % size, i / size);
            if (val == Board.Color.Blank) {
                Point point = Point.pointOfIndex(size, i);
                board.put(color,point); // 落子到棋盘
                int score = moveEvaluator.evaluate(point, board);
                if (alphaOrBeta) {
                    score += battle(board, Board.Color.exchange(color), depth + 1,alpha,beta);
                    if (score > alpha) {
                        alpha = score;
                    }
                }else {
                    score -= battle(board, Board.Color.exchange(color), depth + 1,alpha,beta);
                    if (score > alpha) {
                        alpha = score;
                    }
                }

            }

        }
//        return (depth&1)==0?maxScore:-maxScore;
        return 0;
    }
}
