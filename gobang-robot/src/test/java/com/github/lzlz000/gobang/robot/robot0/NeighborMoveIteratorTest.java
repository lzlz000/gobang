package com.github.lzlz000.gobang.robot.robot0;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.Point;
import com.github.lzlz000.gobang.robot.ZobristHashBoard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NeighborMoveIteratorTest {

    @Test
    public void test(){
        ZobristHashBoard board = new ZobristHashBoard(15);
        board.put(Board.Color.Black,new Point(3,3));
        board.put(Board.Color.Black,new Point(5,6));
        board.put(Board.Color.Black,new Point(6,6));
        fillNeighbor(board);
        System.out.println(board);

    }

    private void fillNeighbor(ZobristHashBoard board){
        NeighborMoveIterator iterator = new NeighborMoveIterator();
        Board.Color color = Board.Color.White;
        List<Point> nextSteps = iterator.next(board);
        for (Point nextStep : nextSteps) {
            System.out.println(nextStep);
            board.put(color,nextStep);
        }
    }

}