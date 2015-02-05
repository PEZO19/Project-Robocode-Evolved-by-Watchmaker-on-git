package wmevo.Programs.Factory;

import org.uncommons.maths.random.Probability;
import wmevo.Node;
import wmevo.Programs.LeafNodes.TurnLeafNode;

/**
 * Created by Zoltán on 2014.04.28..
 */
public class TurnProgramFactory extends AbstractProgramCandidateFactory{
    public TurnProgramFactory(int maxDepth, Probability operationProbability) {
        super(maxDepth,operationProbability);
    }

    /**
     * GunTurn, Moving, Shooting, Turn Programok Leaf Node-jainak
     * különbözik az értékkészlete, erre vonatkozó infót ad le.
     */
    @Override
    public Node getSpecificLeafNode() {
        return new TurnLeafNode();
    }
}
