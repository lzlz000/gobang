package com.github.lzlz000.gobang.common.robot;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.BoardImpl;
import com.github.lzlz000.gobang.common.game.GobangGame;
import com.github.lzlz000.gobang.common.game.Point;

import java.util.Random;

/**
 * 下棋的策略为随机找一点 如果有空就下在这里 否则就依次向后搜索有任何空位就下下去
 */
public class MonkeyRobot implements GobangRobot {
    private GobangGame game;
    private Board.Color player;

    @Override
    public String  name() {
        return "猴子机器人";
    }

    @Override
    public void start(GobangGame gobangGame, Board.Color player) {
        this.game = gobangGame;
        this.player = player;
    }

    @Override
    public Point yourTurn(long restTime) {
        BoardImpl board = game.getBoard();
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
        return new Point(x,y);
    }

    @Override
    public Board.Color getPlayer() {
        return player;
    }
}
