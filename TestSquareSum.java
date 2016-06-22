package Module_EE_03_02;

/**
 * Created by Andrey on 14.06.2016.
 */
public class TestSquareSum {

    public static void main(String[] args) {
        int[] array = new int[106];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        SquareSumImpl squareSum = new SquareSumImpl();

        long result = squareSum.getSquareSum(array, 10);

        System.out.println("Total result - " + result);


    }
}
