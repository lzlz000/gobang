package lzlz000.gobang.robot;

import lzlz000.gobang.common.GobangGame;
import lzlz000.gobang.common.Player;
import lzlz000.gobang.common.Point;

public interface GobangRobot {
    default String name(){
        return getClass().getSimpleName();
    }

    void start(GobangGame gobangGame,Player player);

    /**
     * 你的回合 restTime 是你的剩余时间（ms），它会计算从调用yourTurn到你给出consumer回掉的时间间隔
     */
    Point yourTurn(long restTime);

    Player getPlayer();

}
