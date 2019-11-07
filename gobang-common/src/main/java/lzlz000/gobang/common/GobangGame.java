package lzlz000.gobang.common;


public interface GobangGame {
    /**
     * 下棋落子
     * @param player 玩家
     * @param x 位置
     * @param y 位置
     * @return <tt>false</tt> 下棋失败,可能是不是你的回合 or 位置已经被占 or 游戏已经结束
     */
    boolean move(Player player,int x ,int y);

    /**
     * 是否游戏结束
     */
    default boolean isGameOver(){
        return getWinner()!=null;
    }
    /**
     * 获胜者
     */
    Player getWinner();

    int getBoardVal(int x, int y);

    /**
     * 悔棋
     * @param step 悔棋总步数 >0
     */
    void cancel(int step);

    Board getBoard();

}
