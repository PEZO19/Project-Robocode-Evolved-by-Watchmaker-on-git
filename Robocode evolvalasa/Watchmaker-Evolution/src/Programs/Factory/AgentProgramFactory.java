package wmevo.Programs.Factory;

import mappasrc.AgentProgram;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

/**
 * Created by Zoltán on 2014.04.23..
 */
public class AgentProgramFactory extends AbstractCandidateFactory<AgentProgram>{

    public final GunTurnProgramFactory gunTurnProgramFactory;
    public final MovingProgramFactory movingProgramFactory;
    public final ShootingProgramFactory shootingProgramFactory;
    public final TurnProgramFactory turnProgramFactory;


    public AgentProgramFactory(int maxDepth, Probability operationProbability){
        {
            if (maxDepth < 1)
            {
                throw new IllegalArgumentException("Max depth must be at least 1.");
            }
            else{
                this.gunTurnProgramFactory = new GunTurnProgramFactory(maxDepth, operationProbability);
                this.movingProgramFactory = new MovingProgramFactory(maxDepth, operationProbability);
                this.shootingProgramFactory = new ShootingProgramFactory(maxDepth, operationProbability);
                this.turnProgramFactory = new TurnProgramFactory(maxDepth, operationProbability);
            }
        }
    }


    @Override
    public AgentProgram generateRandomCandidate(Random rng) {

        AgentProgram agentProgram = new AgentProgram(this, rng); //TODO: wtf? nem kell AgentProgramFactory paraméterbe?
//        agentProgram.gunTurnProgram = gunTurnFactory.generateRandomCandidate(random);
//        agentProgram.movingProgram = movingProgramFactory.generateRandomCandidate(random);
//        agentProgram.shootingProgram = shootingProgramFactory.generateRandomCandidate(random);
//        agentProgram.turnProgram = turnFactory.generateRandomCandidate(random);

        return agentProgram;
    }
}
