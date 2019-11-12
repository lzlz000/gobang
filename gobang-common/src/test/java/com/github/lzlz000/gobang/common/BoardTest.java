package com.github.lzlz000.gobang.common;


import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.BoardImpl;
import com.github.lzlz000.gobang.common.game.Point;
import org.junit.Test;

public class BoardTest {

    @Test
    public void test(){
        BoardImpl board = new BoardImpl(10);
        System.out.println(board);
        board.put(Board.Color.Black, new Point(1,2));
        board.put(Board.Color.White, new Point(0,1));
        System.out.println(board);
        board.cancel(1);
        System.out.println(board);
    }

}