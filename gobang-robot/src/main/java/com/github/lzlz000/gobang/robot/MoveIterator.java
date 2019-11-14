package com.github.lzlz000.gobang.robot;


import com.github.lzlz000.gobang.common.game.Point;

import java.util.List;

/**
 * 选择一个合适的的下一步
 */
public interface MoveIterator {

    /**
     * @return 所有可用的下一个点位
     */
    List<Point> next(ZobristHashBoard board);


}
