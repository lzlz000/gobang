package lzlz000.gobang.common;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/** 一局游戏 */
@Getter
public class GobangGameImpl implements GobangGame {
    Logger log = LoggerFactory.getLogger(GobangGameImpl.class);
    private Board board;
    private final Consumer<Player> gameOverHandler;

//    private Player black;
//
//    private Player white;

    private Player activePlayer;

    private Player winner = null;

    public GobangGameImpl(int size, Consumer<Player> gameOverHandler){
        board = new Board(size);
        this.gameOverHandler = gameOverHandler;
//        black = Player.Black;
//        white = Player.White;
        activePlayer = Player.Black;
    }

    public boolean move(Player player,int x ,int y){
        if (player!=activePlayer || isGameOver()) {
            log.warn("不是"+player+"的回合！");
//            System.out.println("不是"+player+"的回合！");
            return false;
        }
        if (!board.put(player, new Point(x, y))) {
            log.warn("不可落子的位置");
//            System.out.println("不可落子的位置");
            return false;
        }
        Player winner = checkGameOver(x, y);
        if (winner != null) {
            this.winner = winner;
            log.info("游戏结束，胜者:"+winner);
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
    public int getBoardVal(int x, int y) {
        return board.get(x,y);
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
        activePlayer = activePlayer==Player.Black?Player.White:Player.Black;
    }

    private Player checkGameOver(int x0, int y0){
        if (board.isFull()) {
            winner = Player.Draw; // 平局
        }
        final int val0 = board.get(x0, y0);
        if (val0 == Board.BLANK) {
            return null;
        }
        // TODO 这种判断方式不适合有禁手的规则
        if (checkTopToBottom(x0,y0,val0) || checkLeftToRight(x0,y0,val0)
                ||  checkTopLeftToRightBottom(x0,y0,val0) || checkTopRightToLeftBottom(x0,y0,val0)) {
            return val0 == Player.Black.getValue()?Player.Black:Player.White;
        }
        return null;
    }

    private boolean checkTopLeftToRightBottom(int x0, int y0,int val0){
        int x = x0;
        int y = y0;
        int val;
        int count = 0;
        while (x>=0 && y >=0){
            val = board.get(x,y);
            if (val!=val0) {
                break;
            }
            count++;
            x--;
            y--;
        }
        x = x0+1;
        y = y0+1;
        while (x< board.getSize() && y < board.getSize()){
            val = board.get(x,y);
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
        while (x>=0 && y < board.getSize()){
            val = board.get(x,y);
            if (val!=val0) {
                break;
            }
            count++;
            x--;
            y++;
        }
        x = x0+1;
        y = y0-1;
        while (x< board.getSize() && y >=0){
            val = board.get(x,y);
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
            val = board.get(x0,y);
            if (val!=val0) {
                break;
            }
            count++;
            y--;
        }
        y = y0+1;
        while (y < board.getSize()){
            val = board.get(x0,y);
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
        while (x < board.getSize()){
            val = board.get(x,y0);
            if (val!=val0) {
                break;
            }
            count++;
            x++;
        }

        x = x0 - 1;
        while (x >= 0){
            val = board.get(x,y0);
            if (val!=val0) {
                break;
            }
            count++;
            x--;
        }
        return count>=5;
    }


}
