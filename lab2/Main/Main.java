package Main;


public class Main
{
    public static void main(String[] args)
    {
        Matrix m1 = new Matrix(3);

        Matrix m2 = new Matrix(3);

        System.out.println("Matrix 1:\n");
        System.out.println(m1.toString());

        System.out.println("\nMatrix 2:\n");
        System.out.println(m2.toString());

        Matrix result_sum = m1.sum(m2);
        System.out.println(result_sum.toString());
        
        System.out.println("\nResult of mul: \n");
        Matrix result_product = m1.product(m2);
        System.out.println(result_product.toString());

        Matrix m3 = new Matrix(2);

        m3.setElement(0, 0, 1);
        m3.setElement(0, 1, 1);
        m3.setElement(1, 0, 1);
        m3.setElement(1, 1, 0);
        Matrix res = new Matrix(2);

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                res.setElement(i, j, m3.getElement(i, j));
            }
        }

        for (int i = 1; i <= 10; ++i) {
            System.out.println("Power " + i);
            
            System.out.println(res.toString());
            res = res.product(m3);
        }


        Matrix m4 = new Matrix(3);

        m4.setElement(0, 0, 1);
        m4.setElement(0, 1, 2);
        m4.setElement(0, 2, 3);
        m4.setElement(1, 0, 4);
        m4.setElement(1, 1, 5);
        m4.setElement(1, 2, 6);
        m4.setElement(2, 0, 7);
        m4.setElement(2, 1, 8);
        m4.setElement(2, 2, 9);

        System.out.println("Matrix for miracle:");
        System.out.println(m4.toString());

        Matrix res2 = m4.doMiracle(0);
        Matrix res3 = m4.doMiracle(1);
        System.out.println("Miracle type 0(vertical)");
        System.out.println(res2.toString());

        System.out.println("Miracle type 1(horizontal)");
        System.out.println(res3.toString());


        Matrix m5 = new Matrix(4);

        m5.setElement(0, 0, 1);
        m5.setElement(0, 1, 2);
        m5.setElement(0, 2, 3);
        m5.setElement(0, 3, 4);
        m5.setElement(1, 0, 5);
        m5.setElement(1, 1, 6);
        m5.setElement(1, 2, 7);
        m5.setElement(1, 3, 8);
        m5.setElement(2, 0, 9);
        m5.setElement(2, 1, 10);
        m5.setElement(2, 2, 11);
        m5.setElement(2, 3, 12);
        m5.setElement(3, 0, 13);
        m5.setElement(3, 1, 14);
        m5.setElement(3, 2, 15);
        m5.setElement(3, 3, 16);

        System.out.println("Matrix for miracle:");
        System.out.println(m5.toString());

        Matrix res4 = m5.doMiracle(0);
        Matrix res5 = m5.doMiracle(1);

        System.out.println("Miracle type 0(vertical)");
        System.out.println(res4.toString());

        System.out.println("Miracle type 1(horizontal)");
        System.out.println(res5.toString());

        try{
            Matrix res6 = m5.doMiracle(2);
        }
        catch (RuntimeException e)
        {
            System.err.println("Ошибка: " + e.getMessage());
        }

        
    }
     
}
