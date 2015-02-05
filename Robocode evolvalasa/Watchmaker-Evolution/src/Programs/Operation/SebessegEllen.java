package wmevo.Programs.Operation;

import mappasrc.BeliefBase;
import wmevo.BinaryNode;
import wmevo.Node;

import java.util.Random;

/**
 * Created by Zoltán on 2014.05.13..
 */
public class SebessegEllen extends BinaryNode {

    double parameter;

    public SebessegEllen(Node left, Node right) {
        super(left, right);
        Random random = new Random();
        double betweenNullAndOne = random.nextDouble();
        double scaled = betweenNullAndOne * 8;
        double shifted = scaled - 0;
        parameter = shifted;

        symbol = "SeE[" + parameter + "]";
    }

    @Override
    public void evaluate(BeliefBase beliefBase) {
        System.out.println("if elott");
        System.out.println("SebessegEllen: " + BeliefBase.Vellen);
        if(BeliefBase.Vellen <= parameter){
            System.out.println("ifben");
            left.evaluate(beliefBase);
        }
        else{
            System.out.println("elseben");
            right.evaluate(beliefBase);
        }
    }

    @Override
    public Node simplify() {
        return null;
    }
}

