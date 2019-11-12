package com.github.lzlz000.gobang.common.robot;

import com.github.lzlz000.gobang.common.game.Point;

public class DelayMonkeyRobot extends MonkeyRobot {

    @Override
    public String name() {
        return "迟钝的猴子机器人";
    }

    @Override
    public Point yourTurn(long restTime) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.yourTurn(restTime);
    }
}
