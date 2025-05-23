package Main;
import UsualMatrix.*;
import ParallelMatrixProduct.*;
import ParallelNQueens.*;

public class Main { 
    public static void main(String[] args) {

        UsualMatrix m1 = new UsualMatrix(1000, 1000);
        UsualMatrix m2 = new UsualMatrix(1000, 1000);
        m1.generateRandomMatrix(1000, 1000);
        m2.generateRandomMatrix(1000, 1000);

        long startTime = System.currentTimeMillis();
        m1.product(m2);
        long endTime = System.currentTimeMillis();
        System.out.println("Умножение в одном потоке: " + (endTime - startTime) + " ms");

        ParallelMatrixProduct parallelProduct = new ParallelMatrixProduct(4);
        startTime = System.currentTimeMillis();
        parallelProduct.product(m1, m2);
        endTime = System.currentTimeMillis();
        System.out.println("Параллельное умножение в " + parallelProduct.getThreadCount() +" потоках: " + (endTime - startTime) + " ms");

        int N = 12; 
        long startQueensParallel = System.currentTimeMillis();
        ParallelNQueens solver = new ParallelNQueens(4);
        int solutions = solver.calcQueenNum(N);
        long endQuennsParallel = System.currentTimeMillis();
        System.out.println("Total solutions: " + solutions + " Duration: " + (endQuennsParallel - startQueensParallel) + " ms, threads: " + 4);

        long startQueens = System.currentTimeMillis();
        ParallelNQueens nonThreadSolver = new ParallelNQueens(1);
        long notThreadSolutions = nonThreadSolver.calcQueenNum(N);
        long endQuenns = System.currentTimeMillis();
        System.out.println("Total solutions: " + notThreadSolutions + " Duration: " + (endQuenns - startQueens) + " ms");
    }
}
