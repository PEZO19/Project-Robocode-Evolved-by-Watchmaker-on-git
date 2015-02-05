package wmevo.Programs;

import org.uncommons.maths.random.Probability;
import wmevo.Node;
import wmevo.Programs.Factory.AgentProgramFactory;
import wmevo.Programs.Factory.TurnProgramFactory;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.23..
 */
public class TurnProgram implements Serializable {

    public Node tree;

    public TurnProgram(Node tree) {
        this.tree = tree;
    }

    public TurnProgram(TurnProgramFactory turnProgramFactory, Random rng) {

        tree = turnProgramFactory.generateRandomCandidate(rng);
    }

    public TurnProgram mutate(Random rng, Probability mutationProbability, AgentProgramFactory agentProgramFactory) {

        this.tree = tree.mutate(rng, mutationProbability, agentProgramFactory.turnProgramFactory);
        return this;
    }

    @Override
    public String toString() {
        return "\n TurnProgram: \n" + tree.print(tree.getDepth());
    }

}
