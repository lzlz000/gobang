package com.github.lzlz000.gobang.robot;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.PathNode;
import com.github.lzlz000.gobang.common.game.Point;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZobristHashBoardTest {
    @Test
    public void test(){
        ZobristHashBoard board = new ZobristHashBoard(15);
        board.put(Board.Color.Black,new Point(2,3));
        board.put(Board.Color.White,new Point(3,3));
        long hash1 = board.getHash();
        System.out.println(hash1);
        board.put(Board.Color.Black,new Point(2,4));
        board.put(Board.Color.White,new Point(3,4));
        long hash2 = board.getHash();
        System.out.println(hash2);
        Assert.assertNotEquals(hash2,hash1);
        board.cancel(2);
        long hash3 = board.getHash();
        Assert.assertEquals(hash1,hash3);
        // 落子顺序不影响hash值
        board.put(Board.Color.White,new Point(3,4));
        board.put(Board.Color.Black,new Point(2,4));
        Assert.assertEquals(hash1,hash3);
        for (PathNode pathNode : board.getTrace()) {
            System.out.println("x:"+pathNode.getX()+":y"+pathNode.getY());
        }

    }

}