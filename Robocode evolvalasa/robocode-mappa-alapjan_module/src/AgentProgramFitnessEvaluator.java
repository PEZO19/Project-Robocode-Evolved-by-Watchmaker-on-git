package mappasrc;

import org.uncommons.watchmaker.framework.FitnessEvaluator;
import wmevo.RoboEvolution;

import java.util.List;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.21..
 */
public class AgentProgramFitnessEvaluator implements FitnessEvaluator<AgentProgram>{

    public static Random rng = new Random();

    public static AgentProgram agentProgram;

    //Round közben frissül Turn-ről Turn-re, Inicializálódik minden Round elején
    public static BeliefBase beliefBase;

    //ki ki ellen játszik, hány körig, stb. - Lényegében a BattleRunner paraméterezése
    public static RoundConfig roundConfig;

    public static int egyedJatszik = 1;

    public AgentProgramFitnessEvaluator() {
        //this.agentProgram = new AgentProgram(); //TODO: megirni az ures konstruktorat AP-re , illetve irni neki initet mint BB-nek, ha kell
        //this.beliefBase = new BeliefBase();     // TODO: rendes initet

    }

    // Evobot hivja minden Round vegen, onRoundeEnded event-ben.
    // Minden round-ban nullázni kell a TudásBázist
    public static void initBeliefBase(){}

    // frissiteni kell még a BB-t a Sensing alapján - feltoltjuk a fifokat
    private static void refreshBeliefBase(Sensing sensing) {
        System.out.println("refreshBB");

        BeliefBase.v_per_d = sensing.Vellen/sensing.d;
        BeliefBase.fifok.get("_Vellen_atlag").add(sensing.Vellen);
        BeliefBase.fifok.get("_Vsajat_atlag").add(sensing.Vsajat);
        BeliefBase.Vellen = sensing.Vellen;
        BeliefBase.Vsajat = sensing.Vsajat;

        //delta_d = uj tavolsag - regi tavolsag
        BeliefBase.delta_d = sensing.d - BeliefBase.d;
        //tavolsag frissitese
        BeliefBase.d = sensing.d;

        BeliefBase.heat = sensing.Heat;
        BeliefBase.Esajat = sensing.Esajat;
        BeliefBase.Eellen = sensing.Eellen;

        //x_tav
        if(sensing.PsajatX <= 400){
            BeliefBase.x_tav = sensing.PsajatX;
        }
        else{
            BeliefBase.x_tav = 800 - sensing.PsajatX;
        }

        //y_tav
        if(sensing.PsajatY <= 300){
            BeliefBase.y_tav = sensing.PsajatY;
        }
        else{
            BeliefBase.y_tav = 600 - sensing.PsajatY;
        }


        System.out.println("refreshBB sikerult");
    }

    //TODO:configot ki inicializálja? A tervező a config fájlban? Minden Roundban uo. a config az egész Battle alatt?
    public static void initConfig(){}

    // Evobot hivja
    // Megadja Evobotnak a paramétereket, amikkel a következő turnben cselekednie kell.
    // Ehhez szükséges: 1. frissíteni a TudásBázist Evobot jelenlegi(turn) Érzékelés alapján.
    //                  2. a frissített Tudásbázis alapján kiértékelni(/interpretálni)
    public static void calculateActionParameters(Sensing sensing){
        refreshBeliefBase(sensing);
        agentProgram.calculateActionParameters(beliefBase);
        //mostmar kiolvashato az ActionParameters értéke
    }


    //Watchmaker hívja minden Battle-re
        //BattleRunnert kell benne hívni
    @Override
    public double getFitness(AgentProgram agentProgramp, List<? extends AgentProgram> agentPrograms) {

        // Static init
        agentProgram = agentProgramp;

        System.out.println( egyedJatszik + "." + " egyed jatszik N db BATTLE-t");

        //Agensprogram kiirata
        System.out.println(" getfitness " + agentProgram.toString());

        //100- az ellenfél alap HP-ja.
        FitnessInfo.refreshRemainingEnergyInBattle();

        //BattleRunner.runRobocodeBattle("sample.Corners","sample.Evobot");
//        BattleRunner.runRobocodeBattle("sample.VelociRobot", "sample.Evobot");
        BattleRunner.runRobocodeBattle("jk.mega.DrussGT", "sample.Evobot");

        egyedJatszik++;

        double fitness = FitnessInfo.getFitnessInBattleViaEnergy();
        System.out.println("Fitness: " + fitness + " ------------------------------------------------------ Fitness: " + fitness);

        //ha eleg jo kiiratom
        if(fitness<390){
            RoboEvolution.save(agentProgramp, fitness);
        }

        return fitness;
    }

    @Override
    public boolean isNatural() {
        return false;
    }

    public static void loadedBattle(AgentProgram agentProgramp){

        // Static init
        agentProgram = agentProgramp;

        //Agensprogram kiirata
        System.out.println(" getfitness " + agentProgram.toString());

        //100- az ellenfél alap HP-ja.
        FitnessInfo.refreshRemainingEnergyInBattle();

//        BattleRunner.runRobocodeBattle("sample.VelociRobot", "sample.Evobot"); // 1a
        BattleRunner.runRobocodeBattle("jk.mega.DrussGT", "sample.Evobot");      // 1b , 2
//        BattleRunner.runRobocodeBattle("sample.Interactive", "sample.Evobot"); // b


        double fitness = FitnessInfo.getFitnessInBattleViaEnergy();
        System.out.println("Fitness: " + fitness + " ------------------------------------------------------ Fitness: " + fitness);

        //ha eleg jo kiiratom
//        if(fitness<50){
//            RoboEvolution.save(agentProgramp,null);
//        }


    }
}
