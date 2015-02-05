package mappasrc; /**
 * Created by Zoltán on 2014.04.21..
 */
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;

public class BattleRunner {

    public static RobocodeEngine engine;
    public static BattlefieldSpecification battlefield;
    public static int numberOfRounds;
    public static int numberOfBattle = 0;

    //main volt
    public static void main(String[] args) {

        long starttime = System.currentTimeMillis();

        // Disable log messages from Robocode
        RobocodeEngine.setLogMessagesEnabled(true);

        // Create the RobocodeEngine
        //   RobocodeEngine engine = new RobocodeEngine(); // Run from current working directory
        RobocodeEngine engine = new RobocodeEngine(new java.io.File("C:/Robocode")); // Run from C:/Robocode

        // Add our own battle listener to the RobocodeEngine
        engine.addBattleListener(new BattleObserver());

        // Show the Robocode battle view
        engine.setVisible(false);

        // Setup the battle specification

        int numberOfRounds = 10;
        BattlefieldSpecification battlefield = new BattlefieldSpecification(800, 600); // 800x600
        RobotSpecification[] selectedRobots = engine.getLocalRepository("sample.RamFire,sample.Corners,sample.Evobot");

        BattleSpecification battleSpec = new BattleSpecification(numberOfRounds, battlefield, selectedRobots);

        //DEBUG FitnessInfo.setCornerscanned(0);
        //DEBUG System.out.println("CORNERSCANNED: " + FitnessInfo.getCornerscanned());
        // Run our specified battle and let it run till it is ove
        //engine.runBattle(battleSpec, true);

        for (int i = 0;i<100;i++) {
            System.out.println(i+1 +". Battle");
            engine.runBattle(battleSpec, true);
        }

        // Cleanup our RobocodeEngine
        engine.close();
        //DEBUG System.out.println("CORNERSCANNED: " + FitnessInfo.getCornerscanned());
        System.out.println("eltelt ido:" + (System.currentTimeMillis()-starttime));

        // Make sure that the Java VM is shut down properly
        System.exit(0);
    }

    //RoboEvolution hivja, előkészíti az egészet, hogy a getfitness már csak a konkrét játékokat játassa
    public static void initBattleRunner(boolean logMessagesEnabled,
                                        boolean battleViewVisible,
                                        int numberOfRounds,
                                        int battlefieldX,
                                        int battlefieldY){

        // Disable log messages from Robocode
        RobocodeEngine.setLogMessagesEnabled(logMessagesEnabled);

        // Create the RobocodeEngine
        //   RobocodeEngine engine = new RobocodeEngine(); // Run from current working directory
        engine = new RobocodeEngine(new java.io.File("C:/Robocode")); // Run from C:/Robocode

        // Add our own battle listener to the RobocodeEngine
        engine.addBattleListener(new BattleObserver());

        // Show the Robocode battle view
        engine.setVisible(battleViewVisible);

        // Setup the battle specification
        battlefield = new BattlefieldSpecification(battlefieldX, battlefieldY); // 800x600

        BattleRunner.numberOfRounds = numberOfRounds;
    }

    //FitnessEvaluator getFitnesse hivja
    public static void runRobocodeBattle(String playerOneName,String playerTwoName) {

//        RobotSpecification[] rs = engine.getLocalRepository();
//        megtalált robotok kiiratása
//        for(int i = 0; i<29; i++){
//            System.out.println(rs[i].getClassName());
//        }


        RobotSpecification[] selectedRobots = engine.getLocalRepository(playerOneName + "," + playerTwoName);

        BattleSpecification battleSpec = new BattleSpecification(numberOfRounds, battlefield, selectedRobots);


        // Run our specified battle and let it run till it is over
        engine.runBattle(battleSpec, true); // waits till the battle finishes //INITIAL PSITIONS itt!!!

    }

    //RoboEvolution hívja az egész evolucio végén.
    public static void finalizeBattleRunner(){

        // Cleanup our RobocodeEngine
        engine.close();

        // Make sure that the Java VM is shut down properly
        System.exit(0);
    }


}
