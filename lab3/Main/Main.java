package Main;

import Matrix.Matrix;
import SquareMatrix.SquareMatrix;
import MatrixException.MatrixOperationException;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            System.out.println("Создание матрицы 2x3:");
            Matrix m1 = new Matrix(2, 3);
            m1.set_element(0, 0, 1);
            m1.set_element(0, 1, 2);
            m1.set_element(0, 2, 3);
            m1.set_element(1, 0, 4);
            m1.set_element(1, 1, 5);
            m1.set_element(1, 2, 6);

            System.out.println("Матрица m1:");
            System.out.println(m1);

            System.out.println("Создание матрицы 2x3 для сложения с m1:");
            Matrix m2 = new Matrix(2, 3);
            m2.set_element(0, 0, 6);
            m2.set_element(0, 1, 5);
            m2.set_element(0, 2, 4);
            m2.set_element(1, 0, 3);
            m2.set_element(1, 1, 2);
            m2.set_element(1, 2, 1);

            System.out.println("Матрица m2:");
            System.out.println(m2);

            Matrix sum = m1.sum(m2);
            System.out.println("Сумма матриц m1 и m2:");
            System.out.println(sum);

            // Попытка умножения матриц с несовместимыми размерами
            try
            {
                Matrix m3 = new Matrix(3, 2);
                System.out.println("Попытка умножения несовместимых матриц m1 и m3:");
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

        // Тестирование квадратной матрицы
        try
        {
            System.out.println("\nСоздание квадратной матрицы размером 3x3:");
            SquareMatrix sm1 = new SquareMatrix(3);
            System.out.println("Квадратная матрица sm1 (единичная матрица):");
            System.out.println(sm1);

            SquareMatrix sm2 = new SquareMatrix(3);
            System.out.println("Создание второй квадратной матрицы размером 3x3 (единичная матрица):");
            System.out.println(sm2);

            Matrix sumSquares = sm1.sum(sm2);
            System.out.println("Сумма квадратных матриц sm1 и sm2:");
            System.out.println(sumSquares);

            // Попытка сложения квадратной матрицы с обычной матрицей
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
    }

}
