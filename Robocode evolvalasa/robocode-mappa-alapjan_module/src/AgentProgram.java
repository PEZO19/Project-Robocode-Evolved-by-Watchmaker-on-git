package mappasrc;

import org.uncommons.maths.random.Probability;
import wmevo.Programs.Factory.AgentProgramFactory;
import wmevo.Programs.GunTurnProgram;
import wmevo.Programs.MovingProgram;
import wmevo.Programs.ShootingProgram;
import wmevo.Programs.TurnProgram;

import java.io.Serializable;
import java.util.Random;



/**
 * Created by Zoltán on 2014.04.23..
 */
public class AgentProgram implements Serializable {

    public GunTurnProgram   gunTurnProgram;
    public MovingProgram    movingProgram;
    public ShootingProgram  shootingProgram;
    public TurnProgram      turnProgram;

    public AgentProgram(GunTurnProgram gunTurnProgram,
                        MovingProgram movingProgram,
                        ShootingProgram shootingProgram,
                        TurnProgram turnProgram) {
        this.gunTurnProgram = gunTurnProgram;
        this.movingProgram = movingProgram;
        this.shootingProgram = shootingProgram;
        this.turnProgram = turnProgram;
    }

    public AgentProgram(MovingProgram movingProgram, ShootingProgram shootingProgram, TurnProgram turnProgram, Random rng) {
        this.movingProgram = movingProgram;
        this.shootingProgram = shootingProgram;
        this.turnProgram = turnProgram;
    }

    //"Eredeti konstruktor"
    public AgentProgram(AgentProgramFactory agentProgramFactory, Random rng) {
        //gunTurnProgram = new GunTurnProgram();
        movingProgram  = new MovingProgram(agentProgramFactory.movingProgramFactory,rng);
        shootingProgram= new ShootingProgram(agentProgramFactory.shootingProgramFactory, rng);
        turnProgram    = new TurnProgram(agentProgramFactory.turnProgramFactory, rng);
    }
    //csak tesztre
    public AgentProgram(ShootingProgram shootingProgram, Random rng) {
        this.shootingProgram = shootingProgram;
    }

    // mindegyik program végén a LeafNode beállítja az ActionParametert a megfelelő értékre
    public void calculateActionParameters(BeliefBase beliefBase) {

//        gunTurnProgram.tree.evaluate(beliefBase);

        movingProgram.tree.evaluate(beliefBase);
        System.out.println("Calculate moving befejezve");

        shootingProgram.tree.evaluate(beliefBase);
        System.out.println("Calculate shooting befejezve");

        turnProgram.tree.evaluate(beliefBase);
        System.out.println("Calculate turn befejezve");

    }

    public AgentProgram mutate(Random rng, Probability mutationProbability, AgentProgramFactory agentProgramFactory) {

        //letrehozunk egy AgentProgramot - csak a returnhöz
        AgentProgram mutatedAgentProgram = new AgentProgram(agentProgramFactory, rng);

        //mutatedAgentProgram.gunTurnProgram  = this.gunTurnProgram. mutate(rng, mutationProbability, agentProgramFactory);
        mutatedAgentProgram.movingProgram   = this.movingProgram.  mutate(rng, mutationProbability, agentProgramFactory);
        mutatedAgentProgram.shootingProgram = this.shootingProgram.mutate(rng, mutationProbability,agentProgramFactory);
        mutatedAgentProgram.turnProgram     = this.turnProgram.    mutate(rng, mutationProbability, agentProgramFactory);

        return mutatedAgentProgram;
    }

    @Override
    public String toString() {
        return "|_AgentProgram_kezdodik_| \n "+ shootingProgram.toString() + "\n" + movingProgram.toString() + "\n"+ turnProgram.toString() + "\n";

    }
}
