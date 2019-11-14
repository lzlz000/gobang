package com.github.lzlz000.gobang.common.robot;

import com.github.lzlz000.gobang.common.game.*;
import com.github.lzlz000.gobang.common.game.referee.SimpleGameReferee;

import java.util.Random;

/**
 * 下棋的策略为随机找一点 如果有空就下在这里 否则就依次向后搜索有任何空位就下下去
 */
public class MonkeyRobot implements GobangRobot {
    private GobangGame game;
    private Board.Color mine;
    private Board.Color oppo;

    @Override
    public String  name() {
        return "猴子机器人";
    }

    @Override
    public void start(int color, int boardSize) {
        this.game = new GobangGameImpl(boardSize,new SimpleGameReferee(),null);
        this.mine = Board.Color.valueOf(color);
        this.oppo = Board.Color.exchange(this.mine);
    }

    @Override
    public Point yourTurn(int x0,int y0) {
        if (x0>=0 && y0>=0) {
            game.move(oppo,x0,y0);
        }
        Board board = game.getBoard();
        int size = board.size();
        Random random = new Random();
        int ran = random.nextInt(size * size);
        //int index = x + y * size;
        int x = ran % size;
        int y = ran / size;
        while (board.get(x,y) != Board.Color.Blank){
            ran ++;
            if (ran >= size * size) {
                ran = 0;
            }
            x = ran % size;
            y = ran / size;
        }
        game.move(mine,x,y);
        return new Point(x,y);
    }

    @Override
    public int getMyColor() {
        return mine.getValue();
    }
}
