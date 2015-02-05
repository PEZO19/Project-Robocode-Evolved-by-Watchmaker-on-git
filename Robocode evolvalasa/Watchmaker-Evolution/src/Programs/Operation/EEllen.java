package wmevo.Programs.Operation;

import mappasrc.BeliefBase;
import wmevo.BinaryNode;
import wmevo.Node;

import java.util.Random;

/**
 * Created by Zoltán on 2014.05.13..
 */
public class EEllen extends BinaryNode{

    int parameter;

    public EEllen(Node left, Node right) {
        super(left, right);
        Random rng = new Random();
        parameter = rng.nextInt(120);
        symbol = "EEl[" + parameter + "]";
    }

    @Override
    public void evaluate(BeliefBase beliefBase) {
        System.out.println("if elott" + getClass().toString());
        System.out.println("Eellen: " + BeliefBase.Eellen);
        if(BeliefBase.Eellen <= parameter){
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
