package lzlz000.gobang.common;

import lzlz000.gobang.common.game.Board;

/**
 * 裁判，判断谁是胜利者
 */
public interface Referee {
    Winner judge(Board board);
}
