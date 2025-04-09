package ParallelNQueens;

import java.util.concurrent.atomic.AtomicInteger;
import UsualMatrix.*;

public class ParallelNQueens {
    private int threadCount;
    private AtomicInteger solutions = new AtomicInteger(0);

    public ParallelNQueens(int threads) {
        this.threadCount = threads;
    }

    public int calcQueenNum(int N) {
        solutions.set(0);
        int tasks = Math.min(N, threadCount); 
        Thread[] threadPool = new Thread[tasks];
        
        for (int col = 1; col < N + 1; col++) {
            final int startCol = col;
            if (threadCount != 1) {
                threadPool[col / tasks] = new Thread(() -> {
                    UsualMatrix board = new UsualMatrix(N, N);
                    board.setElement(0, startCol - 1, 1);
                    solve(board, 1, N);
                });
                threadPool[col / tasks].start();
            } else {
                UsualMatrix board = new UsualMatrix(N, N);
                board.setElement(0, startCol - 1, 1);
                solve(board, 1, N);
            }
        }

        for (int i = 0; i < tasks; ++i) {
            try {
                if (threadPool[i] != null) {
                    threadPool[i].join();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return solutions.get();
    }

    private void solve(UsualMatrix board, int row, int N) {
        if (row == N) {
            solutions.incrementAndGet();
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafePosition(board, row, col, N)) {
                UsualMatrix newBoard = copyMatrix(board, N);
                newBoard.setElement(row, col, 1);
                solve(newBoard, row + 1, N);
            }
        }
    }

    private boolean isSafePosition(UsualMatrix board, int row, int col, int N) {
        for (int i = 0; i < row; i++) {
            if (board.getElement(i, col) == 1) 
                return false;
            int leftDiag = col - (row - i);
            int rightDiag = col + (row - i);
            if (leftDiag >= 0 && board.getElement(i, leftDiag) == 1) 
                return false;
            if (rightDiag < N && board.getElement(i, rightDiag) == 1) 
                return false;
        }
        return true;
    }

    private UsualMatrix copyMatrix(UsualMatrix board, int N) {
        UsualMatrix copy = new UsualMatrix(N, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                copy.setElement(i, j, board.getElement(i, j));
        }
        return copy;
    }
}
