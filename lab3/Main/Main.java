package Main;

import Matrix.Matrix;
import SquareMatrix.SquareMatrix;
import MatrixException.BadMatrixSizesException;
import MatrixException.MatrixOperationException;
import MirrorMatrixHor.MirrorMatrixHor;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Создание матрицы 2x3:");
            Matrix m1 = new Matrix(2, 3);
            m1.setElement(0, 0, 1);
            m1.setElement(0, 1, 2);
            m1.setElement(0, 2, 3);
            m1.setElement(1, 0, 4);
            m1.setElement(1, 1, 5);
            m1.setElement(1, 2, 6);

            System.out.println("Матрица m1 (2x3):");
            System.out.println(m1);

            System.out.println("Создание матрицы 2x3 для сложения с m1:");
            Matrix m2 = new Matrix(2, 3);
            m2.setElement(0, 0, 6);
            m2.setElement(0, 1, 5);
            m2.setElement(0, 2, 4);
            m2.setElement(1, 0, 3);
            m2.setElement(1, 1, 2);
            m2.setElement(1, 2, 1);

            System.out.println("Матрица m2 (2x3):");
            System.out.println(m2);

            Matrix sum = m1.sum(m2);
            System.out.println("Сумма матриц m1 и m2 (2x3):");
            System.out.println(sum);

            
            try
            {
                Matrix m3 = new Matrix(4, 3);
                System.out.println("Попытка умножения несовместимых матриц m1 и m3 (2x3 * 4x3):");
                Matrix product = m1.product(m3);
                System.out.println(product);
            }
            catch (MatrixOperationException e)
            {
                System.err.println("Ошибка при умножении матриц: " + e.getMessage());
            }

        }
        catch (MatrixOperationException e)
        {
            System.err.println("Ошибка: " + e.getMessage());
        }
        
        try
        {
            System.out.println("\nСоздание квадратной матрицы размером 3x3:");
            SquareMatrix sm1 = new SquareMatrix(3);
            System.out.println("Квадратная матрица sm1 (единичная матрица):");
            System.out.println(sm1);

            SquareMatrix sm2 = new SquareMatrix(3);
            System.out.println("Создание второй квадратной матрицы размером 3x3 (единичная матрица):");
            System.out.println(sm2);

            SquareMatrix sm3 = new SquareMatrix(2);
            System.out.println("Проверка суммирования квадратных матриц разных размеров");
            System.out.println(sm3.sum(sm2));

            Matrix sumSquares = sm1.sum(sm2);
            System.out.println("Сумма квадратных матриц sm1 и sm2:");
            System.out.println(sumSquares);

            
            try
            {
                Matrix m1_t = new Matrix(2, 3);

                boolean equals_test = sm1.equals(m1_t);
                System.out.println(equals_test);
                boolean equals_test1 = sm1.equals(sm2);
                System.out.println(equals_test1);
                
                System.out.println("Попытка сложения квадратной матрицы sm1 и матрицы m1:");
                Matrix sumInvalid = sm1.sum(new Matrix(2, 3));
                System.out.println(sumInvalid);
                

            }
            catch (MatrixOperationException e)
            {
                System.err.println("Ошибка при сложении матриц: " + e.getMessage());
            }

        }
        catch (MatrixOperationException e)
        {
            System.err.println("Ошибка: " + e.getMessage());
        }

        try
        {
            Matrix mA = new Matrix(3, 3);
            mA.setElement(0, 0, 34);
            mA.setElement(0, 1, 34);
            mA.setElement(0, 2, 34);
            mA.setElement(1, 0, 34);
            mA.setElement(1, 1, 34);
            mA.setElement(1, 2, 34);
            mA.setElement(2, 0, 34);
            mA.setElement(2, 1, 34);
            mA.setElement(2, 2, 34);

            Matrix mB = new Matrix(3, 3);
            mB.setElement(0, 0, 34);
            mB.setElement(0, 1, 34);
            mB.setElement(0, 2, 34);
            mB.setElement(1, 0, 34);
            mB.setElement(1, 1, 34);
            mB.setElement(1, 2, 34);
            mB.setElement(2, 0, 34);
            mB.setElement(2, 1, 34);
            mB.setElement(2, 2, 34);

            System.out.println("A equals B? " + mA.equals(mB));

            Matrix mB1 = new Matrix(2, 2);
            mB1.setElement(0, 0, 34);
            mB1.setElement(0, 1, 34);
            mB1.setElement(1, 0, 3);
            mB1.setElement(1, 1, 34);

            System.out.println("A equals B? " + mA.equals(mB1));
        }
        catch (MatrixOperationException e)
        {
            System.err.println("Error: " + e.getMessage());
        }

        try 
        {
            Matrix m10 = new Matrix(3, 3);
            SquareMatrix m11 = new SquareMatrix(3);

            m10.setElement(0, 0, 1);
            m10.setElement(0, 1, 1);
            m10.setElement(1, 1, 1);
            m10.setElement(2, 2, 1);

            Matrix m12 = m10.sum(m11);
            System.out.println("Matrix m10: ");
            System.out.println(m10.toString());

            System.out.println("Matrix m11: ");
            System.out.println(m11.toString());

            System.out.println("Trying m10 + square m11");

            System.out.println(m12.toString());

            Matrix m13 = m10.product(m11);

            System.out.println("Trying m10 * square m11");
            System.out.println(m13.toString());

            SquareMatrix m14 = new SquareMatrix(2);
            System.out.println("SquareMatrix m14: ");
            System.out.println(m14.toString());

            System.out.println("Trying m10 * square m14");
            System.out.println(m10.product(m14));
        }
        catch (MatrixOperationException e)
        {
            System.err.println("Error:" + e.getMessage());
        }

        try
        {
            MirrorMatrixHor matrix = new MirrorMatrixHor(4, 4);

            matrix.setElement(0, 0, 1);
            matrix.setElement(0, 1, 2);
            matrix.setElement(1, 0, 3);
    
            System.out.println("Matrix after setting upper half:");
            System.out.println(matrix);
    
            System.out.println("Mirror test:");
            System.out.println("Element at (0, 1): " + matrix.getElement(0, 1)); 
            System.out.println("Element at (3, 1): " + matrix.getElement(3, 1));
            
        }
        catch (BadMatrixSizesException e)
        {
            System.err.println("Error: " + e.getMessage()) ;
        }
        try
        {
            System.out.println("\n=== Проверка операций MirrorMatrixHor ===");
            
            MirrorMatrixHor mirrorMatrix = new MirrorMatrixHor(4, 4);
            mirrorMatrix.setElement(0, 0, 1);
            mirrorMatrix.setElement(0, 1, 2);
            mirrorMatrix.setElement(0, 2, 3);
            mirrorMatrix.setElement(0, 3, 4);
            mirrorMatrix.setElement(1, 0, 5);
            mirrorMatrix.setElement(1, 1, 6);
            mirrorMatrix.setElement(1, 2, 7);
            mirrorMatrix.setElement(1, 3, 8);
        
            System.out.println("MirrorMatrixHor (4x4):");
            System.out.println(mirrorMatrix);
        
            Matrix regularMatrix = new Matrix(4, 4);
            regularMatrix.setElement(0, 0, 9);
            regularMatrix.setElement(0, 1, 8);
            regularMatrix.setElement(0, 2, 7);
            regularMatrix.setElement(0, 3, 6);
            regularMatrix.setElement(1, 0, 5);
            regularMatrix.setElement(1, 1, 4);
            regularMatrix.setElement(1, 2, 3);
            regularMatrix.setElement(1, 3, 2);
            regularMatrix.setElement(2, 0, 1);
            regularMatrix.setElement(2, 1, 0);
            regularMatrix.setElement(2, 2, -1);
            regularMatrix.setElement(2, 3, -2);
            regularMatrix.setElement(3, 0, -3);
            regularMatrix.setElement(3, 1, -4);
            regularMatrix.setElement(3, 2, -5);
            regularMatrix.setElement(3, 3, -6);
        
            System.out.println("Matrix (4x4):");
            System.out.println(regularMatrix);
        
            
            System.out.println("\nСложение MirrorMatrixHor и Matrix:");
            Matrix sumResult = mirrorMatrix.sum(regularMatrix);
            System.out.println("Результат:");
            System.out.println(sumResult);
        
            System.out.println("\nУмножение MirrorMatrixHor на Matrix:");
            Matrix productResult = mirrorMatrix.product(regularMatrix);
            System.out.println("Результат:");
            System.out.println(productResult);
        
            SquareMatrix squareMatrix = new SquareMatrix(4);
            squareMatrix.setElement(0, 0, 1);
            squareMatrix.setElement(1, 1, 1);
            squareMatrix.setElement(2, 2, 1);
            squareMatrix.setElement(3, 3, 1);
        
            System.out.println("SquareMatrix (4x4):");
            System.out.println(squareMatrix);
        
            System.out.println("\nСложение MirrorMatrixHor и SquareMatrix:");
            Matrix sumSquareResult = mirrorMatrix.sum(squareMatrix);
            System.out.println("Результат:");
            System.out.println(sumSquareResult);
        
            System.out.println("\nУмножение MirrorMatrixHor на SquareMatrix:");
            Matrix productSquareResult = mirrorMatrix.product(squareMatrix);
            System.out.println("Результат:");
            System.out.println(productSquareResult);
        
        }
        catch (BadMatrixSizesException | MatrixOperationException e)
        {
            System.err.println("Ошибка: " + e.getMessage());
        }
        
    }

}
