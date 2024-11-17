package MirrorMatrixHor;
import Matrix.Matrix;
import MatrixException.BadMatrixSizesException;

public class MirrorMatrixHor extends Matrix {
    

    public MirrorMatrixHor(int rows, int cols)
    {
        super(rows, cols);

        this.data = new int[rows][];

        for (int i = 0; i < rows; ++i)
        {
            data[i] = new int[cols - i];
        }
    }

    @Override
    public void setElement(int row, int col, int value)
    {
         if (row < 0 || row >= this.rows || col < 0 || col >= this.cols)
        {
            throw new BadMatrixSizesException("Index is out of matrix size");
        }
        if (row <= col)
        {
            data[row][col - row] = value;
        }
        else
        {
            data[col][row - col] = value;
        }
    }
    
    @Override
    public int getElement(int row, int col)
    {
        if (row < 0 || row >= this.rows || col < 0 || col >= this.cols)
        {
            throw new BadMatrixSizesException("Index is out of matrix size");
        }

        if (row <= col)
        {
            return data[row][col - row];
        }
        else {
            return data[col][row - col];
        }
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(getElement(i, j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
