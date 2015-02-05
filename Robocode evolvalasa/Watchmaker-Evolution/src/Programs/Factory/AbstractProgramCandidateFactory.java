package wmevo.Programs.Factory;

import org.uncommons.maths.random.Probability;
import wmevo.Node;
import wmevo.Programs.ISpecificLeafNodeGenerator;
import wmevo.Programs.Operation.*;

import java.util.Random;

/**
 * Created by Zoltán on 2014.04.28..
 */
// a TreeFactory-val egyezik meg, csak ennek vannak további gyerekei az Interface miatt
public abstract class AbstractProgramCandidateFactory implements ISpecificLeafNodeGenerator {

    public static int db = 0; //csak tesztelésre - generált egyedszám. (monoton nő)

    private final int maxDepth;
    private final Probability operationProbability;

    public AbstractProgramCandidateFactory(int maxDepth,
                                           Probability operationProbability) {
        if (maxDepth < 1)
        {
            throw new IllegalArgumentException("Max depth must be at least 1.");
        }

        this.maxDepth = maxDepth;
        this.operationProbability = operationProbability;
    }


    /**
     * @param rng random number generator az vagy az Operation-ök VAGY
     *            a Leaf-ek közötti választások között
     *
     */
    public Node generateRandomCandidate(Random rng) {
        //db++; //ehelyett Shootingprogramban?
        //System.out.println(db + ". generateRandomCandidate");
        //TODO: kiiratni azt is, hogy melyik Agensprogramhoz tartozik az adott makenode.
        // TODO:- magyarul minden GENERATERANDOMCANDIDATE alkalmával növelni egy számlálót sztem.
        return makeNode(rng, maxDepth);
    }

    private Node makeNode(Random rng, int maxDepth) {

        // Ha belso Node lesz amit épp készítünk
        // (mivel azt sorsoltuk és van még mélységben hely)
        if (operationProbability.nextEvent(rng) && maxDepth > 1)
        {
            // Max depth for sub-trees is one less than max depth for this node.
            int depth = maxDepth - 1;

            // .nextInt(lehetosegek szama)
            switch (rng.nextInt(11))
            {
                case 1: return new EEllen(makeNode(rng, depth), makeNode(rng, depth));
                case 2: return new EleCelzas(makeNode(rng, depth), makeNode(rng, depth));
                case 3: return new ESajat(makeNode(rng, depth), makeNode(rng, depth));
                case 4: return new Heat(makeNode(rng, depth), makeNode(rng, depth));
//              Heat: mukodik, de a lovest nem befolyasolja (logikailag)
                // , a mozgasra meg eleg fura lenne
                case 5: return new Kozeledes(makeNode(rng, depth), makeNode(rng, depth));
                case 6: return new SebessegEllen(makeNode(rng, depth), makeNode(rng, depth));
                case 7: return new SebessegEllenAtlag(makeNode(rng, depth), makeNode(rng, depth));
                case 0: return new SebessegSajat(makeNode(rng, depth), makeNode(rng, depth)); ///////////////////////!! Ezt ha egyedul
                case 8: return new SebessegSajatAtlag(makeNode(rng, depth), makeNode(rng, depth));
                case 9: return new XTavolsag(makeNode(rng, depth), makeNode(rng, depth));
                case 10: return new YTavolsag(makeNode(rng, depth), makeNode(rng, depth));

//                case 0: return new LottSzamSajat(makeNode(rng, depth), makeNode(rng, depth));
//                case 1: return new TalaltDb(makeNode(rng, depth), makeNode(rng, depth));
//                case 2: return new EltalaltDb(makeNode(rng, depth), makeNode(rng, depth));
//                case 3: return new AlfaEleCelzas(makeNode(rng, depth), makeNode(rng, depth));
//                case 6: return new GyorsulasEllen(makeNode(rng, depth), makeNode(rng, depth));
//                case 7: return new GyorsulasEllenAtlag(makeNode(rng, depth), makeNode(rng, depth));
//                case 9: return new PozicioEllenAtlag(makeNode(rng, depth), makeNode(rng, depth));
//                case 11: return new IranyValtozasEllen(makeNode(rng, depth), makeNode(rng, depth));
//                case 12: return new IranyValtozasAtlagEllen(makeNode(rng, depth), makeNode(rng, depth));
//                case 13: return new EnergiaValtozasEllen(makeNode(rng, depth), makeNode(rng, depth));
//                case 16: return new BetaEleCelzas(makeNode(rng, depth), makeNode(rng, depth));
//                case 17: return new Epsilon(makeNode(rng, depth), makeNode(rng, depth));
//                default: return new Or(makeNode(rng, depth), makeNode(rng, depth));


            }
        }
        //Ha level Node lesz amit épp készítünk
        else
        {
            //1. A levelek csak a paraméterükben fognak különbözni.
            //2. Erre az öröklésre a kódduplikáció elkerülése végett volt szükség. (Mármint erre az egész osztályra)
           return getSpecificLeafNode(); // ez is a kokret classsban fut le.
        }

        return null; //TODO: itt elvileg Node-ot vissza kene adni, de ide biztosan nem fog eljutni...
    }

}



