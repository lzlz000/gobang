package com.github.lzlz000.gobang.common.game.referee;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.WinnerMessage;

/**
 * 无禁手的简单规则裁判
 */
public class SimpleGameReferee extends BaseGobangReferee implements GobangReferee {
    @Override
    public WinnerMessage judge(Board board) {
        return judge(board,false);
    }
}
