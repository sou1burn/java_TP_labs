package Matrix;
import MatrixException.MatrixOperationException;
import MirrorMatrixHor.MirrorMatrixHor;
public class Matrix {

    protected final int rows;
    protected final int cols;
    protected final int[][] data;


    public Matrix(int rows, int cols) 
    {
        if (rows < 0 || cols < 0)
        {
            throw new MatrixOperationException("Rows & cols must be positive integers");
        }
        
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];

        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0 ; j < cols; ++j)
            {
                data[i][j] = 0;
            }
        }
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
  
    public Matrix sum(Matrix other)
    {
        int row = this.rows;

        if (other.getClass() == MirrorMatrixHor.class || this.getClass() == MirrorMatrixHor.class)
        {
            row *= 2;
        }
        if (this.rows != other.rows || this.cols != other.cols)
        {
            if (other.getClass() == MirrorMatrixHor.class)
            {
                if (this.rows != other.rows * 2)
                {
                    throw new MatrixOperationException("Cannot sum matrices with different sizes");
                }
            }
            else if (this.getClass() == MirrorMatrixHor.class)
            {
                if ( other.rows != this.rows* 2 )
                {
                    throw new MatrixOperationException("Cannot sum matrices with different sizes");
                }
            }
            else
            {
                throw new MatrixOperationException("Cannot sum matrices with different sizes");
            } 
        }

        Matrix res = new Matrix(row, cols);
        for (int i = 0; i < res.rows; ++i)
        {
            for (int j = 0; j < res.cols; ++j)
            {
                res.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
            }
        }
        
        return res;
    }

    
    public final Matrix product(Matrix other) 
    {
        int row = this.rows;

        if (other.getClass() == MirrorMatrixHor.class || this.getClass() == MirrorMatrixHor.class)
        {
            row *= 2;
        }
        if (this.rows != other.rows || this.cols != other.cols)
        {
            if (other.getClass() == MirrorMatrixHor.class)
            {
                if (this.rows != other.rows * 2)
                {
                    throw new MatrixOperationException("Cannot sum matrices with different sizes");
                }
            }
            else if (this.getClass() == MirrorMatrixHor.class)
            {
                if ( other.rows != this.rows* 2 )
                {
                    throw new MatrixOperationException("Cannot sum matrices with different sizes");
                }
            }
            else
            {
                throw new MatrixOperationException("Cannot sum matrices with different sizes");
            } 
        }

        Matrix res = new Matrix(row, other.cols);

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

    
    @Override
    public final boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Matrix other = (Matrix) o;
        
        if (this.rows != other.rows || this.cols != other.cols)
        {
            return false;
            //throw new MatrixOperationException("Matrixes sizes should be equal");
        }

        for (int i = 0; i < this.rows; ++i)
        {
            for (int j = 0; j < this.cols; ++j)
            {
                if (this.data[i][j] != other.data[i][j]){
                    return false;
                }
            }
        }

        return true;
    }   
}