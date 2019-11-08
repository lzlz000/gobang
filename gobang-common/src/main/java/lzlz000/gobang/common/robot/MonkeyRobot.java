package lzlz000.gobang.common.robot;

import lzlz000.gobang.common.Board;
import lzlz000.gobang.common.GobangGame;
import lzlz000.gobang.common.Player;
import lzlz000.gobang.common.Point;

import java.util.Random;

/**
 * 下棋的策略为随机找一点 如果有空就下在这里 否则就依次向后搜索有任何空位就下下去
 */
public class MonkeyRobot implements GobangRobot {
    private GobangGame game;
    private Player player;

    @Override
    public String  name() {
        return "猴子机器人";
    }

    @Override
    public void start(GobangGame gobangGame, Player player) {
        this.game = gobangGame;
        this.player = player;
    }

    @Override
    public Point yourTurn(long restTime) {
        Board board = game.getBoard();
        int size = board.getSize();
        Random random = new Random();
        int ran = random.nextInt(size * size);
        //int index = x + y * size;
        int x = ran % size;
        int y = ran / size;
        while (board.get(x,y) != Board.BLANK){
            ran ++;
            if (ran >= size * size) {
                ran = 0;
            }
            x = ran % size;
            y = ran / size;
        }
        return new Point(x,y);
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
