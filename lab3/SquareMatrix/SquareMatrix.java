package SquareMatrix;

import Matrix.Matrix;
import MatrixException.MatrixOperationException;

public final class SquareMatrix extends Matrix {

    public SquareMatrix(int size) 
    {
        super(size, size);

        for (int i = 0 ; i < size; ++i)
        {
            data[i][i] = 1;
        }
    }


    @Override
    public Matrix sum(Matrix other)
    {
        if (!(other instanceof SquareMatrix))
        {
            throw new MatrixOperationException("Both matrixes should be square for sum!");
        }

        return super.sum(other);
    }
}
