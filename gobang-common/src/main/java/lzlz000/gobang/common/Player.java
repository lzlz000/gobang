package lzlz000.gobang.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Player {
    Black(1),White(2),
    /**平局*/
    Draw(-1);

    private final int value;
}
