package wmevo.Programs.Operation;

import mappasrc.BeliefBase;
import wmevo.BinaryNode;
import wmevo.Node;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.28..
 */
public class LottSzamSajat extends BinaryNode {

    int parameter;

    public LottSzamSajat(Node left, Node right) {
        super(left, right);
        Random rng = new Random();
        parameter = rng.nextInt(2);
        symbol = "Lot[" + parameter + "]";
        //System.out.println("LottSzamSajat szuletett. Paramatere: " + parameter);
    }

    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any
     * child nodes) and returns a numeric value. //TODO: boolt ad vissza nálunk
     *
     * @param actionParameter@return The result of evaluating this node and all of its children.
     */
    @Override
    public void evaluate(BeliefBase beliefBase) {
        System.out.println("if elott" + getClass().toString());
        System.out.println("LOTT DB SAJAT: " + BeliefBase.get_LOTT_DB_SAJAT());
        if(BeliefBase.get_LOTT_DB_SAJAT() <= parameter){
            System.out.println("ifben");
            left.evaluate(beliefBase);
        }
        else{
            System.out.println("elseben");
            right.evaluate(beliefBase);
        }
    }

    /**
     * Reduce this program tree to its simplest equivalent form.
     *
     * @return A simplification of this program tree, or this program tree unodified if it
     * cannot be simplified.
     */
    @Override
    public Node simplify() {
        return null;
    }
}

