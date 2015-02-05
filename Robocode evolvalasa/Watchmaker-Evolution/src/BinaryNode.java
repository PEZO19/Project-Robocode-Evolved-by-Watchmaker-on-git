package wmevo;

/**
 * Created by Zoltán on 2014.04.28..
 */

import org.uncommons.maths.random.Probability;
import org.uncommons.util.reflection.ReflectionUtils;
import wmevo.Programs.Factory.AbstractProgramCandidateFactory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Random;

/**
 * Convenient base class for {@link Node}s that have two sub-trees.
 * @author Daniel Dyer
 */
public abstract class BinaryNode implements Node , Serializable
{
    protected static final double[] NO_ARGS = new double[0];

    /** The first argument to the binary function. */
    protected final Node left;
    /** The second argument to the binary function. */
    protected final Node right;

    protected String symbol = "[Symb]";


    /**
     * @param left The first argument to the binary function.
     * @param right The second argument to the binary function.
     * @param symbol A single character that indicates the type of function.
     */
    protected BinaryNode(Node left, Node right, String symbol)
    {
        this.left = left;
        this.right = right;
        this.symbol = symbol;
    }

    public BinaryNode(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.symbol = null;

    }


    /**
     * {@inheritDoc}
     */
    public String getLabel()
    {
        return String.valueOf(symbol);
    }


    /**
     * The arity of a binary node is two.
     * @return 2
     */
    public int getArity()
    {
        return 2;
    }


    /**
     * The depth of a binary node is the depth of its deepest sub-tree plus one.
     * @return The depth of the tree rooted at this node.
     */
    public int getDepth()
    {
        return 1 + Math.max(left.getDepth(), right.getDepth());
    }


    /**
     * The width of a binary node is the sum of the widths of its two sub-trees.
     * @return The width of the tree rooted at this node.
     */
    public int getWidth()
    {
        return left.getWidth() + right.getWidth();
    }


    /**
     * {@inheritDoc}
     */
    public int countNodes()
    {
        return 1 + left.countNodes() + right.countNodes();
    }


    /**
     * {@inheritDoc}
     */
    public Node getNode(int index)
    {
        if (index == 0)
        {
            return this;
        }
        int leftNodes = left.countNodes();
        if (index <= leftNodes)
        {
            return left.getNode(index - 1);
        }
        else
        {
            return right.getNode(index - leftNodes - 1);
        }
    }


    /**
     * {@inheritDoc}
     */
    public Node getChild(int index)
    {
        switch (index)
        {
            case 0: return left;
            case 1: return right;
            default: throw new IndexOutOfBoundsException("Invalid child index: " + index);
        }
    }


    /**
     * {@inheritDoc}
     */
    public Node replaceNode(int index, Node newNode)
    {
        if (index == 0)
        {
            return newNode;
        }

        int leftNodes = left.countNodes();
        if (index <= leftNodes)
        {
            return newInstance(left.replaceNode(index - 1, newNode), right);
        }
        else
        {
            return newInstance(left, right.replaceNode(index - leftNodes - 1, newNode));
        }
    }



    /**
     * {@inheritDoc}
     */
    public String print()
    {
//        StringBuilder buffer = new StringBuilder("(");
//        buffer.append(left.print());
//        buffer.append(' ');
//        //buffer.append(symbol);
//        buffer.append('-');
//        buffer.append(' ');
//        buffer.append(right.print());
//        buffer.append(')');
//        return buffer.toString();

        StringBuilder buffer = new StringBuilder();
        buffer.append(left.print(this.getDepth() - 1));
        buffer.append('\n'); // ha level, ha nem level, utana uj sor jon

        insertWhiteSpace(this.getDepth(), buffer);
        //buffer.append("[Bin ]");
        buffer.append(symbol);
        buffer.append('\n');

        buffer.append(right.print(this.getDepth() - 1));
        buffer.append('\n');
        return buffer.toString();



//        System.out.println(left.print());
//        System.out.println("-binary-"); //this.tostring()
//        System.out.println(right.print());
    }

    public String print(int depth){ //eleg lenne ez a print is, csak akkor ezt is kellene meghivni eredetileg.
        StringBuilder buffer = new StringBuilder();
        buffer.append(left.print(depth-1));
        buffer.append('\n'); // ha level, ha nem level, utana uj sor jon

        //buffer.append(symbol);
        insertWhiteSpace(depth,buffer);
        //buffer.append("[Bin ]");
        buffer.append(symbol);
        buffer.append('\n');

        buffer.append(right.print(depth-1));
        return buffer.toString();
    }





    /**
     * {@inheritDoc}
     */


    public Node mutate(Random rng, Probability mutationProbability, AbstractProgramCandidateFactory abstractProgramCandidateFactory)
    {
        if (mutationProbability.nextEvent(rng))
        {
            //System.out.println("mutacio Binary Nodenal general:");
            return abstractProgramCandidateFactory.generateRandomCandidate(rng);
        }
        else
        {
            Node newLeft = left.mutate(rng, mutationProbability, abstractProgramCandidateFactory);
            Node newRight = right.mutate(rng, mutationProbability, abstractProgramCandidateFactory);
            if (newLeft != left && newRight != right)
            {
                return newInstance(newLeft, newRight);
            }
            else
            {
                // Tree has not changed.
                return this;
            }
        }
    }


    private Node newInstance(Node newLeft, Node newRight)
    {
        Constructor<? extends BinaryNode> constructor = ReflectionUtils.findKnownConstructor(this.getClass(),
                Node.class,
                Node.class);
        return ReflectionUtils.invokeUnchecked(constructor, newLeft, newRight);
    }


    @Override
    public String toString()
    {
        return this.print() + "Ez egy binaryNode volt";
    }

    public StringBuilder insertWhiteSpace(int depth, StringBuilder buffer){

        for(int i=1; i<depth;i++){
            buffer.append("      ");
        }

        return buffer;
    }


}
