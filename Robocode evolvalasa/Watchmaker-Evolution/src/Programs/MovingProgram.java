package wmevo.Programs;

import org.uncommons.maths.random.Probability;
import wmevo.Node;
import wmevo.Programs.Factory.AgentProgramFactory;
import wmevo.Programs.Factory.MovingProgramFactory;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.23..
 */
public class MovingProgram implements Serializable {

    public Node tree;

    public MovingProgram(Node tree) {
        this.tree = tree;
    }

    public MovingProgram(MovingProgramFactory movingProgramFactory, Random rng) {

        tree = movingProgramFactory.generateRandomCandidate(rng);
    }

    public MovingProgram mutate (Random rng, Probability mutationProbability, AgentProgramFactory agentProgramFactory) {

        this.tree = tree.mutate(rng, mutationProbability, agentProgramFactory.movingProgramFactory);
        return this;
    }

    @Override
    public String toString() {
        return "\n MovingProgram: \n" + tree.print(tree.getDepth());
    }
}
