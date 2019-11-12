package com.github.lzlz000.gobang.common.game.referee;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.game.WinnerMessage;

/**
 * 裁判，判断谁是胜利者
 */
public interface GobangReferee {

    /**
     * 判断对局的胜利者
     * @return <tt>null</tt> 表示暂时无人获胜
     */
    WinnerMessage judge(Board board);
}
