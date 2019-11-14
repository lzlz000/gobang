package com.github.lzlz000.gobang.common.robot;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.BoardImpl;
import com.github.lzlz000.gobang.common.game.Point;

import java.util.Scanner;

public class ConsoleHum implements GobangRobot {
    private Board.Color myColor;
    private Board.Color anotherColor;
    private final Scanner scanner = new Scanner(System.in);
    private Board board;

    @Override
    public void start(int color, int boardSize) {
        this.myColor = Board.Color.valueOf(color);
        this.anotherColor = Board.Color.exchange(myColor);
        board = new BoardImpl(boardSize);
    }

    @Override
    public Point yourTurn(int x, int y) {
        String next;
        if (x>=0 && y>=0) {
            board.put(anotherColor,new Point(x,y));
        }
        for (;;){
            System.out.println(board);
            System.out.println("请输入 x,y");
            next = scanner.next();
            try {
                String[] split = next.split(",");
                int x1 = Integer.valueOf(split[0]);
                int y1 = Integer.valueOf(split[1]);
                Point point = new Point(x1, y1);
                board.put(myColor,point);
                return point;
            }catch (Exception ignore){
                System.out.println("非法的输入:"+next);
            }
        }
    }

    @Override
    public int getMyColor() {
        return myColor.getValue();
    }
}
