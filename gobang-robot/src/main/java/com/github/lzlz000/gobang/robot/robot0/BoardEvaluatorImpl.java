package com.github.lzlz000.gobang.robot.robot0;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.PathNode;
import com.github.lzlz000.gobang.common.game.Point;
import com.github.lzlz000.gobang.robot.BoardEvaluator;
import com.github.lzlz000.gobang.robot.ZobristHashBoard;

import java.util.HashMap;
import java.util.Map;

public class BoardEvaluatorImpl implements BoardEvaluator {
    private final Map<Long,Evaluation> boardNeighborMap = new HashMap<>();
    private final Board.Color myColor;
    private MoveEvaluator moveEvaluator = new MoveEvaluator();

    public BoardEvaluatorImpl(Board.Color color) {
        myColor = color;
    }


    @Override
    public Evaluation evaluate(ZobristHashBoard board) {
        Evaluation evaluation = boardNeighborMap.get(board.getHash());
        if (evaluation==null) {
            int score = 0;
            for (PathNode pathNode : board.getTrace()) {
                Evaluation evaluate = moveEvaluator.evaluate(board, new Point(pathNode.getX(), pathNode.getY()));
                // 如果是己方
                if (pathNode.getColor() == myColor) {
                    if (evaluate == Evaluation.WIN) {
                        evaluation = Evaluation.WIN;
                        break;
                    }
                    score += evaluate.getScore();
                } else {  // 如果是敌方
                    if (evaluate == Evaluation.WIN) {
                        evaluation = Evaluation.LOSE;
                        break;
                    }
                    score -= evaluate.getScore();
                }
            }
            if (evaluation ==null) {
                evaluation = new Evaluation(Evaluation.PROGRESS_STATUS,score);
            }
            boardNeighborMap.put(board.getHash(),evaluation);
        }
        return evaluation;
    }

}
