package mappasrc;

import robocode.ScannedRobotEvent;
import sample.Evobot;

/**
 * Created by Zoltán on 2014.04.23..
 */
public class Sensing {
    double Vellen;
    double Vsajat;
    double d = 0;       //ellensegtol valo tavolsag
    double Eellen;
    double Esajat;
    boolean LOTT;   //Heat miatt nincs értelme - Heat van helyette csak
    double Iellen;  // lehetne Body-hoz és Gunhoz képest is.
                    // double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
                    // Head-on targeting-ben.
    double PellenX; // RoboWikis link
    double PellenY;
    public double Isajat;
    double PsajatX;
    double PsajatY;
    double Bear_RAD;
    double Lov;     //felesleges - a fitnesst mashonnan tudjuk meg.
    double Heat;

    public Sensing(ScannedRobotEvent event) {
        Vellen = event.getVelocity();
        //Vsajat
        d = event.getDistance();
        Eellen = event.getEnergy();
        //Esajat
        //Iellen - teljesen hianyzik
        //PellenX- RoboWiki
        //PellenY
        Bear_RAD = event.getBearingRadians();
    }

    public void setMyDatas(Evobot evobot) {
        Vsajat = evobot.getVelocity();
        Esajat = evobot.getEnergy();
        PsajatX= evobot.getX();
        PsajatY= evobot.getY();
        Heat   = evobot.getGunHeat();
    }
}

