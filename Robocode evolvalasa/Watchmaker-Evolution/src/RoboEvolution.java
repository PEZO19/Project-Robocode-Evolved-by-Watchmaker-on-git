package wmevo;

import mappasrc.AgentProgram;
import mappasrc.AgentProgramFitnessEvaluator;
import mappasrc.BattleRunner;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;
import wmevo.Programs.Factory.AgentProgramFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoltán on 2014.04.28..
 */
public class RoboEvolution {

    // Robocode Stuffs

    // - BattleRunner -------------------

    static boolean logMessagesEnabled = true;
    static boolean battleViewVisible = false;

    public static int numberOfRoundInBattle = 1;
    static int numberOfBattle = 2;

    //------------------------------------

    // - Közös ****************************

    // WatchMaker Stuffs

    // - Evolve --------------------------

    static int populationSize = 3;
    static int eliteCount = 0;


    // static számlálók

    public static int generateRandomCandidate_db = 0;

    // -----------------------------------

    public static void main(String[] args) {

        //init dolgok
        AgentProgramFitnessEvaluator.beliefBase.init();


        BattleRunner.initBattleRunner(logMessagesEnabled,battleViewVisible,numberOfRoundInBattle,800,600);
        AgentProgram agentProgram = evolve();
        save(agentProgram, Double.parseDouble(null));
        BattleRunner.finalizeBattleRunner();
    }

    public static AgentProgram evolve(){

        AgentProgramFactory factory = new AgentProgramFactory(3, new Probability(0.65)); //Mélység és OperationNode esély

        List<EvolutionaryOperator<AgentProgram>> operators = new ArrayList<EvolutionaryOperator<AgentProgram>>();
        operators.add(new AgentProgramMutation(factory, new Probability(0.3)));
        operators.add(new AgentProgramCrossover());

        AgentProgramFitnessEvaluator evaluator = new AgentProgramFitnessEvaluator();

        GenerationalEvolutionEngine<AgentProgram> engine = new GenerationalEvolutionEngine<AgentProgram>(factory,
                new EvolutionPipeline<AgentProgram>(operators),
                evaluator,
                new RouletteWheelSelection(), //tobbet is kivalaszthat ugyanabbol :S
                new MersenneTwisterRNG());

        engine.setSingleThreaded(true);

        return engine.evolve(populationSize,eliteCount,new TargetFitness(0.0d, false)); //true = isNatural = nagyobb a jobb ?


    }

    public static void save(AgentProgram agentProgram, double fitness) {

        //Szerializalas
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        int fileNumber = 0;

        try {

            File file = new File("C:\\Dropbox\\_PEZO Projects\\robocode-mappa-alapjan\\Evobot_VEGLEGES_4_" + Integer.toString(fileNumber) + String.valueOf(fitness));

            while(file.exists()){
                fileNumber++;
                file = new File("C:\\Dropbox\\_PEZO Projects\\robocode-mappa-alapjan\\Evobot_VEGLEGES_4_" + Integer.toString(fileNumber) + String.valueOf(fitness));
            }

            fos = new FileOutputStream("Evobot_VEGLEGES_4_" + Integer.toString(fileNumber) + String.valueOf(fitness));
            out = new ObjectOutputStream(fos);
            out.writeObject(agentProgram);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Saved with filname: Evobot" + fileNumber);
    }

}
