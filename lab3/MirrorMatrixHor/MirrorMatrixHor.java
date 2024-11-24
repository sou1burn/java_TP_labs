package MirrorMatrixHor;

import Matrix.Matrix;
import MatrixException.BadMatrixSizesException;

public class MirrorMatrixHor extends Matrix {
    

    public MirrorMatrixHor(int rows, int cols)
    {
        super(rows / 2, cols);
        if (rows % 2 != 0)
        { 
            throw new BadMatrixSizesException("Matrix rows must be even for horizontal mirroring."); 
        } 
        
    }

    /*public MirrorMatrixHor(Matrix original)
    { 
        super(original.rows / 2, original.cols); 
         
        if (original.rows % 2 != 0)
        { 
            throw new BadMatrixSizesException("Matrix rows must be even for horizontal mirroring."); 
        } 

        for (int i = 0; i < this.rows; i++)
        { 
            for (int j = 0; j < this.cols; j++)
            { 
                this.data[i][j] = original.getElement(i, j); 
            } 
        } 
    }*/

    @Override
    public void setElement(int row, int col, int value)
    {
        validateIndices(row, col);

        int mirrored = row >= rows ? 2 * rows - row - 1 : row;

        data[mirrored][col] = value;
    }
    
    @Override
    public int getElement(int row, int col)
    {
        validateIndices(row, col);

        int mirrored = row >= rows ? 2 * rows - row - 1 : row; 
        return data[mirrored][col]; 
    }

    private void validateIndices(int row, int column)
    { 
        if (row < 0 || row >= rows * 2 || column < 0 || column >= cols)
        { 
            throw new BadMatrixSizesException("Index out of bounds"); 
        } 
    } 

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(); 

        for (int i = 0; i < this.rows; i++)
        { 
            for (int j = 0; j < this.cols; j++)
            { 
                sb.append(data[i][j]).append(" "); 
            } 
            sb.append("\n"); 
        } 
 
        for (int i = this.rows - 1; i >= 0; i--)
        { 
            for (int j = 0; j < this.cols; j++)
            { 
                sb.append(data[i][j]).append(" "); 
            } 
            sb.append("\n"); 
        } 
     
        return sb.toString(); 
    }
}
