package lzlz000.gobang.common.robot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lzlz000.gobang.common.PathNode;
import lzlz000.gobang.common.Winner;

import java.util.List;

@Data
@AllArgsConstructor
public class GameResult {
    String blackRobot;
    String whiteRobot;
    Winner winner;
    List<PathNode> trace;




}
