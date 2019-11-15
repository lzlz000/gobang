package com.github.lzlz000.gobang.robot.robot0;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.PathNode;
import com.github.lzlz000.gobang.common.game.Point;
import com.github.lzlz000.gobang.robot.MoveIterator;
import com.github.lzlz000.gobang.robot.ZobristHashBoard;

import java.util.*;

public class NeighborMoveIterator implements MoveIterator {
    // 存储当前棋局和可用的下一节点的集合
    private final Map<Long,List<Point>> boardNeighborMap = new HashMap<>();

    /**
     * @param board  确保一场对局中传入的board是同一个对象的引用，
     *               不同{@link ZobristHashBoard}对象对于相同对局的hash值是不同的
     */
    @Override
    public List<Point> next(ZobristHashBoard board) {
        long hash = board.getHash();
        int size = board.size();
        int pieceCount = board.getTrace().size();
        if (pieceCount ==0) { // 如果是刚开局
            return Collections.singletonList(new Point(size/2,size/2)); // 没有子默认下中间
        }
        List<Point> points = boardNeighborMap.get(hash);
        // 如果没有缓存当前棋局，则遍历有子节点，获取范围内的点位
        if (points==null) {
            // 把有棋子的格子的两格以内纳入迭代范围
            points = generateNextStepCollection(board);
            boardNeighborMap.put(hash,points);
        }
        return points;
    }

    private List<Point> generateNextStepCollection(Board board){
        Set<Point> set = new LinkedHashSet<>();
        int size = board.size();
        // BoardImpl的trace是从最后一个插入的值向前迭代的 这样先加入迭代集合的点就是靠近最后落子的点位，
        // 实际上选择这些点位的可能性更高 后续博弈树搜索时利于剪枝
        int range = board.getTrace().size()>1?2:1; //只有一个子的时候只在周边搜索最有利
        for (PathNode pathNode : board.getTrace()) {
            int x = pathNode.getX();
            int y = pathNode.getY();
            int xMin = Math.max(x - range, 0);
            int xMax = Math.min(x + range, size - 1);
            int yMin = Math.max(y - range, 0);
            int yMax = Math.min(y + range, size - 1);
            List<Point> randonList = new ArrayList<>();
            for (int x1 = xMin; x1 <= xMax ; x1++) {
                for (int y1 = yMin; y1 <= yMax; y1++) {
                    if (board.get(x1,y1) == Board.Color.Blank) {
                        randonList.add(new Point(x1,y1));
                    }
                }
            }
            // 每个节点的周围格子是等价的 随机的加入
            Random random = new Random();
            for (int i = randonList.size(); i >0; i--) {
                set.add(randonList.remove(random.nextInt(i)));
            }
        }
        return new ArrayList<>(set);
    }

}
