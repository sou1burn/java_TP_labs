package Matrix;
import MatrixException.*;
import Matrix.IMatrix;

public class UsualMatrix implements IMatrix {
    
    protected int rows;
    protected int cols;
    protected int[][] data;

    public UsualMatrix(int rows, int cols)
    {
        if (rows <= 0 || cols <= 0)
        {
            throw new MatrixOperationException("Wrong sizes entered");
        }
        
        this.rows = rows;
        this.cols = cols;

        this.data = new int[rows][cols];
    }

    @Override
    public int getRows()
    {
        return rows;
    }

    @Override
    public int getCols()
    {
        return cols;
    }
    public int getElement(int row, int col)
    {
        if (row < 0 || row >= this.rows || col < 0 || col >= this.cols)
        {
            throw new MatrixOperationException("Index is out of matrix size");
        }
        return data[row][col];
    }

    public void setElement(int row, int col, int value)
    {
        if (row < 0 || row >= this.rows || col < 0 || col >= this.cols)
        {
            throw new MatrixOperationException("Index is out of matrix size");
        }

        data[row][col] = value;
    }

    @Override
    public IMatrix sum(IMatrix other)
    {

        if (rows != other.getRows() || cols != other.getCols())
        {
            
            throw new MatrixOperationException("Cannot sum matrices with different sizes");
        }

        UsualMatrix res = new UsualMatrix(rows, cols);
        for (int i = 0; i < res.rows; ++i)
        {
            for (int j = 0; j < res.cols; ++j)
            {
                res.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
            }
        }
        
        return res;
    }
    @Override
    public IMatrix product(IMatrix other) 
    {

        if (cols != other.getRows())
        {
            throw new MatrixOperationException("Cannot sum matrices with different sizes");
        }

        UsualMatrix res = new UsualMatrix(this.rows, other.getCols());

        for (int i = 0; i < res.rows; ++i) {
            for (int j = 0; j < res.cols; ++j) {

                int sum = 0;

                for (int k = 0; k < this.cols; ++k) {
                    sum += this.getElement(i, k) * other.getElement(k, j);
                }

                res.setElement(i, j, sum);
            }
        }

        return res;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int[] row : data)
        { 
            for (int value : row)
            { 
                sb.append(value).append(" "); 
            } 
            sb.append("\n"); 
        } 

        return sb.toString();
    }

}