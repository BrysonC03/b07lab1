public class Driver {
    public static void main(String [] args) {
        double[] coeff1 = {2, 3, 5};
        int[] exp1 = {2, 1, 0};
        Polynomial p1 = new Polynomial(coeff1, exp1);

        double[] coeff2 = {1, 1};
        int[] exp2 = {2, 0};
        Polynomial p2 = new Polynomial(coeff2, exp2);

        System.out.println("p1(2) = " + p1.evaluate(2)); 
        System.out.println("p1 has root at x = " + p1.hasRoot(-1)); 

        Polynomial sum = p1.add(p2);
        sum.saveToFile("sum.txt");
        System.out.println("p1 + p2 written to sum.txt");

        Polynomial prod = p1.multiply(p2);
        prod.saveToFile("prod.txt");
        System.out.println("p1 * p2 written to prod.txt");
        
        System.out.println("prod(2) = " + prod.evaluate(2));
    }
}