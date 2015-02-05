package wmevo.Programs;

import org.uncommons.maths.random.Probability;
import wmevo.Node;
import wmevo.Programs.Factory.AgentProgramFactory;
import wmevo.Programs.Factory.GunTurnProgramFactory;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.23..
 */
public class GunTurnProgram implements Serializable {

    public Node tree;

    public GunTurnProgram(Node tree) {
        this.tree = tree;
    }

    public GunTurnProgram(GunTurnProgramFactory gunTurnProgramFactory, Random rng) {

        tree = gunTurnProgramFactory.generateRandomCandidate(rng);
    }

    public GunTurnProgram mutate(Random rng, Probability mutationProbability, AgentProgramFactory agentProgramFactory) {

        this.tree = tree.mutate(rng, mutationProbability, agentProgramFactory.gunTurnProgramFactory);
        return this;
    }

    @Override
    public String toString() {
        return "\n GunTurnProgram: \n" + tree.print(tree.getDepth());
    }
}
