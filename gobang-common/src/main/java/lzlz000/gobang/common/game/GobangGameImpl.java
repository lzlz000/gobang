package lzlz000.gobang.common.game;

import lombok.Getter;
import lzlz000.gobang.common.Winner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/** 一局游戏 */
@Getter
public class GobangGameImpl implements GobangGame {
    Logger log = LoggerFactory.getLogger(GobangGameImpl.class);
    private BoardImpl board;
    private final Consumer<Winner> gameOverHandler;

    private Board.Color activePlayer;

    private Winner winner = null;

    public GobangGameImpl(int size, Consumer<Winner> gameOverHandler){
        board = new BoardImpl(size);
        this.gameOverHandler = gameOverHandler;
        activePlayer = Board.Color.Black;
    }

    @Override
    public boolean move(Board.Color color, int x, int y) {
        if (color!=activePlayer || isGameOver()) {
            log.warn("不是"+color+"的回合！");
            return false;
        }
        if (!board.put(color, new Point(x, y))) {
            log.warn("不可落子的位置");
            return false;
        }
        Winner winner = checkGameOver(x, y);
        if (winner != null) {
            this.winner = winner;
//            log.info("游戏结束，胜者:"+winner);
            if (gameOverHandler != null) {
                gameOverHandler.accept(winner);
            }
            return true;
        }
        exchangePlayer();
        return true;
    }

    public boolean isGameOver(){
        return winner!=null;
    }

    @Override
    public void cancel(int step) {
        if (step>0) {
            if(isGameOver()){
                winner = null;
            }
            board.cancel(step);
        }
    }

    private void exchangePlayer(){
        activePlayer = Board.Color.exchange(activePlayer);
    }

    private Winner checkGameOver(int x0, int y0){
        if (board.isFull()) {
            winner = Winner.Draw; // 平局
        }
        final Board.Color color = board.get(x0, y0);
        if (color == Board.Color.Blank) {
            return null;
        }
        int val0 = color.getValue();
        // TODO 这种判断方式不适合有禁手的规则
        if (checkTopToBottom(x0,y0,val0) || checkLeftToRight(x0,y0,val0)
                ||  checkTopLeftToRightBottom(x0,y0,val0) || checkTopRightToLeftBottom(x0,y0,val0)) {
            return val0 == Board.Color.Black.getValue()?Winner.Black:Winner.White;
        }
        return null;
    }

    private boolean checkTopLeftToRightBottom(int x0, int y0,int val0){
        int x = x0;
        int y = y0;
        int val;
        int count = 0;
        while (x>=0 && y >=0){
            val = board.get(x,y).getValue();
            if (val!=val0) {
                break;
            }
            count++;
            x--;
            y--;
        }
        x = x0+1;
        y = y0+1;
        while (x< board.size() && y < board.size()){
            val = board.get(x,y).getValue();
            if (val!=val0) {
                break;
            }
            count++;
            x++;
            y++;
        }
        return count>=5;

    }

    private boolean checkTopRightToLeftBottom(int x0, int y0,int val0){
        int x = x0;
        int y = y0;
        int val;
        int count = 0;
        while (x>=0 && y < board.size()){
            val = board.get(x,y).getValue();
            if (val!=val0) {
                break;
            }
            count++;
            x--;
            y++;
        }
        x = x0+1;
        y = y0-1;
        while (x< board.size() && y >=0){
            val = board.get(x,y).getValue();
            if (val!=val0) {
                break;
            }
            count++;
            x++;
            y--;
        }
        return count>=5;

    }

    private boolean checkTopToBottom(int x0, int y0,int val0){
        int y = y0;
        int val;
        int count = 0;
        while (y >= 0){
            val = board.get(x0,y).getValue();
            if (val!=val0) {
                break;
            }
            count++;
            y--;
        }
        y = y0+1;
        while (y < board.size()){
            val = board.get(x0,y).getValue();
            if (val!=val0) {
                break;
            }
            count++;
            y++;
        }
        return count>=5;
    }

    private boolean checkLeftToRight(int x0, int y0,int val0){
        int x = x0;
        int val;
        int count = 0;
        while (x < board.size()){
            val = board.get(x,y0).getValue();
            if (val!=val0) {
                break;
            }
            count++;
            x++;
        }

        x = x0 - 1;
        while (x >= 0){
            val = board.get(x,y0).getValue();
            if (val!=val0) {
                break;
            }
            count++;
            x--;
        }
        return count>=5;
    }


}
