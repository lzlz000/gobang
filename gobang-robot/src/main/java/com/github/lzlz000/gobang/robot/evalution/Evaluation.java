package com.github.lzlz000.gobang.robot.evalution;

import lombok.Data;

@Data
public class Evaluation {
    public static final int PROGRESS = 0;
    public static final int WIN = 1;
    public static final int LOSE = 2;

    /** 是否结束 */
    private int finishStatus;

    private int score;
}
