package lzlz000.gobang.common;


import org.junit.Test;

public class BoardTest {

    @Test
    public void test(){
        Board board = new Board(10);
        System.out.println(board);
        board.put(Player.Black,board.new Point(1,2));
        board.put(Player.White,board.new Point(0,1));
        System.out.println(board);
        board.cancel(1);
        System.out.println(board);
    }

}