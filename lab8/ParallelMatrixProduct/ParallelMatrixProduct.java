package ParallelMatrixProduct;

import UsualMatrix.UsualMatrix;

public class ParallelMatrixProduct {
    private int threadCount;

    public ParallelMatrixProduct(int threadCount) {
        this.threadCount = threadCount;
    }

    public UsualMatrix product(UsualMatrix m1, UsualMatrix m2) {

        if (m1.getCols() != m2.getRows()) {
            throw new RuntimeException("count of cols in m1 must be equal to m2 rows");
        }

        UsualMatrix res = new UsualMatrix(m1.getRows(), m2.getCols());
        Thread[] threads = new Thread[threadCount];

        int rowsPerThread = (int) Math.ceil((double) m1.getRows() / threadCount);

        for (int i = 0; i < threadCount; ++i) {
            int startRow = i * rowsPerThread;
            int endRow =  Math.min(startRow + rowsPerThread, m1.getRows());

            threads[i] = new Thread(() -> {
                for (int row = startRow; row < endRow; row++) {
                    for (int col = 0; col < m2.getCols(); col++) {
                        int sum = 0;
                        for (int k = 0; k < m1.getCols(); k++) {
                            sum += m1.getElement(row, k) * m2.getElement(k, col);
                        }
                        res.setElement(row, col, sum);
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return res;
    }
}
