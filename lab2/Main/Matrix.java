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
            for (int j = 0 ; j < size; ++j)
            {
                if (i == j)
                {
                    data[i][j] = 1;
                }
                else 
                {
                    data[i][j] = 0;
                }
            }

        }
    }

    public int get_element(int row, int col)
    {
        return data[row][col];
    }

    public void set_element(int row, int col, int value)
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
                res.set_element(i, j, this.get_element(i, j) + other.get_element(i, j));
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
                    sum += this.get_element(i, k) * other.get_element(k, j);
                }
                
                res.set_element(i, j, sum);
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
}
