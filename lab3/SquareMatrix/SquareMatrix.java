package SquareMatrix;

import Matrix.Matrix;
import MatrixException.MatrixOperationException;

public final class SquareMatrix extends Matrix {

    public SquareMatrix(int size) 
    {
        super(size, size);

        for (int i = 0 ; i < size; ++i)
        {
            setElement(i, i, 1);
        }
    }


    @Override
    public Matrix sum(Matrix other)
    {
        return super.sum(other);
    }
}
