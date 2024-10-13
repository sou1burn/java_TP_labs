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

        m3.set_element(0, 0, 1);
        m3.set_element(0, 1, 1);
        m3.set_element(1, 0, 1);
        m3.set_element(1, 1, 0);
        Matrix res = new Matrix(2);

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                res.set_element(i, j, m3.get_element(i, j));
            }
        }

        for (int i = 1; i <= 10; ++i) {
            System.out.println("Power " + i);
            
            System.out.println(res.toString());
            res = res.product(m3);
        }
    }
     
}
