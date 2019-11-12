package lzlz000.gobang.common.robot;

import lzlz000.gobang.common.Board;
import lzlz000.gobang.common.GobangGame;
import lzlz000.gobang.common.Winner;
import lzlz000.gobang.common.Point;

public interface GobangRobot {
    default String name(){
        return getClass().getSimpleName();
    }

    void start(GobangGame gobangGame,Board.Color player);

    /**
     * 你的回合 restTime 是你的剩余时间（ms）,如果剩余时间小于等于0 调度器会判负
     * @return null 代表认输
     */
    Point yourTurn(long restTime);

    Board.Color getPlayer();

}
