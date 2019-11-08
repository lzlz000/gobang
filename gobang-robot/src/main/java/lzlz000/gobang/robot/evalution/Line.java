package lzlz000.gobang.robot.evalution;

import java.util.LinkedList;
import java.util.List;

class Line {
    static final int EMPTY = 0;
    static final int MINE = 1;
    private LinkedList<Integer> line = new LinkedList<>();


    void leftAddEmpty(){
        line.addFirst(EMPTY);
    }

    void addEmpty(){
        line.add(EMPTY);
    }

    void leftAddMine(){
        line.addFirst(MINE);
    }
    void addMine(){
        line.add(MINE);
    }

    List<Integer> getLine() {
        return line;
    }
}
