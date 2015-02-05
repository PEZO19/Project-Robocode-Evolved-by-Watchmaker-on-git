package mappasrc;

import mappasrc.util.Fifo;

import java.util.HashMap;

/**
 * Created by Zoltán on 2014.04.23..
 */

//alahuzassal jelolom a Fifokat
public class BeliefBase {

    static public HashMap<String,Fifo> fifok = new HashMap<String,Fifo>();

    //Fifo _Aatlag;
    //Fifo _Vsajat_atlag;
    //Fifo _Pellen_atlagX;
    //Fifo _alfa2;    //db lövésre visszamenőleg (nem turnre)
    static int t = 100; // ennyi turnre visszamenoleg

    static int db = 1; // ennyi lovesre visszamenoleg - ideiglenesen csak ennek van ertelme //TODO: - belso nodeok (alfa2 és epszilon) -nak 2 parameterrel kellrendelkezzen
    //static int _LOTT_DB_SAJAT = 30; //Fifo kell legyen, csak kell hozzá get
    //Fifo _ELTALALTAM_DB;
    //Fifo _ELTALALT_DB;
    static double alpha;
    public static double v_per_d; // EZT IROM MEG PELDANAK
    //Fifo _Vellen_atlag;
    static double Aellen;

    //Fifo _Pellen_atlagY;
    public static double delta_d; // 2 turnre
    static double delta_Iellen; // 2 turnre

    //Fifo _delta_Ie_atlag;
    static double delta_E;
    public static double x_tav;
    public static double y_tav;

    //Fifo _epszilon; //db lövésre visszamenőleg (nem turnre)
    public static double heat = 0;
    public static double Esajat = 100;
    public static double Eellen = 100;


    //elozo es mostani kor erzekeleseihez osszevetesehez
    static double d = 0; //random kezdeti ertek
    public static double Vellen;
    public static double Vsajat;


    //
    public static void init(){
        fifok.put("_LOTT_DB_SAJAT", new Fifo(t));
        fifok.put("_ELTALALTAM_DB", new Fifo(t));
        fifok.put("_ELTALALT_DB",   new Fifo(t));
        fifok.put("_Vellen_atlag",  new Fifo(t));
        fifok.put("_Aatlag",        new Fifo(t));
        fifok.put("_Vsajat_atlag",  new Fifo(t));
        fifok.put("_Pellen_atlagX", new Fifo(t));
        fifok.put("_Pellen_atlagY", new Fifo(t));
        fifok.put("_delta_Ie_atlag",new Fifo(t));
        fifok.put("_alfa2",         new Fifo(db));
        fifok.put("_epszilon",      new Fifo(db));
    }

//TODO: midnen static
    public static double get_LOTT_DB_SAJAT() {
        //return _LOTT_DB_SAJAT; //Ezt kell atirni AGGREGALT-ra - eredeti inteshez
        return fifok.get("_LOTT_DB_SAJAT").getSum(); //feltetelezve, hogy a Fifoban 0 és 1 van
    }

    public static double get_ELTALALTAM_DB() {
        return fifok.get("_ELTALALTAM_DB").getSum();
    }

    public static double get_ELTALALT_DB() {
        return fifok.get("_ELTALALT_DB").getSum();
    }

    public static double getAlpha() {
        return alpha;
    }

    public static double getV_per_d() {
        return v_per_d;
    }

    public static double get_Vellen_atlag() {
        return fifok.get("_Vellen_atlag").avg;

    }

    public static double getAellen() {
        return Aellen;
    }

    public static double get_Aatlag() {
        return fifok.get("_Aatlag").avg;
    }

    public static double get_Vsajat_atlag() {
        return fifok.get("_Vsajat_atlag").avg;
    }

    public static double get_Pellen_atlagX() {
        return fifok.get("_Pellen_atlagX").avg;
    }

    public static double get_Pellen_atlagY() {
        return fifok.get("_Pellen_atlagY").avg;
    }

    public static double getDelta_d() {
        return delta_d;
    }

    public static double getDelta_Iellen() {
        return delta_Iellen;
    }

    public static double get_delta_Ie_atlag() {
        return fifok.get("_delta_Ie_atlag").avg;
    }

    public static double getDelta_E() {
        return delta_E;
    }

    public static double getX_tav() {
        return x_tav;
    }

    public static double getY_tav() {
        return y_tav;
    }

    public static double get_alfa2() {
        return fifok.get("_alfa2").avg;
        // egyelore db=1 esetén vizsgaljuk, külön lenne érdemes őket kezelni, azaz
        // utolso elotti amit lottem : sokkal ele
        // ÉS utolso amit lottem     : kicsit ele
        //  =>
        // most pont ra fogok celozni
    }

    public static double get_epszilon() {
        return fifok.get("_epszilon").avg; //mint alfa2
    }
}
