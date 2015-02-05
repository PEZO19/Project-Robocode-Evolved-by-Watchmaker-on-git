package wmevo;

import mappasrc.AgentProgram;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import wmevo.Programs.Factory.AgentProgramFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.28..
 */
public class AgentProgramMutation implements EvolutionaryOperator<AgentProgram> {

    private final AgentProgramFactory agentProgramFactory;

    private final Probability mutationProbability;

    public AgentProgramMutation(AgentProgramFactory agentProgramFactory, Probability probability) {
        this.mutationProbability = probability;
        this.agentProgramFactory = agentProgramFactory;
    }

    @Override
    public List<AgentProgram> apply(List<AgentProgram> selectedCandidates, Random rng) {


        System.out.println("Populacio a mutacio elott:");
        printPopulation(selectedCandidates);


        List<AgentProgram> mutatedPopulation = new ArrayList<AgentProgram>(selectedCandidates.size());
        for (AgentProgram agentProgram : selectedCandidates)
        {
            mutatedPopulation.add(agentProgram.mutate(rng, mutationProbability, agentProgramFactory));
        }

        System.out.println("Populacio a mutacio utan:");
        printPopulation(mutatedPopulation);



        return mutatedPopulation;
    }





    public void printPopulation(List<AgentProgram> population){

//        int i = 0;
//        for (AgentProgram agentProgram : population)
//        {
//            i++;
//            System.out.println(i + ". egyed:");
//            System.out.println(agentProgram.toString());
//        }

        for (int i = 0; i < population.size(); i++) {
            System.out.println(i + ". egyed:");
            System.out.println(population.get(i).toString());
        }

    }



}
