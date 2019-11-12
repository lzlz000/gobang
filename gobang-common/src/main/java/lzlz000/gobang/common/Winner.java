package lzlz000.gobang.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lzlz000.gobang.common.game.Board;

@AllArgsConstructor
@Getter
public enum Winner {
    Black(Board.Color.Black),
    White(Board.Color.White),
    /**平局*/
    Draw(null);

    private final Board.Color color;

}
