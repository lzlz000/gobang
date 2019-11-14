package com.github.lzlz000.gobang.robot.robot0;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.robot.RobotDispatcher;
import com.github.lzlz000.gobang.common.robot.SimpleRobotDispatcher;


public class Robot0Test {
    public static void main(String[] args){
        Robot0 robot0 = new Robot0();
        Robot0 robot1 = new Robot0();
        robot0.start(Board.Color.Black.getValue(),15);
        RobotDispatcher robotDispatcher = new SimpleRobotDispatcher();
        robotDispatcher.startGame(robot0,robot1);

    }

}