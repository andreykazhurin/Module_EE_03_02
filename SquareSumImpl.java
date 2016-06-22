package Module_EE_03_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * Created by Andrey on 13.06.2016.
 */
public class SquareSumImpl implements SquareSum{


    private long totalResult = 0;

    @Override
    public long getSquareSum(int[] values, int numberOfThreads) {

        if (numberOfThreads > values.length) {
            throw new IllegalArgumentException();
        }

        final Phaser phaser = new Phaser(1);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        List<Long> results = Collections.synchronizedList(new ArrayList<Long>());


        int m = numberOfThreads;
        int n = values.length;

        int parts1 = n/m;
        int parts2 = n/m+1;
        int countPart1 = n/m-n%m;
        int countPart2 = n%m;

        int generalIndex = 0;

        for (int i = 0; i < countPart1; i++) {
            int[] partValue = Arrays.copyOfRange(values, generalIndex, generalIndex + parts1);
            executorService.execute(new PhaseThread(phaser, "PhaseThread1_" + i, partValue, results));
            generalIndex += parts1;
        }

        for (int i = 0; i < countPart2; i++) {
            int[] partValue = Arrays.copyOfRange(values, generalIndex, generalIndex + parts2);
            executorService.execute(new PhaseThread(phaser, "PhaseThread2_" + i, partValue, results));
            generalIndex += parts2;
        }


        phaser.arriveAndAwaitAdvance();
        phaser.arriveAndDeregister();
        executorService.shutdown();

        results.forEach(res->totalResult+=res);

        return totalResult;

    }

    public static long getSquareSumPart(int[] values){
        long result = 0;
        for(int i = 0; i < values.length; i++){
            result += Math.pow(values[i], 2);
        }
        return result;
    }
}
