import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;


public class Polynomial{
    double[] coefficient;
    int[] exponents;

    public Polynomial() {
    }

    public Polynomial(double[] input, int[] exponentInput)
    {
        coefficient = new double[input.length];
        exponents = new int[exponentInput.length];
        for (int i = 0; i<input.length; i++)
        {
            coefficient[i] = input[i];
        }
        for (int j = 0; j<exponentInput.length; j++)
        {
            exponents[j] = exponentInput[j];
        }
    }

    class Combine 
    {
        int exp;
        double coef;

        Combine(int a, double b)
        {
            this.exp = a;
            this.coef = b;
        }
    }

    public Polynomial add(Polynomial input)
    {

        ArrayList<Combine> adding = new ArrayList<>();
        for(int i=0; i<this.coefficient.length; i++)
        {
            adding.add(new Combine(this.exponents[i], this.coefficient[i]));
        }
        for(int i=0; i<input.coefficient.length; i++)
        {
            adding.add(new Combine(input.exponents[i], input.coefficient[i]));
        }

        Collections.sort(adding, Comparator.comparingInt(c -> c.exp));


        ArrayList<Combine> mergedPoly = new ArrayList<>();
        int n=0;
        int currentExp = adding.get(n).exp;
        double calculateCoeff = adding.get(n).coef;
        while(n<adding.size())
        {
            currentExp = adding.get(n).exp;
            calculateCoeff = adding.get(n).coef;
            n++;
            while((n<adding.size()) && (currentExp == adding.get(n).exp))
            {
                calculateCoeff += adding.get(n).coef;
                n++;
            }

            mergedPoly.add(new Combine(currentExp, calculateCoeff));
        }

        double[] finalCoeff = new double[mergedPoly.size()];
        int[] finalExp = new int[mergedPoly.size()];
        for (int i=0; i<mergedPoly.size(); i++)
        {
            finalCoeff[i] = mergedPoly.get(i).coef;
            finalExp[i] = mergedPoly.get(i).exp;
        }

        return new Polynomial(finalCoeff, finalExp);





        // int totalLength = this.coefficient.length + input.coefficient.length;
        // double[] newCoefficient = new double[totalLength];
        // int[] newExponent = new int[totalLength];
        // double thisNumber;
        // double inputNumber;
        // for (int i = 0; i<totalLength; i++)
        // {
        //     thisNumber = (i < this.coefficient.length) ? this.coefficient[i] : 0;
        //     inputNumber = (i < input.coefficient.length) ? input.coefficient[i] : 0;
        //     newCoefficient[i] = thisNumber + inputNumber;
        //     thisNumber = (i < this.exponents.length) ? this.exponents[i] : 0;
        //     inputNumber = (i < input.exponents.length) ? input.exponents[i] : 0;
        //     newExponent[i] = thisNumber + inputNumber;
        // }
        // return new Polynomial(newCoefficient, newExponent);
    }

    public double evaluate(double input)
    {
        double calculate = 0;
        for (int i=0; i<this.coefficient.length; i++)
        {
            calculate += this.coefficient[i] * Math.pow(input, this.exponents[i]);
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

    public Polynomial multiply(Polynomial input)
    {
        int maximumLength = this.coefficient.length * input.coefficient.length;
        int index = 0;
        double[] newCoefficient = new double[maximumLength];
        int[] newExponent = new int[maximumLength];
        for (int i=0; i<this.coefficient.length; i++)
        {
            for (int j=0; j<input.coefficient.length; j++)
            {
                newCoefficient[index] = this.coefficient[i] * input.coefficient[j];
                newExponent[index] = this.exponents[i] + input.exponents[j];
                index++;
            }
        }



        ArrayList<Combine> finalPoly = new ArrayList<>();
        for (int i=0; i<newCoefficient.length; i++)
        {
            finalPoly.add(new Combine(newExponent[i], newCoefficient[i]));
        }
        Collections.sort(finalPoly, Comparator.comparingInt(c -> c.exp));



        ArrayList<Combine> mergedPoly = new ArrayList<>();
        int i=0;
        int currentExp = finalPoly.get(i).exp;
        double calculateCoeff = finalPoly.get(i).coef;
        while(i<finalPoly.size())
        {
            currentExp = finalPoly.get(i).exp;
            calculateCoeff = finalPoly.get(i).coef;
            i++;
            while((i<finalPoly.size()) && (currentExp == finalPoly.get(i).exp))
            {
                calculateCoeff += finalPoly.get(i).coef;
                i++;
            }

            mergedPoly.add(new Combine(currentExp, calculateCoeff));
        }
        

        double[] finalCoeff = new double[mergedPoly.size()];
        int[] finalExp = new int[mergedPoly.size()];
        for(int i=0; i<mergedPoly.size(); i++)
        {
            finalCoeff[i] = mergedPoly.get(i).coef;
            finalExp[i] = mergedPoly.get(i).exp;
        }


        return new Polynomial(finalCoeff, finalExp);

    }



    public void saveToFile(String fileName)
    {
        StringBuilder newString = new StringBuilder();

        for (int i=0; i<this.coefficient.length; i++)
        {

            if (this.coefficient[i] == 0)
            {
                continue;
            }

            if (i>0 && this.coefficient[i] > 0)
            {
                newString.append("+");
            }

            if (this.exponents[i] != 0)
            {
                newString.append(this.coefficient[i]).append("x").append(this.exponents[i]);
            } else if (this.exponents[i] == 0) {
                newString.append(this.coefficient[i]);
            }
        }

        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write(newString.toString());
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }


}