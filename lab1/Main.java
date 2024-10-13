public class Main
{
//class complex sub div
    public static void main(String[] args)

    {
        Int one = new Int();

        one.increment();

        Int ten = new Int();

        for (int i = 0; i < 10; ++i)
        {
            ten.add(one);
        }

        Int hundred = new Int();
        for (int i = 0; i < 10; ++i)
        {
            hundred.add(ten);
        }
        
        Int thousand = new Int();
        for (int i = 0; i < 10; ++i)
        {
            thousand.add(hundred);
        }

        System.out.println(thousand.toString());



        Complex c1 = new Complex(41.0, 59.0);
        Complex c2 = new Complex(36.5, -17.0);
        System.out.println("First complex number:");
        System.out.println(c1.toString());

        System.out.println("Second complex number:");
        System.out.println(c2.toString());

        System.out.println("Res of sub:");
        Complex res_sub = c1.substract(c2);
        System.out.println(res_sub.toString());

        System.out.println("Res of div:");
        Complex res_div = c1.divide(c2);

        System.out.println(res_div.toString());
    }

}


class Int
{
    private int value;

    public Int()
    {
        this.value = 0;
    }

    public void increment()
    {
        this.value++;
    }

    public void decrement()
    {
        this.value--;
    }

    public void add(Int num)
    {
        this.value += num.value;
    }

    public void sub(Int num)
    {
        this.value -= num.value;
    }

    public String toString()
    {
        return String.valueOf(this.value);
    }
}



class Complex
{
    private double real;
    private double imaginated;

    public Complex()
    {
        this.real = 0.0;
        this.imaginated = 0.0;
    }

    public Complex(double real, double imaginated)
    {
        this.real = real;
        this.imaginated = imaginated;
    }

    public Complex sum(Complex other)
    {
        return new Complex((this.real + other.real), (this.imaginated + other.imaginated));
    }

    public Complex substract(Complex b)
    {
        return new Complex((this.real - b.real), (this.imaginated - b.imaginated));
    }

    public Complex divide(Complex b)
    {
       
        double denominator = (b.real * b.real) + (b.imaginated * b.imaginated);

        double real_part = (this.real * b.real + this.imaginated * b.imaginated) / denominator;

        double imaginated_part = (this.imaginated * b.real - this.real * b.imaginated) / denominator;


        return new Complex(real_part, imaginated_part);
    }

    @Override
    public String toString()
    {
        return real + " + " + imaginated + "j";
    }

}