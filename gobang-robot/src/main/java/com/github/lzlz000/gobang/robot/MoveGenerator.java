package com.github.lzlz000.gobang.robot;


import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.Point;

public interface MoveGenerator {

    /**
     * 评估局势生成下一步棋
     */
    Point get(ZobristHashBoard board);

}
