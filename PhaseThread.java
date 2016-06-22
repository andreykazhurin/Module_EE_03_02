package Module_EE_03_02;

import java.util.List;
import java.util.concurrent.Phaser;

/**
 * Created by Andrey on 14.06.2016.
 */
public class PhaseThread implements Runnable{

    private Phaser phaser;
    private String name;
    private int[] values;
    private List<Long> results;


    public PhaseThread(Phaser p, String n, int[] values, List<Long> results){

        this.phaser = p;
        this.name   = n;
        this.values = values;
        this.results = results;
        phaser.register();


    }
    public void run(){
        //System.out.println(Thread.currentThread().getName() + " arrived");
        long result = SquareSumImpl.getSquareSumPart(values);
        //System.out.println(Arrays.toString(values));
        //System.out.println(Thread.currentThread().getName() + " result = " + result);
        results.add(result);
        phaser.arriveAndDeregister();
        //System.out.println(Thread.currentThread().getName() + " after passing barrier");
    }

}
