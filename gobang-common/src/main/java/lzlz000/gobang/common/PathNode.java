package lzlz000.gobang.common;


import lombok.Data;
import lombok.Getter;

@Getter
public class PathNode {
    private final int x;
    private final int y;
    private final int  val;

    public PathNode(int x,int y,int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}
