package lzlz000.gobang.robot;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RobotDispatcherTest {

    @Test
    public void simpleRobotDispatcher() {
        RobotDispatcher robotDispatcher = new SimpleRobotDispatcher();
        robotDispatcher.startGame(new MonkeyRobot(),new MonkeyRobot());
    }

    @Test
    public void timeLimitRobotDispatcher() {
        RobotDispatcher robotDispatcher = new TimeLimitRobotDispatcher(5,TimeUnit.SECONDS);
        robotDispatcher.startGame(new MonkeyRobot(),new DelayMonkeyRobot());
    }


    @Test
    public void timeErr() {
        RobotDispatcher robotDispatcher = new TimeLimitRobotDispatcher(5,TimeUnit.SECONDS);
        robotDispatcher.startGame(new MonkeyRobot(),new ErrorRobot());
    }
}