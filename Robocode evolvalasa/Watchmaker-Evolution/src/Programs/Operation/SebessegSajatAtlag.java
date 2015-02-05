package wmevo.Programs.Operation;

import mappasrc.BeliefBase;
import wmevo.BinaryNode;
import wmevo.Node;

import java.util.Random;

/**
 * Created by Zoltán on 2014.05.13..
 */
public class SebessegSajatAtlag extends BinaryNode {

    double parameter;

    public SebessegSajatAtlag(Node left, Node right) {
        super(left, right);
        Random random = new Random();
        double betweenNullAndOne = random.nextDouble();
        double scaled = betweenNullAndOne * 8;
        double shifted = scaled - 0;
        parameter = shifted;

        symbol = "SSA[" + parameter + "]";
    }

    @Override
    public void evaluate(BeliefBase beliefBase) {
        System.out.println("if elott");
        System.out.println("SebessegSajatAtlag: " + BeliefBase.fifok.get("_Vsajat_atlag").avg);
        if(BeliefBase.fifok.get("_Vsajat_atlag").avg <= parameter){
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
