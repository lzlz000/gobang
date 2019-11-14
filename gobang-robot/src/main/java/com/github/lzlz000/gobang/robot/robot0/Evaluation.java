package com.github.lzlz000.gobang.robot.robot0;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Evaluation {
    public static final int PROGRESS_STATUS = 0;
    public static final int WIN_STATUS = 1;
    public static final int LOSE_STATUS = 2;
    public static final Evaluation WIN = new Evaluation(WIN_STATUS,Integer.MAX_VALUE);
    public static final Evaluation LOSE = new Evaluation(LOSE_STATUS,Integer.MIN_VALUE);

    /** 是否结束 */
    private int finishStatus;

    private int score;
}
