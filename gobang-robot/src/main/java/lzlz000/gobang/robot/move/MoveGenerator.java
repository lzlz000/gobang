package lzlz000.gobang.robot.move;

import lzlz000.gobang.common.game.BoardImpl;
import lzlz000.gobang.common.Winner;
import lzlz000.gobang.robot.evalution.MoveEvaluator;

public class MoveGenerator {
    private final MoveEvaluator moveEvaluator;
    // 最大搜索深度应设为奇数
    private final int maxDepth;

    public MoveGenerator(MoveEvaluator moveEvaluator, int maxDepth) {
        this.moveEvaluator = moveEvaluator;
        this.maxDepth = maxDepth;
    }

    public void generate(BoardImpl board, Winner player){
    }

    /**
     *
     * @param board 当前棋局
     * @param player 当前玩加 黑/白
     * @param depth 当前搜索深度 从0开始 则偶数是自己的回合，奇数是对首回合
     */
    private int battle(BoardImpl board, Winner player, int depth, int alpha, int beta){
//        if (depth >= maxDepth){
//            return null;
//        }
//        int size = board.size();
//        int indexLength = size * size;
//        int maxScore = 0;
//        int maxDepth = depth;
//        Point maxScorePoint = null;
//        for (int i = 0; i < indexLength; i++) {
//            int val = board.getByIndex(i);
//            if (val == BoardImpl.BLANK) {
//                Point point = Point.pointOfIndex(size, i);
//                board.put(player,point); // 落子到棋盘
//                int score = moveEvaluator.evaluate(point, board);
//                Result result = battle(board, Winner.exchange(player), depth + 1);
//                if (result != null) {
//                    score += result.score;
//                }
//                if (score >= maxScore) {
//                    maxScore = score;
//                    maxScorePoint = point;
//                    maxDepth = 0;
//                }
//            }
//
//        }
//        return (depth&1)==0?maxScore:-maxScore;
        return 0;
    }
}
