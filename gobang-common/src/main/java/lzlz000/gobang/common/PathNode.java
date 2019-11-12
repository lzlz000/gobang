package lzlz000.gobang.common;

import lombok.Getter;

@Getter
public class PathNode {
    private final Board.Color color;
    private final int x;
    private final int y;

    public PathNode(Board.Color color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }
}
