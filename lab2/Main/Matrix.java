package Main;

public class Matrix {

    private int size;
    private int[][] data;


    Matrix(int size) 
    {
        this.size = size;
        data = new int[size][size];
        for (int i = 0; i < size; ++i)
        {
            data[i][i] = 1;
        }
    }

    public int getElement(int row, int col)
    {
        return data[row][col];
    }

    public void setElement(int row, int col, int value)
    {
        data[row][col] = value;
    }


    public Matrix sum(Matrix other)
    {
        Matrix res = new Matrix(size);
        System.out.println("\nResult of add: \n");
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                res.setElement(i, j, this.getElement(i, j) + other.getElement(i, j));
            }
        }
        
        return res;
    }

    
    public Matrix product(Matrix other) 
    {
        Matrix res = new Matrix(size);

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {

                int sum = 0;

                for (int k = 0; k < size; ++k) {
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
        int size1 = this.size;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size1; ++i)
        {
            for (int j = 0; j < size1; ++j)
            {
                sb.append(data[i][j]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();       
    }

    Matrix doMiracle(int type)
    {
        if (type != 0 && type != 1)
        {
            throw new RuntimeException("Unsupported format");
        }

        Matrix result = new Matrix(size);
        
        if (type == 0)
        {
            for (int i = 0; i < size; ++i)
            {
                for (int j = 0; j < size; ++j)
                {
                    result.setElement(i, j, this.getElement(i, size - j -1));
                }
            }
        }
        else if (type == 1)
        {
            for (int i = 0; i < size; ++i)
            {
                for (int j = 0; j < size; ++j)
                {
                    result.setElement(i, j, this.getElement(size - i - 1, j));
                }
            }
        }

        return result;
    }
}
