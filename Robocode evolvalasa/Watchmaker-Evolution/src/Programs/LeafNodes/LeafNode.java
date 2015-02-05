package wmevo.Programs.LeafNodes;

import org.uncommons.maths.random.Probability;
import wmevo.Node;
import wmevo.Programs.Factory.AbstractProgramCandidateFactory;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.28..
 */
public abstract class LeafNode implements Node , Serializable
{
    //Az adott intervallumbol randomolt szám.
    double parameter;

    /**
     * The arity of a non-function node is always zero.
     * @return 0
     */
    public int getArity()
    {
        return 0;
    }

    /**
     * Leaf nodes always have a depth of 1 since they have no child nodes.
     * @return 1
     */
    public int getDepth()
    {
        return 1;
    }

    /**
     * Leaf nodes always have a width of 1 since they have no child nodes.
     * @return 1
     */
    public int getWidth()
    {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public int countNodes()
    {
        return 1;
    }

    public Node getNode(int index)
    {
        if (index != 0)
        {
            throw new IndexOutOfBoundsException("Invalid node index: " + index);
        }
        return this;
    }


    /**
     * {@inheritDoc}
     */
    public Node getChild(int index)
    {
        throw new IndexOutOfBoundsException("Leaf nodes have no children.");
    }


    public Node replaceNode(int index, Node newNode)
    {
        if (index != 0)
        {
            throw new IndexOutOfBoundsException("Invalid node index: " + index);
        }
        return newNode;
    }


    /**
     * {@inheritDoc}
     */
    public Node mutate(Random rng, Probability mutationProbability, AbstractProgramCandidateFactory abstractProgramCandidateFactory)
    {
        if (mutationProbability.nextEvent(rng))
        {
            //System.out.println("mutacio Leafnodenal general:");
            return abstractProgramCandidateFactory.generateRandomCandidate(rng);
        }
        else
        {
            // Node is unchanged.
            return this;
        }
    }


    /**
     * Returns this node (leaf nodes cannot be simplified).
     * @return This node, unmodified.
     */
    public Node simplify()
    {
        return this;
    }

    public String insertWhiteSpace(int depth){

        StringBuilder buffer = new StringBuilder();

        for(int i=1; i<depth;i++){
            buffer.append("      ");
        }

        return buffer.toString();
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p/>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return this.print(0) + "Ez egy leafNode volt";
    }
}

