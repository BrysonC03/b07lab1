class Polynomial{
    private double[] coefficient;

    public Polynomial() {
        coefficient = new double[1];
        coefficient[0] = 0;
    }

    public Polynomial(double[] input)
    {
        coefficient = new double[input.length];
        for (int i = 0; i<input.length; i++)
        {
            coefficient[i] = input[i];
        }
    }

    public Polynomial add(Polynomial input)
    {
        int totalLength = coefficient.length + input.coefficient.length;
        double[] newCoefficient = new double[totalLength];
        for (int i = 0; i<totalLength; i++)
        {
            double thisNumber = (i < this.coefficient.length) ? this.coefficient[i] : 0;
            double inputNumber = (i < input.coefficient.length) ? input.coefficient[i] : 0;
            newCoefficient[i] = thisNumber + inputNumber;
        }
        return new Polynomial(newCoefficient);
    }

    public double evaluate(double input)
    {
        double calculate = 0;
        for (int i=0; i<this.coefficient.length; i++)
        {
            calculate += this.coefficient[i] * Math.pow(input, i);
        }
        return calculate;
    }

    public boolean hasRoot(double input)
    {
        double result = evaluate(input);
        if (result == 0)
        {
            return true;
        } else {
            return false;
        }
    }
}

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        Polynomial p1 = new Polynomial(c1);
        double [] c2 = {0,-2,0,0,-9};
        Polynomial p2 = new Polynomial(c2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
    }
}