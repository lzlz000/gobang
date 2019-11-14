package com.github.lzlz000.gobang.robot;


import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.Point;

public interface MoveIterator {

    /**
     * 生成一个合法的下一步
     */
    Point next(Board board);

}
