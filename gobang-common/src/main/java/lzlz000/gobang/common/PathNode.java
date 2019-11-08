package lzlz000.gobang.common;


import lombok.Getter;

@Getter
public class PathNode {
    private final Player player;
    private final int x;
    private final int y;
    private final int  val;

    public PathNode(Player player, int x, int y, int val){
        this.player = player;
        this.x = x;
        this.y = y;
        this.val = val;
    }
}
