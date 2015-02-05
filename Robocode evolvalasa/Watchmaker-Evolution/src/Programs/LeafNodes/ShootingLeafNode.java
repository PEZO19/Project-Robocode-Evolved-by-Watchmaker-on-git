package wmevo.Programs.LeafNodes;

import mappasrc.ActionParameters;
import mappasrc.BeliefBase;

import java.util.Random;

/**
 * Created by Zoltán on 2014.04.30..
 */
public class ShootingLeafNode extends LeafNode {
    /**
     * Recursively builds a string representation of the tree rooted at this node.
     *
     * @return A string representation of this tree.
     */
    @Override
    public String print() {
        return "ShootingLeafNode print-je, de nem szabadna meghivodnia";
    }

    public ShootingLeafNode() {

        Random random = new Random();
        double nullToFour = random.nextInt(5);    // 0-4
        double shifted = nullToFour + 0;
        parameter = shifted;
        //System.out.println("ShootingLeafNode szuletett: " + parameter);

    }


    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any
     * child nodes) and returns a numeric value. //TODO: boolt ad vissza nálunk
     *
     * @param actionParameter@return The result of evaluating this node and all of its children.
     */
    //Actionparameters fog megfelelő értékre állítani.- amit el kell érnie, tehát az Actionparameters static kéne legyen
    @Override
    public void evaluate(BeliefBase beliefBase) {
        ActionParameters.setShootingValue(parameter);
        System.out.println("fire: (" + parameter + ") kivalasztva");
    }

    /**
     * Recursively builds a string representation of the tree rooted at this node.
     *
     * @return A string representation of this tree.
     */
    @Override
    public String print(int depth) {  //felesleges SB

        String whiteSpaces = insertWhiteSpace(depth);

        String outputString = whiteSpaces + "S[" + parameter +"]";
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
