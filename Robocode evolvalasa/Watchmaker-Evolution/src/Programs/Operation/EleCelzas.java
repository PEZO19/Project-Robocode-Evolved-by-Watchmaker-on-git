package wmevo.Programs.Operation;

import mappasrc.BeliefBase;
import wmevo.BinaryNode;
import wmevo.Node;

import java.util.Random;

/**
 * Created by Zoltán on 2014.05.13..
 */

//v_per_d
public class EleCelzas extends BinaryNode {

    double parameter;

    public EleCelzas(Node left, Node right) {
        super(left, right);
        Random random = new Random();
        double betweenNullAndOne = random.nextDouble(); //0-1
        double scaled = betweenNullAndOne * 0.5; //0-0.5
        double shifted = scaled - 0;        //0-0.5
        parameter = shifted;

        symbol = "ElC[" + parameter + "]";
    }

    @Override
    public void evaluate(BeliefBase beliefBase) {
        System.out.println("if elott" + getClass().toString());
        System.out.println("EleCelzas: " + BeliefBase.v_per_d);
        if(BeliefBase.v_per_d <= parameter){
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
