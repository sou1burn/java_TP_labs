package Main;

import java.util.Random;

import Matrix.*;
import MatrixException.*;

//сделать перемножение двух обычных разреженных и двух на хэшмапе для отчета
public class Main {
    
    public static void main(String[] args)
    {   
        int size = 1000;
        int nonZeroElements = 1000;

        Random random = new Random();

        try 
        {
            UsualMatrix matrix1 = new UsualMatrix(size, size);
            for (int i = 0; i < nonZeroElements; i++)
            {
                matrix1.setElement(random.nextInt(size), random.nextInt(size), random.nextInt(100));
            }

            SparseMatrix matrix2 = new SparseMatrix(size, size);
            for (int i = 0; i < nonZeroElements; i++)
            {
                matrix2.setElement(random.nextInt(size), random.nextInt(size), random.nextInt(100));
            }

            //SquareMatrix matrix3 = new SquareMatrix(size);
            
            SparseHashMapMatrix matrix4 = new SparseHashMapMatrix(size, size);
            for (int i = 0; i < nonZeroElements; i++)
            {
                matrix4.setElement(random.nextInt(size), random.nextInt(size), random.nextInt(100));
            }

            /*  System.out.println("Matrix 1 (Usual Matrix):");
            System.out.println(matrix1);
            System.out.println("\nMatrix 2 (Sparse Matrix):");
            System.out.println(matrix2);
            System.out.println("\nMatrix 3 (SparseHM Matrix):");
            System.out.println(matrix4);
    */        
            IMatrix sum1 = matrix1.sum(matrix2);

            long startLLSum = System.currentTimeMillis();

            IMatrix sum2 = matrix2.sum(matrix2);

            long endLLSum = System.currentTimeMillis();

            System.out.println("time elapsed for sum m2 (sparse) + m2 " + (endLLSum - startLLSum));

            long startHMSum = System.currentTimeMillis();

            IMatrix sumHM = matrix4.sum(matrix4);
            
            long endHMSum = System.currentTimeMillis();

            System.out.println("time elapsed for sum  m3 (sparseHM) + m3 " + (endHMSum - startHMSum));

           // IMatrix sum3 = matrix3.sum(matrix1);
            //IMatrix sum4 = matrix3.sum(matrix2);
            
            
            /*System.out.println("\nAddition Result: m1 + m2");
            System.out.println(sum1);
            System.out.println("\nAddition Result: m2 + m1");
            System.out.println(sum2);
            System.out.println("\nAddition Result: m3 + m1");
            System.out.println(sumHM);
 */           
            // System.out.println("\nAddition Result: m3 + m1");
           // System.out.println(sum3);
            //System.out.println("\nAddition Result: m3 + m2");
            //System.out.println(sum4);
            

            //IMatrix product1 = matrix1.product(matrix2);
            long startLL = System.currentTimeMillis();
            
            IMatrix product2 = matrix2.product(matrix2);
            long endLL = System.currentTimeMillis();

            System.out.println("time elapsed for multiplication m2 (sparse) * m2 " + (endLL - startLL));

            long startHM = System.currentTimeMillis();

            IMatrix productHash = matrix4.product(matrix4);

            long endHM = System.currentTimeMillis();

            System.out.println("time elapsed for multiplication m3 (sparseHM) * m3 " + (endHM - startHM));


            //IMatrix product3 = matrix3.product(matrix1);
            //IMatrix product4 = matrix3.product(matrix2);
/*
            System.out.println("\nMultiplication Result: m1 * m2");
            System.out.println(product1);
            System.out.println("\nMultiplication Result: m2 * m1");
            System.out.println(product2);
            System.out.println("\nMultiplication Result: m3 * m1");
            System.out.println(productHash);
*/    
            
           /*
            System.out.println("\nMultiplication Result: m3 * m1");
            System.out.println(product3);
            System.out.println("\nMultiplication Result: m3 * m2");
            System.out.println(product4);
            */
        }
        catch (MatrixOperationException e)
        {
            System.err.println("Error:" + e);
        }
    }
}
