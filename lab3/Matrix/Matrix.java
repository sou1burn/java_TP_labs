package Matrix;
import MatrixException.MatrixOperationException;
public class Matrix {

    protected int rows;
    protected int cols;
    protected int[][] data;


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
        if (this.rows != other.rows || this.cols != other.cols)
        {
            throw new MatrixOperationException("Matrixes sizes should be equal");
        }

        Matrix res = new Matrix(rows, cols);
        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < cols; ++j)
            {
                res.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
            }
        }
        
        return res;
    }

    
    public final Matrix product(Matrix other) 
    {
        if (this.cols != other.rows)
        {
            throw new MatrixOperationException("Number of cols should be equal to number of rows in another matrix");
        }

        Matrix res = new Matrix(this.rows, other.cols);

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < other.cols; ++j) {

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
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                sb.append(data[i][j]).append(" ");
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