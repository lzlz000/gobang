package lzlz000.gobang.common.robot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lzlz000.gobang.common.PathNode;
import lzlz000.gobang.common.Player;

import java.util.List;

@Data
@AllArgsConstructor
public class GameResult {
    String blackRobot;
    String whiteRobot;
    Player winner;
    List<PathNode> trace;




}
