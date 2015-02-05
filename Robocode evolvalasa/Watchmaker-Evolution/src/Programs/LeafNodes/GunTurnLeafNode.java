package wmevo.Programs.LeafNodes;

import mappasrc.BeliefBase;

import java.util.Random;

/**
 * Created by Zoltán on 2014.04.30..
 */
public class GunTurnLeafNode extends LeafNode {

    public GunTurnLeafNode() {

        Random random = new Random();
        double betweenNullAndOne = random.nextDouble();
        double scaled = betweenNullAndOne * 40;
        double shifted = scaled - 20;         // -20 - +20
        parameter = shifted;

    }

    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any
     * child nodes) and returns a numeric value. //TODO: boolt ad vissza nálunk
     *
     * @param beliefBase@return The result of evaluating this node and all of its children.
     */
    @Override
    public void evaluate(BeliefBase beliefBase) {

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

        String outputString = whiteSpaces + "G[" + parameter +"]";
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
