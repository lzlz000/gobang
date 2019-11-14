package com.github.lzlz000.gobang.robot.robot0;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class LineEvaluation {
    static final int[] scores = {1,2,10,100,1000,10000,1000000};
    static final int Win = scores[5];
    
    static class Node {
        private int val;
        //最多只有可能有三个子节点:空 我的 对手的
        private Node empty;
        private Node mine;

        Node(int val) {
            this.val = val;
        }

        Node addNext(int piece, int val){
            switch (piece) {
                case Line.EMPTY:
                    if (empty == null) {
                        empty = new Node(val);
                    }else if (val>0){
                        empty.val=val;
                    }
                    return empty;
                case Line.MINE:
                    if (mine == null) {
                        mine = new Node(val);
                    } else if (val>0){
                        mine.val=val;
                    }
                    return mine;
                default:
                    return null;
            }
        }
    }

    private final Node root = new Node(-1);

    LineEvaluation(){
        Map<String,Integer> lineScoreMap = new TreeMap<>();
        // 一条线上的状态截取的截止状态为遇到： >2个0  对手棋子 边界
        // 注意这里分数的确定，以离确保胜利的步数作为分数的给定标准
        // 离胜利少一步是多一步的两倍，因为棋盘总共有四条线，分数可以叠加
        // 最典型的 “活三”即 01110 如果形成两个方向的“双三” 实际上已经同于确保胜利,因此双三的分数和等于胜利
        // 约定0代表1个空位  1代表我方棋子
        lineScoreMap.put("001",scores[0]);
        lineScoreMap.put("100",scores[0]);
        lineScoreMap.put("00100",scores[0]);
        lineScoreMap.put("01100",scores[2]);
        lineScoreMap.put("00110",scores[2]);
        // 离确保胜利还差两步
        lineScoreMap.put("11010",scores[3]);
        lineScoreMap.put("01011",scores[3]);
        // 大多数情况下需要保持对称 但是右侧的0如果是计分所不在乎的可以忽略
        // 匹配时的逻辑，举个栗子：
        // 模式存在为 a:001011 b:0010110
        // 输入串为 001011 00101121211 匹配a  0010110 匹配b，即当串匹配到尾端或者匹配不到合适的模式时结束
        // 左侧不忽略而只忽略右侧是为了方便构件搜索树
        lineScoreMap.put("001011",scores[3]);
        lineScoreMap.put("11100",scores[3]);
        lineScoreMap.put("01110",scores[3]);
        lineScoreMap.put("00111",scores[3]);
        //离确保胜利还差一步
        // 注意上文已经定义了边界条件是 >2个0  对手棋子 遇到边界，因此“活三”需要分情况讨论 而 x01110x实际上不属于“活三”
        lineScoreMap.put("011100",scores[4]);
        lineScoreMap.put("001110",scores[4]);
        lineScoreMap.put("010110",scores[4]);
        lineScoreMap.put("0010110",scores[4]);
        lineScoreMap.put("011010",scores[4]);
        lineScoreMap.put("0011010",scores[4]);
        lineScoreMap.put("01111",scores[4]);
        lineScoreMap.put("001111",scores[4]);
        lineScoreMap.put("10111",scores[4]);
        lineScoreMap.put("010111",scores[4]);
        lineScoreMap.put("0010111",scores[4]);
        lineScoreMap.put("11011",scores[4]);
        lineScoreMap.put("011011",scores[4]);
        lineScoreMap.put("0011011",scores[4]);
        lineScoreMap.put("11101",scores[4]);
        lineScoreMap.put("011101",scores[4]);
        lineScoreMap.put("0011101",scores[4]);
        lineScoreMap.put("11110",scores[4]);
        lineScoreMap.put("011110",scores[5]);
        lineScoreMap.put("0011110",scores[5]);
        lineScoreMap.put("11111",scores[6]);
        lineScoreMap.put("011111",scores[6]);
        lineScoreMap.put("0011111",scores[6]);
        // 链模式匹配是算法中及其高频的操作 与map中的字符串比较还是太过低效 构件一颗搜索树
        // 之所以不直接构件树是因为字符串更加直观 方便调整参数
        buildPatternTree(lineScoreMap,root);
    }

    int getScore(Line line){
        List<Integer> list = line.getLine();
        int score = 0;
        Node node = root;
        a:for (int p : list) {
            switch (p) {
                case Line.EMPTY:
                    if (node.empty == null) {
                        break a;
                    }
                    score = node.empty.val;
                    node = node.empty;
                    break;
                case Line.MINE:
                    if (node.mine == null) {
                        break a;
                    }
                    score = node.mine.val;
                    node = node.mine;
                    break;
                default:
                    throw new IllegalArgumentException("Line中非法的值:"+p);
            }
        }
        return score;
    }

    private void buildPatternTree(Map<String,Integer> lineScoreMap,Node root){
        for (Map.Entry<String, Integer> entry : lineScoreMap.entrySet()) {
            String linePattern = entry.getKey();
            byte[] bytes = linePattern.getBytes();
            Node node = root;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                int val = i == bytes.length - 1?entry.getValue():0;
                switch (b) {
                    case '0':
                        node = node.addNext(Line.EMPTY, val);
                        break;
                    case '1':
                        node =  node.addNext(Line.MINE, val);
                        break;
                }
            }
        }
    }
}
