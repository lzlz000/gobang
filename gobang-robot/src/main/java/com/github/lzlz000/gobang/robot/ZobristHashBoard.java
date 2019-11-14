package com.github.lzlz000.gobang.robot;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.BoardImpl;
import com.github.lzlz000.gobang.common.game.PathNode;
import com.github.lzlz000.gobang.common.game.Point;

import java.util.List;
import java.util.Random;

/**
 *  Zobrist Hash算法缓存棋局
 *  对于棋局来说，我们并不关心你是如何到达这个局面的，只要当前局面上的棋子一样，局势就是一样的
 *  通过一个hash值来代表当前棋局，
 */
public class ZobristHashBoard extends BoardImpl implements Board {
    private final long[] blackArr; // 黑棋的随机数组
    private final long[] whiteArr; // 白棋的随机数组
    private long hash = 0;

    public ZobristHashBoard(int size) {
        super(size);
        blackArr = new long[boardArr.length];
        whiteArr = new long[boardArr.length];
        Random random = new Random();
        for (int i = 0; i < boardArr.length; i++) {
            blackArr[i] = random.nextLong();
            whiteArr[i] = random.nextLong();
        }
    }

    @Override
    public boolean put(Color color, Point point) {
        int index = point.getIndex(size());
        hash ^= color ==Color.Black? blackArr[index]:whiteArr[index];
        return super.put(color, point);
    }

    @Override
    public void cancel(int step) {
        for (int i = 0; i < step && i< trace.size(); i++) {
            PathNode pathNode = trace.get(i);
            int index = Point.index(pathNode.getX(), pathNode.getY(), size());
            if (pathNode.getColor()==Color.Black) {
                hash ^= blackArr[index];
            }
            if (pathNode.getColor()==Color.White) {
                hash ^= whiteArr[index];
            }
        }
        super.cancel(step);
    }

    public long getHash() {
        return hash;
    }

    @Override
    public List<PathNode> getTrace() {
        return trace; // 这个棋盘就是给机器人使用的 不需要复制对局
    }
}
