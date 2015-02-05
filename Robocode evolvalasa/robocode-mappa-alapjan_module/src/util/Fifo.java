package mappasrc.util;

import java.util.LinkedList;

/**
 * Created by Zoltán on 2014.05.06..
 */
public class Fifo{

    public int fifoMaxSize;
    public LinkedList<Double> fifo = new LinkedList<Double>();

    public double sum;
    public double avg;

    public Fifo(int size) {
        fifoMaxSize = size;
        sum = 0;
    }

    public Fifo() {
        //TODO: EZT hivja a Fifo HashMap
    }

    public double getSum() {
        return sum;
    }

    public void add(double input){
        //System.out.println("adding");
        //nincs tele
        if (fifo.size() < fifoMaxSize) {
            fifo.add(input);
            sum += input;
        }
        // epp tele
        else {
            //System.out.println("removing");
            sum -= fifo.removeFirst();
            fifo.add(input);
            sum += input;
        }

        avg = (double)sum/fifo.size();
    }

    public void refresh(){
        fifo.clear();
        sum = 0;
        avg = 0;
    }

}