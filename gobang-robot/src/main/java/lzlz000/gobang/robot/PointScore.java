package lzlz000.gobang.robot;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PointScore {
    /*  xoox */
    Dead2(0),
    /*  xoo */
    Single2(1),
    /*  oo  */
    Live2(2),
    /*  o_o  */
    Skip2(2),
    /*  xooox  */
    Dead3(1),
    /*  xooo  */
    Single3(2),
    /*  ooo  */
    Live3(3),
    /*  oo_o or oo__o  */
    Skip3(3),
    /*  xoooox  */
    Dead4(1),
    /*  xoooo  */
    Single4(3),
    /*  oooo  */
    Live4(6),
    /*  ooooo  */
    Victory5(6);

    private int score;




}
