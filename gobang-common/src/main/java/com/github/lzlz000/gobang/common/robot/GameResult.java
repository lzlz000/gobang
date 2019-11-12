package com.github.lzlz000.gobang.common.robot;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.github.lzlz000.gobang.common.game.PathNode;
import com.github.lzlz000.gobang.common.game.Winner;

import java.util.List;

@Data
@AllArgsConstructor
public class GameResult {
    String blackRobot;
    String whiteRobot;
    Winner winner;
    List<PathNode> trace;




}
