package lzlz000.gobang.robot.move;

import lombok.AllArgsConstructor;
import lzlz000.gobang.common.Board;
import lzlz000.gobang.common.Player;
import lzlz000.gobang.common.Point;
import lzlz000.gobang.robot.evalution.MoveEvaluator;

public class MoveGenerator {
    private final MoveEvaluator moveEvaluator;
    // 最大搜索深度应设为奇数
    private final int maxDepth;

    public MoveGenerator(MoveEvaluator moveEvaluator, int maxDepth) {
        this.moveEvaluator = moveEvaluator;
        this.maxDepth = maxDepth;
    }

    public void generate(Board board, Player player){
    }

    @AllArgsConstructor
    private class Result{
        int score;
        int maxDepth;
        Point point;
    }

    /**
     *
     * @param board 当前棋局
     * @param player 当前玩加 黑/白
     * @param depth 当前搜索深度 从0开始 则偶数是自己的回合，奇数是对首回合
     * @param max 当前最大分数 和其对应的 搜索深度
     */
    private Result battle(Board board, Player player, int depth){
        if (depth >= maxDepth){
            return null;
        }
        int size = board.getSize();
        int indexLength = size * size;
        int maxScore = 0;
        int maxDepth = depth;
        Point maxScorePoint = null;
        for (int i = 0; i < indexLength; i++) {
            int val = board.getByIndex(i);
            if (val == Board.BLANK) {
                Point point = Point.pointOfIndex(size, i);
                board.put(player,point); // 落子到棋盘
                int score = moveEvaluator.evaluate(point, board);
                Result result = battle(board, Player.exchange(player), depth + 1);
                if (result != null) {
                    score += result.score;
                }
                if (score >= maxScore) {
                    maxScore = score;
                    maxScorePoint = point;
                    maxDepth =
                }
            }

        }
        return (depth&1)==0?maxScore:-maxScore;
    }
}
