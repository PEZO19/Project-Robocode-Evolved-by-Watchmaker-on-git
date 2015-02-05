package wmevo;

import mappasrc.AgentProgram;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;
import wmevo.Programs.MovingProgram;
import wmevo.Programs.ShootingProgram;
import wmevo.Programs.TurnProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zoltán on 2014.04.28..
 */
public class AgentProgramCrossover extends AbstractCrossover<AgentProgram> {

    protected AgentProgramCrossover() {
        //Cross-overek száma TODO: ezt is paraméter .txt/be tenni
        super(1);
    }

    @Override
    protected List<AgentProgram> mate(AgentProgram parent1Program, AgentProgram parent2Program, int numberOfCrossoverPoints, Random rng) {

        //int numberOfActions = 1; //#1
        int numberOfActions = 3;


        System.out.println(" \n Keresztezodes előtt állunk: \n");
        System.out.println("1. szülő: \n");
        System.out.println(parent1Program.toString());
        System.out.println("2. szülő: \n");
        System.out.println(parent2Program.toString());;


    //AgentProgram ==> Node
        //parent 1
        List<Node> parent1Programs = new ArrayList<Node>(numberOfActions);
        //parent1Programs.add(parent1Program.gunTurnProgram.tree);
        parent1Programs.add(parent1Program.movingProgram.tree);
        parent1Programs.add(parent1Program.shootingProgram.tree); //#1
        parent1Programs.add(parent1Program.turnProgram.tree);

        //parent 2
        List<Node> parent2Programs = new ArrayList<Node>(numberOfActions);
        //parent2Programs.add(parent2Program.gunTurnProgram.tree);
        parent2Programs.add(parent2Program.movingProgram.tree);
        parent2Programs.add(parent2Program.shootingProgram.tree); //#1
        parent2Programs.add(parent2Program.turnProgram.tree);

        // lemasolni ok hogyy csinaljak. valoszinel egy for ciklusban vegigmegyek az arraylisteken
        // es keresztezem a megfelelo fakat a lenti for-ral
        List<AgentProgram> results = new ArrayList<AgentProgram>(2);

        List<Node> offspring1 = new ArrayList<Node>(numberOfActions);
        List<Node> offspring2 = new ArrayList<Node>(numberOfActions);

    //Lenyegi atvett resz
        for(int j = 0; j<numberOfActions; j++){

            Node subOffspring1 = parent1Programs.get(j);
            Node subOffspring2 = parent2Programs.get(j);

            //TODO: valahogy kereszteződésnél megjelenhetnek "régebbi" Node-ok - amik eltűntek. Dunno why.
            //TODO: bár érdekes mód, lehet hogycsak az elnevezések miatt tűnt ugy, sokszor lecsekkoltam és most jo
            //TODO: kevés Node-dal a reszfak kiiratasaval (mutacio kozben) ki kellene iratni!
            for (int i = 0; i < numberOfCrossoverPoints; i++)
            {
                int crossoverPoint1 = rng.nextInt(parent1Programs.get(j).countNodes());
                System.out.println("countNodes1: " + parent1Programs.get(j).countNodes());
                Node subTree1 = parent1Programs.get(j).getNode(crossoverPoint1);
                System.out.println(" -------------------------------------------------------------- 1. szülő: " + j + ". fa");
                System.out.println(subTree1.toString());

                int crossoverPoint2 = rng.nextInt(parent2Programs.get(j).countNodes());
                System.out.println("countNodes2: " + parent1Programs.get(j).countNodes());
                Node subTree2 = parent2Programs.get(j).getNode(crossoverPoint2);
                System.out.println(" -------------------------------------------------------------- 2. szülő: " + j + ". fa");
                System.out.println(subTree2.toString());

                subOffspring1 = parent1Programs.get(j).replaceNode(crossoverPoint1, subTree2);
                subOffspring2 = parent2Programs.get(j).replaceNode(crossoverPoint2, subTree1);
            }

            offspring1.add(subOffspring1);
            offspring2.add(subOffspring2);
        }

    //Node ==> AgentProgram
        //GunTurnProgram gunTurnProgram1 = new GunTurnProgram(offspring1.get(0));
        MovingProgram movingProgram1 = new MovingProgram(offspring1.get(0));
        ShootingProgram shootingProgram1 = new ShootingProgram(offspring1.get(1));
        TurnProgram turnProgram1 = new TurnProgram(offspring1.get(2));
        //ShootingProgram shootingProgram1 = new ShootingProgram(offspring1.get(0)); #1


        //GunTurnProgram gunTurnProgram2 = new GunTurnProgram(offspring2.get(0));
        MovingProgram movingProgram2 = new MovingProgram(offspring2.get(0));
        ShootingProgram shootingProgram2 = new ShootingProgram(offspring2.get(1));
        TurnProgram turnProgram2 = new TurnProgram(offspring2.get(2));
        //ShootingProgram shootingProgram2 = new ShootingProgram(offspring2.get(0)); #1


        //AgentProgram agentProgram1 = new AgentProgram(gunTurnProgram1, movingProgram1,shootingProgram1,turnProgram1, rng);
        //AgentProgram agentProgram2 = new AgentProgram(gunTurnProgram2, movingProgram2,shootingProgram2,turnProgram2, rng);

        AgentProgram agentProgram1 = new AgentProgram(movingProgram1,shootingProgram1,turnProgram1, rng);
        AgentProgram agentProgram2 = new AgentProgram(movingProgram2,shootingProgram2,turnProgram2, rng);

        //csak teszteléshez #1
        //AgentProgram agentProgram1 = new AgentProgram(shootingProgram1, rng);
        //AgentProgram agentProgram2 = new AgentProgram(shootingProgram2, rng);
        //TODO: numberofactions 4

        results.add(agentProgram1);
        results.add(agentProgram2);

        System.out.println("\n Keresztezodes tortent:\n");
        System.out.println("1. utod: \n");
        System.out.println(agentProgram1.toString());
        System.out.println("2. utod: \n");
        System.out.println(agentProgram2.toString());

        return results;

    }
}

