package Main;
import UsualMatrix.*;
import ParallelMatrixProduct.*;

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
    }


    public int calcQueenNum(int N, int threadCount) {
        UsualMatrix board = new UsualMatrix(N, N);

        Thread[] threads = new Thread[threadCount];
        int solutions = 0;

        for (int i = 0; i < threadCount; ++i) {
            final int startCol = i;
            if (startCol >= N) break;
        }
    }

    public void solve(UsualMatrix board, int row, int N) {
        
    }

    public boolean isSafePosition(UsualMatrix board, int row, int col, int N) {
        for (int i = 0; i < row; ++i) {
            if (board.getElement(row, col) == 1) return false;
            int leftDiag = col - (row - i);
            int rightDiag = col + (row - i);
            if (leftDiag >= 0 && board.getElement(i, leftDiag) == 1) return false;
            if (rightDiag < N && board.getElement(i, rightDiag) == 1) return false;
        }
        return true;
    }
}
