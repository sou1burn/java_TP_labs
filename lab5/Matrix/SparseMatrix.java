package Matrix;

import MatrixException.MatrixOperationException;
import java.util.LinkedList;
import java.util.ListIterator;


public class SparseMatrix implements IMatrix{
    
    public static final int k = 10;
    private int rows;
    private int cols;
    private LinkedList<Cell> elems;

    private static class Cell
    {
        int row;
        int col;
        int val;

        Cell(int row, int col, int val)
        {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public SparseMatrix(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.elems = new LinkedList<Cell>();
    }

    @Override 
    public int getCols()
    {
        return this.cols;
    }

    @Override
    public int getRows()
    {
        return this.rows;
    }

    @Override
    public int getElement(int row, int col)
    {
        if (row < 0 || col < 0 || row >= this.rows || col >= this.cols)
        {
            throw new MatrixOperationException("Wrong row&col given");
        }

        for (Cell cell : elems)
        {
            if (cell.row == row && cell.col == col)
            {
                return cell.val;
            }
        }

        return 0;
    }

    @Override
    public void setElement(int row, int col, int value)
    {
        if (value == 0)
        {
            return;
        }

        if (row < 0 || col < 0 || row >= this.rows || col >= this.cols)
        {
            throw new MatrixOperationException("Wrong row&col given");
        }
        
        ListIterator<Cell> it = elems.listIterator();
        while (it.hasNext())
        {
            Cell cell = it.next();
            if(cell.row == row && cell.col == col)
            {
                cell.val = value;
                return;
            }
        }

        if (value != 0)
        {
           it.add(new Cell(row, col, value));
        }
    }

    @Override
    public IMatrix sum(IMatrix other)
    {
        if (this.rows != other.getRows() || this.cols != other.getCols())
        {
            throw new MatrixOperationException("Dimensions must be the same for sum");
        }

        SparseMatrix res = new SparseMatrix(this.rows, this.cols);

        for (int i = 0; i < other.getRows() ; ++i)
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
            throw new MatrixOperationException("First cols must be equal to second rows");
        }

        SparseMatrix res = new SparseMatrix(rows, other.getCols());

        for (Cell cell : elems)
        {
            for (int k = 0; k < other.getCols(); ++k)
            {
                int value = res.getElement(cell.row, k) + cell.val * other.getElement(cell.col, k);
                if (value != 0)
                {
                    res.setElement(cell.row, k, value);
                }
            }
        }

        return res;
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
