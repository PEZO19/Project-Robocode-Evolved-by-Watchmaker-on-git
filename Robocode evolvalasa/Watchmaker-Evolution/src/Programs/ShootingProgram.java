package wmevo.Programs;

import org.uncommons.maths.random.Probability;
import wmevo.Node;
import wmevo.Programs.Factory.AgentProgramFactory;
import wmevo.Programs.Factory.ShootingProgramFactory;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.23..
 */
public class ShootingProgram implements Serializable{

    public Node tree;

    public ShootingProgram(Node tree) {
        this.tree = tree;
    }

    public ShootingProgram(ShootingProgramFactory shootingProgramFactory, Random rng) {

        tree = shootingProgramFactory.generateRandomCandidate(rng);
    }

    public ShootingProgram mutate(Random rng, Probability mutationProbability, AgentProgramFactory agentProgramFactory) {

        this.tree = tree.mutate(rng, mutationProbability, agentProgramFactory.shootingProgramFactory);
        return this;
    }

    @Override
    public String toString() {
        return "\n ShootingProgram: \n" + tree.print(tree.getDepth());
    }
}