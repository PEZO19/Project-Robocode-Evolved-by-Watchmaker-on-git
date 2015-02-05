package wmevo;

import mappasrc.AgentProgram;
import mappasrc.AgentProgramFitnessEvaluator;
import mappasrc.BattleRunner;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by Zoltán on 2014.05.12..
 */
public class RoboLoader {

//   static String fileName = "Evobot0 - 325"; // 1a, 1b, 3
   static String fileName = "Evobot_VEGLEGES_4_0124.15000000000649"; //kijött minden // 2
//       static String fileName = "jk.mega.DrussGT"; // 1a, 1b, 3



//    static String fileName = "Evobot_VEGLEGES_1_0774.7499 - gyonyoru";
//    static String fileName = "Evobot_VEGLEGES_2_0715.3499999999993";
//    static String fileName = "Evobot_VEGLEGES_3_0736.8999999999993"; // "szerencse"
//        static String fileName = "Evobot_VEGLEGES_4_0376.44999999999976";


    static boolean logMessagesEnabled = true;
    static boolean battleViewVisible = true;

    public static int numberOfRoundInBattle = 1;


    public static void main(String[] args) {

        //init dolgok
        AgentProgramFitnessEvaluator.beliefBase.init();

        //Load from file
        AgentProgram agentProgram = load();

        //BattleRunner
        BattleRunner.initBattleRunner(logMessagesEnabled, battleViewVisible, numberOfRoundInBattle, 800, 600);
        AgentProgramFitnessEvaluator.loadedBattle(agentProgram);

    }




    private static AgentProgram load() {
        // read the object from file
        // save the object to file
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(fileName);
            in = new ObjectInputStream(fis);
            AgentProgram agentProgram = (AgentProgram) in.readObject();
            in.close();
            System.out.println("Loaded");
            return agentProgram;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
