package wmevo.Programs.LeafNodes;

import mappasrc.ActionParameters;
import mappasrc.BeliefBase;

import java.util.Random;

/**
 * Created by Zoltán on 2014.04.30..
 */
public class MovingLeafNode extends LeafNode {
    public MovingLeafNode() {
        Random random = new Random();
        double betweenNullAndOne = random.nextDouble();
        double scaled = betweenNullAndOne * 200;
        double shifted = scaled - 100;
        parameter = shifted;
//        System.out.println("MovingLeafNode szuletett: " + parameter);
    }

    @Override
    public void evaluate(BeliefBase beliefBase) {
        ActionParameters.setMovingValue(parameter);
        System.out.println("moving: (" + parameter + ") kivalasztva");
    }

    /**
     * Recursively builds a string representation of the tree rooted at this node.
     *
     * @return A string representation of this tree.
     */
    @Override
    public String print() {
        return null;
    }

    @Override
    public String print(int depth) {  //felesleges SB

        String whiteSpaces = insertWhiteSpace(depth);

        String outputString = whiteSpaces + "M[" + parameter +"]";
        return outputString;
    }

    /**
     * @return A short String that represents the function or value represented by this node.
     */
    @Override
    public String getLabel() {
        return null;
    }
}
