package com.github.lzlz000.gobang.robot.robot0;

import com.github.lzlz000.gobang.common.game.Board;
import com.github.lzlz000.gobang.common.robot.ConsoleHum;
import com.github.lzlz000.gobang.common.robot.RobotDispatcher;
import com.github.lzlz000.gobang.common.robot.SimpleRobotDispatcher;
import org.junit.Test;


public class Robot0Test {
    @Test
    public void test(){
        Robot0 robot0 = new Robot0();
        Robot0 robot1 = new Robot0();
        robot0.start(Board.Color.Black.getValue(),15);
        robot1.start(Board.Color.White.getValue(),15);
        RobotDispatcher robotDispatcher = new SimpleRobotDispatcher();
        robotDispatcher.startGame(robot0,robot1);

    }
    public static void main(String[] args){
        ConsoleHum hum = new ConsoleHum();
        Robot0 robot = new Robot0();
        hum.start(Board.Color.Black.getValue(),15);
        robot.start(Board.Color.White.getValue(),15);
        RobotDispatcher robotDispatcher = new SimpleRobotDispatcher();
        robotDispatcher.startGame(hum,robot);
    }
}