package lzlz000.gobang.common;


import lombok.Data;

@Data
public class PathNode {
    int x;
    int y;
    int val;
    PathNode prev;
    PathNode next;

    public PathNode(int x,int y,int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}
