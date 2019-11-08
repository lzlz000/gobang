package lzlz000.gobang.robot.evalution;

import org.junit.Assert;
import org.junit.Test;

public class LineEvaluationTest {
    @Test
    public void test(){
        LineEvaluation evalution = new LineEvaluation();
        Line line = new Line();
        line.addEmpty();
        line.addMine();
        line.addMine();
        line.addMine();
        line.addEmpty();
        int score = evalution.getScore(line); // 01110 分数为2
        Assert.assertEquals(LineEvaluation.scores[1],score);
        line.addEmpty();
        score = evalution.getScore(line);
        Assert.assertEquals(LineEvaluation.scores[2],score);
        line.addEmpty();
        line.addEmpty();
        score = evalution.getScore(line);
        Assert.assertEquals(LineEvaluation.scores[2],score);
        line = new Line();
        line.addEmpty();
        line.addMine();
        line.addMine();
        line.addMine();
        line.addMine();
        Assert.assertEquals(LineEvaluation.scores[2],evalution.getScore(line));  // 01111 分数4
        line.addMine();
        Assert.assertEquals(LineEvaluation.scores[3],evalution.getScore(line)); // 011111 分数8
    }
}