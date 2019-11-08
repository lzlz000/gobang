package lzlz000.gobang.robot;

import lzlz000.gobang.common.Board;
import lzlz000.gobang.common.Point;

public class PointChecker {
    /*
     四个方向,约定编号
        2↘  3↓  4↙
             上
      1→  左  右
            下

     */
    PointScore[] score4 = new PointScore[4];
    public static void main(String[] args){
        System.out.println(0xFFFFFFFF);
    }

    void direction1(Point p, Board board){
        int x0 = p.getX();
        int y0 = p.getY();
        int x = Math.max(0,x0-4);
        int y = Math.max(0,y0-4);
    }


}
