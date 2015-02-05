package wmevo.Programs.Operation;

import mappasrc.BeliefBase;
import wmevo.BinaryNode;
import wmevo.Node;

import java.util.Random;

/**
 * Created by Zoltán on 2014.05.13..
 */
public class Heat extends BinaryNode {

    double parameter;

    public Heat(Node left, Node right) {
        super(left, right);
        Random random = new Random();
        double betweenNullAndOne = random.nextDouble();
        double scaled = betweenNullAndOne * 20;
        double shifted = scaled - 0;
        parameter = shifted;

        symbol = "Hea[" + parameter + "]";
    }

    @Override
    public void evaluate(BeliefBase beliefBase) {
        System.out.println("if elott");
        System.out.println("Heat: " + BeliefBase.heat);
        if(BeliefBase.heat <= parameter){
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
