package Main;
import UsualMatrix.*;
import ParallelMatrixProduct.*;

public class Main { 
    public static void main(String[] args) {

        UsualMatrix m1 = new UsualMatrix(1000, 1000);
        UsualMatrix m2 = new UsualMatrix(1000, 1000);
        m1.generateRandomMatrix(1000, 1000);
        m2.generateRandomMatrix(1000, 1000);

        long startTime = System.currentTimeMillis();
        UsualMatrix result1 = m1.product(m2);
        long endTime = System.currentTimeMillis();
        System.out.println("Умножение в одном потоке: " + (endTime - startTime) + " ms");

        ParallelMatrixProduct parallelProduct = new ParallelMatrixProduct(4);
        startTime = System.currentTimeMillis();
        UsualMatrix result2 = parallelProduct.product(m1, m2);
        endTime = System.currentTimeMillis();
        System.out.println("Умножение в нескольких потоках: " + (endTime - startTime) + " ms");
    }

}
