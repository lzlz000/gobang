package lzlz000.gobang.common;

import lombok.Getter;

/** 棋盘 */
public class Board {
    public static final int BLANK = 0;

    private final int size;
    private final int[] boardArr;
    private PathNode head;
    private PathNode tail;
    private int pieceCount = 0;
    public boolean isFull(){
        return pieceCount == size*size;
    }

    public Board(int size){
        this.size = size;
        boardArr = new int[size*size];
    }

    public boolean put(Player player,Point point){
        if (point.getX() >= size || point.getY() >= size){
            throw new IllegalArgumentException("out of the board size");
        }
        if (get(point.getX(),point.getY())>0) {
            return false;
        }
        int color = player.getValue();
        set(point.getX(),point.getY(),color);
        addRecord(new PathNode(point.getX(), point.getY(), color));
        pieceCount++;
        return true;
    }

    public int getSize() {
        return size;
    }

    /** 悔棋 */
    public void cancel(int step){
        for (int i = 0; i < step && tail!=null; i++) {
            pieceCount --;
            set(tail.getX(),tail.getY(),BLANK);
            tail = tail.getPrev();
            tail.setNext(null);
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
        if (head == null) {
            head = pathNode;
            tail = pathNode;
        }else {
            pathNode.setPrev(tail);
            tail.setNext(pathNode);
            tail = pathNode;
        }
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
}
