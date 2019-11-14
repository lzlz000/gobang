package com.github.lzlz000.gobang.common.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Point {
    private int x;
    private int y;
    public int getIndex(int size){
        return x + y * size;
    }

    public static int index(int x, int y, int size){
        return x + y * size;
    }

    public static Point pointOfIndex(int size, int index){
        if (index >= size * size) {
            throw new IllegalArgumentException();
        }
        return new Point(index%size,index/size);
    }
}
