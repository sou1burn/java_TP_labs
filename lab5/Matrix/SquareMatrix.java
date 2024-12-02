package Matrix;

import MatrixException.MatrixOperationException;



public class SquareMatrix extends UsualMatrix
{
    protected int rows;
    protected int cols;
    protected int[][] data;


    public SquareMatrix(int size)
    {
        super(size, size);

        if (size <= 0)
        {
            throw new MatrixOperationException("Incorrect Square matrix size");
        }

        for (int i = 0 ; i < size; ++i)
        {
            setElement(i, i, 1);
        }
        
    }

}