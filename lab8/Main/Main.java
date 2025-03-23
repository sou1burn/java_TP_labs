package Main;
import UsualMatrix.*;
import ParallelMatrixProduct.*;
import ParallelNQueens.*;

public class Main { 
    public static void main(String[] args) {

        UsualMatrix m1 = new UsualMatrix(500, 500);
        UsualMatrix m2 = new UsualMatrix(500, 500);
        m1.generateRandomMatrix(500, 500);
        m2.generateRandomMatrix(500, 500);

        long startTime = System.currentTimeMillis();
        m1.product(m2);
        long endTime = System.currentTimeMillis();
        System.out.println("Умножение в одном потоке: " + (endTime - startTime) + " ms");

        ParallelMatrixProduct parallelProduct = new ParallelMatrixProduct(1);
        startTime = System.currentTimeMillis();
        parallelProduct.product(m1, m2);
        endTime = System.currentTimeMillis();
        System.out.println("Параллельное умножение в " + parallelProduct.getThreadCount() +" потоках: " + (endTime - startTime) + " ms");

        int N = 8; 
        int threadCount = Math.min(N, 4); 
        ParallelNQueens solver = new ParallelNQueens(threadCount);
        int solutions = solver.calcQueenNum(N);
        System.out.println("Total solutions: " + solutions);
    }
}
