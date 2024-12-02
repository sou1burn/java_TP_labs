package Matrix;

public interface IMatrix
{
    int getRows();
    int getCols();
    int getElement(int row, int col);
    void setElement(int row, int col, int value);
    IMatrix sum(IMatrix other);
    IMatrix product(IMatrix other);
    String toString();
}