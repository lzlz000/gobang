package lzlz000.gobang.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** 棋盘 */
public class Board {
    public static final int BLANK = 0;

    private final int size;
    private final int[] boardArr;
    private LinkedList<PathNode> trace = new LinkedList<>();

    public boolean isFull(){
        return trace.size() == size*size;
    }

    public Board(int size){
        this.size = size;
        boardArr = new int[size*size];
    }

    public boolean put(Player player,Point point){
        int x = point.getX();
        int y = point.getY();
        if (x < 0 || x >= size || y < 0 || y >= size){
            return false;
        }
        if (get(x, y)>0) {
            return false;
        }
        int color = player.getValue();
        set(x, y,color);
        addRecord(new PathNode(player, x, y, color));
        return true;
    }

    public int getSize() {
        return size;
    }

    /** 悔棋 */
    public void cancel(int step){
        for (int i = 0; i < step && !trace.isEmpty(); i++) {
            PathNode tail = trace.pop();
            set(tail.getX(),tail.getY(),BLANK);
        }
    }

    private void set(int x, int y, int val){
        int index = x + y * size;
        boardArr[index] = val;
    }

    public int get(int x, int y){
        int index = x + y * size;
        if ( index < 0 || index >= boardArr.length) {
            return BLANK;
        }
        return boardArr[index];
    }

    private void addRecord(PathNode pathNode){
        trace.push(pathNode);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < boardArr.length; i++) {
            int x = i % size;
            int y = i / size;
            if (x == 0) {
                builder.append("\n\n");
            }
            int val = boardArr[i];
            if (val == BLANK) {
                if (x == 0 && y == 0) {
                    builder.append("┌");
                } else if (x < size-1 && y == 0) {
                    builder.append("┬");
                } else if (x == size-1 && y == 0){
                    builder.append("┐");
                } else if (x == 0 && y < size - 1){
                    builder.append("├");
                } else if (x == 0 && y == size - 1){
                    builder.append("└");
                } else if (x < size-1 && y == size - 1){
                    builder.append("┴");
                } else if (x == size-1 && y == size - 1){
                    builder.append("┘");
                } else if (x == size-1 && y < size - 1){
                    builder.append("┤");
                } else {
                    builder.append("┼");
                }
            } else {
                if (val == Player.Black.getValue()) {
                    builder.append("●");
                } else {
                    builder.append("○");
                }
            }
            builder.append('\t');
        }
        return builder.toString();
    }

    public List<PathNode> getTrace() {
        return new ArrayList<>(trace); //防止机器人篡改数据
    }

    public int getSteps() {
        return trace.size();
    }

    public PathNode getLast(){
        if (trace.isEmpty()) {
            return null;
        }
        return trace.getLast();
    }

}
