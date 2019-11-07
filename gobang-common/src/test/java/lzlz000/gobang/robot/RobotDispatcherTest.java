package lzlz000.gobang.robot;

import org.junit.Test;

import static org.junit.Assert.*;

public class RobotDispatcherTest {

    @Test
    public void start() {
        RobotDispatcher robotDispatcher = new RobotDispatcher();
        robotDispatcher.start(new MonkeyRobot(),new MonkeyRobot());
    }
}