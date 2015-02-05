package wmevo.Programs.Operation;

import mappasrc.BeliefBase;
import wmevo.BinaryNode;
import wmevo.Node;

import java.util.Random;

/**
 * Created by Zoltán on 2014.05.13..
 */
//delta_d
public class Kozeledes extends BinaryNode {

    double parameter;

    public Kozeledes(Node left, Node right) {
        super(left, right);
        Random random = new Random();
        double betweenNullAndOne = random.nextDouble();
        double scaled = betweenNullAndOne * 16;
        double shifted = scaled - 8;
        parameter = shifted;

        symbol = "Koz[" + parameter + "]";
    }

    @Override
    public void evaluate(BeliefBase beliefBase) {
        System.out.println("if elott");
        System.out.println("Kozeledes: " + BeliefBase.delta_d);
        if(BeliefBase.delta_d <= parameter){
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
