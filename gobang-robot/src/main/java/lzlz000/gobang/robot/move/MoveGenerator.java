package lzlz000.gobang.robot.move;

import lombok.AllArgsConstructor;
import lzlz000.gobang.common.Board;
import lzlz000.gobang.common.Player;
import lzlz000.gobang.common.Point;
import lzlz000.gobang.robot.evalution.MoveEvaluator;

public class MoveGenerator {
    private final MoveEvaluator moveEvaluator;
    private final int maxDepth;

    public MoveGenerator(MoveEvaluator moveEvaluator, int maxDepth) {
        this.moveEvaluator = moveEvaluator;
        this.maxDepth = maxDepth;
    }

    public void generate(Board board, Player player){
    }

    @AllArgsConstructor
    private class MaxScoreDepthPair{
        int score;
        int depth;
    }

    /**
     *
     * @param board 当前棋局
     * @param player 当前玩加 黑/白
     * @param depth 当前搜索深度
     * @param max 当前最大分数 和其对应的 搜索深度
     */
    private void battle(Board board, Player player, int depth, MaxScoreDepthPair max){
        if (depth >= maxDepth){
            return;
        }
        int size = board.getSize();
        int indexLength = size * size;
        for (int i = 0; i < indexLength; i++) {
            int val = board.getByIndex(i);
            if (val == Board.BLANK) {
                Point point = Point.pointOfIndex(size, i);
                board.put(player,point); // 落子到棋盘
                int score = moveEvaluator.evaluate(point, board); // 获取的分
                if (score > max.score || (score == max.score  && depth < max.depth )) {
                    max.score = score;
                    max.depth = depth;
                    battle(board,Player.exchange(player),depth+1,max);
                    board.cancel(1);
                }
            }

        }
    }
}
