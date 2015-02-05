package mappasrc;

import wmevo.RoboEvolution;

/**
 * Created by Zoltán on 2014.05.03..
 */
public class FitnessInfo {

    public static int maxHitsInRound = 100; //TODO: ezt átgondolni
    public static int hitsInRound = 0; // talalatok szama egy adott korban

    public static int roundsInBattle = RoboEvolution.numberOfRoundInBattle; //parameterkent at kene adni neki BattleRunner tartalmazz az infot.
    public static int maxHitsInBattle = maxHitsInRound * roundsInBattle;
    public static int hitsInBattle;
    public static double remainingEnergyInBattle = 0;

    public static void incrementHits() {
        setHitsInRound (getHitsInRound () + 1);
        setHitsInBattle(getHitsInBattle() + 1);

    }

    //Gerfitnessben refreshelni kell. -minden Battlre...
    public static void incrementRemainingEnergyInBattle(double remainingEnergyInRound){
        remainingEnergyInBattle += remainingEnergyInRound;
    }


    // bel lehetne tenni egy listába aztán később kiíratni.
    public static double getFitnessInRound(){ return (double)hitsInRound/(double)maxHitsInRound; }

    public static double getFitnessInBattleViaHits(){ return (double)hitsInBattle/(double)maxHitsInBattle; }

    public static double getFitnessInBattleViaEnergy(){ return remainingEnergyInBattle; }


    public static int getMaxHitsInRound() {
        return maxHitsInRound;
    }

    public static void setMaxHitsInRound(int maxHitsInRoundv) {
        maxHitsInRound = maxHitsInRoundv;
    }

    public static int getHitsInRound() {
        return hitsInRound;
    }

    public static void setHitsInRound(int hitsInRoundv) {
        hitsInRound = hitsInRoundv;
    }

    public static int getRoundsInBattle() {
        return roundsInBattle;
    }

    public static void setRoundsInBattle(int roundsInBattlev) {
        roundsInBattle = roundsInBattlev;
    }

    public static int getMaxHitsInBattle() {
        return maxHitsInBattle;
    }

    public static void setMaxHitsInBattle(int maxHitsInBattlev) {
        maxHitsInBattle = maxHitsInBattlev;
    }

    public static int getHitsInBattle() {
        return hitsInBattle;
    }

    public static void setHitsInBattle(int hitsInBattlev) {
        hitsInBattle = hitsInBattlev;
    }
    public static void refreshHitsinRound(){
        setHitsInRound(0);
    }

    public static void refreshRemainingEnergyInBattle() {
        remainingEnergyInBattle = 0;
    }
}

