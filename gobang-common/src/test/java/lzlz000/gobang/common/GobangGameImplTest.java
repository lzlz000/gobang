package lzlz000.gobang.common;

import org.junit.Test;


public class GobangGameImplTest {
    @Test
    public void testLeftRight(){
        GobangGameImpl game = new GobangGameImpl(11,null);
        Board.Color black = Board.Color.Black;
        Board.Color white = Board.Color.White;
        game.move(black,1,2);
        game.move(black,1,2); // 不是BlackPlayer的回合！
        game.move(white,1,2); // 不可落子的位置
        game.move(white,1,0);
        game.move(black,2,2);
        game.move(white,2,0);
        game.move(black,3,2);
        game.move(white,3,0);
        game.move(black,4,2);
        game.move(white,4,0);
        game.move(black,5,2);
        System.out.println(game.getBoard());

    }

    @Test
    public void testTopBottom(){
        GobangGameImpl game = new GobangGameImpl(11,null);
        Board.Color black = Board.Color.Black;
        Board.Color white = Board.Color.White;
        game.move(black,3,2);
        game.move(white,1,0);
        game.move(black,3,3);
        game.move(white,2,0);
        game.move(black,3,4);
        game.move(white,3,0);
        game.move(black,3,5);
        game.move(white,4,0);
        game.move(black,3,6);
        System.out.println(game.getBoard());
    }


    @Test
    public void testTopLeft2RightBottom(){
        GobangGameImpl game = new GobangGameImpl(11,null);
        Board.Color black = Board.Color.Black;
        Board.Color white = Board.Color.White;
        game.move(black,2,2);
        game.move(white,1,0);
        game.move(black,3,3);
        game.move(white,2,0);
        game.move(black,4,4);
        game.move(white,3,0);
        game.move(black,5,5);
        game.move(white,4,0);
        game.move(black,6,6);
        System.out.println(game.getBoard());
    }

    @Test
    public void testTopRight2LeftBottom(){
        GobangGameImpl game = new GobangGameImpl(11,null);
        Board.Color black = Board.Color.Black;
        Board.Color white = Board.Color.White;
        game.move(black,2,6);
        game.move(white,1,0);
        game.move(black,3,5);
        game.move(white,2,0);
        game.move(black,4,4);
        game.move(white,3,0);
        game.move(black,5,3);
        game.move(white,4,0);
        game.move(black,6,2);
        System.out.println(game.getBoard());
    }

}