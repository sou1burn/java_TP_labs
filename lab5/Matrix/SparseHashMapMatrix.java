package Matrix;

import java.util.HashMap;
import MatrixException.MatrixOperationException;


public class SparseHashMapMatrix implements IMatrix {
    
    private int rows;
    private int cols;

    private HashMap<Element, Integer> elems;

    public SparseHashMapMatrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.elems = new HashMap<>();
    }

    private static class Element
    {
        int row, col;

        public Element(int row, int col)
        {
            this.row = row;     
            this.col = col;
        }
        
        @Override
        public boolean equals(Object o)
        {

            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Element element = (Element) o;

            return row == element.row && col == element.col;
        }

        @Override
        public int hashCode()
        {
            return 31 * row + col;
        }
    }

    @Override
    public int getRows()
    {
        return this.rows;
    }

    @Override
    public int getCols()
    {
        return this.cols;
    }

    @Override
    public int getElement(int row, int col)
    {
        if (row < 0 || col < 0 || row >= this.rows || col >= this.cols)
        {
            throw new MatrixOperationException("Wrong row&col given");
        }
        return elems.getOrDefault(new Element(row, col), 0);
    }

    @Override
    public void setElement(int row, int col, int value)
    {
        if (row < 0 || col < 0 || row >= this.rows || col >= this.cols)
        {
            throw new MatrixOperationException("Wrong row&col given");
        }

        Element key = new Element(row, col);
        if (value == 0)
        {
            elems.remove(key);
        }
        else 
        {
            elems.put(key, value);
        }
    }


    @Override
    public IMatrix sum(IMatrix other)
    {
        if (this.rows != other.getRows() || this.cols != other.getCols())
        {
            throw new MatrixOperationException("Dimensions must be the same for sum");
        }

        SparseHashMapMatrix res = new SparseHashMapMatrix(rows, cols);


        for (int i = 0 ; i < other.getRows(); ++i)
        {
            for (int j = 0; j < other.getCols(); ++j)
            {
                int value = this.getElement(i, j) + other.getElement(i, j);
                res.setElement(i, j, value);
            }
        }
        return res;
    }

    @Override
    public IMatrix product(IMatrix other)
    {
        if (this.cols != other.getRows())
        {
            throw new MatrixOperationException("Dimensions must be the same for sum");
        }

        SparseHashMapMatrix res = new SparseHashMapMatrix(rows, cols);

        for (HashMap.Entry<Element, Integer> entry : elems.entrySet())
        {
            Element key = entry.getKey();

            int val = entry.getValue();

            for (int k = 0; k < other.getCols(); ++k)
            {
                int sum = res.getElement(key.row, k) + other.getElement(key.col, k) * val;
                res.setElement(key.row, k, sum);
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
            Element key = new Element(i, j);
            sb.append(elems.getOrDefault(key, 0)).append(" ");
        }
        sb.append("\n");
    }
    return sb.toString();
}

}
