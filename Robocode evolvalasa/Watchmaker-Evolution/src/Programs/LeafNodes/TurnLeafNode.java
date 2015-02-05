package wmevo.Programs.LeafNodes;

import mappasrc.ActionParameters;
import mappasrc.BeliefBase;

import java.util.Random;

/**
 * Created by Zoltán on 2014.04.30..
 */
public class TurnLeafNode extends LeafNode{
    public TurnLeafNode() {

        //TODO: változó a dolog, mi a minimum es a maximum...?
        Random random = new Random();
        double betweenNullAndOne = random.nextDouble();
        double minimum = -10;
        double maximum = +10;
        double scaled = betweenNullAndOne * (maximum - minimum);
        double shift = (maximum - minimum) / 2;
        double shifted = scaled - shift;
        parameter = shifted;

    }
    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any
     * child nodes) and returns a numeric value. //TODO: boolt ad vissza nálunk
     *
     * @param actionParameter@return The result of evaluating this node and all of its children.
     */
    @Override
    public void evaluate(BeliefBase beliefBase) {
        ActionParameters.setTurnValue(parameter);
        System.out.println("turn: (" + parameter + ") kivalasztva");
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

        String outputString = whiteSpaces + "T[" + parameter +"]";
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
