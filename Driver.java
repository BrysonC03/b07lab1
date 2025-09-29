public class Driver {
    public static void main(String [] args) {
        // Polynomial: 2x^2 + 3x + 5
        double[] coeff1 = {2, 3, 5};
        int[] exp1 = {2, 1, 0};
        Polynomial p1 = new Polynomial(coeff1, exp1);

        // Polynomial: x^2 + 1
        double[] coeff2 = {1, 1};
        int[] exp2 = {2, 0};
        Polynomial p2 = new Polynomial(coeff2, exp2);

        // Test evaluate
        System.out.println("p1(2) = " + p1.evaluate(2)); // 2*4 + 3*2 + 5 = 8 + 6 + 5 = 19

        // Test hasRoot
        System.out.println("p1 has root at x = -1? " + p1.hasRoot(-1)); // false

        // Test add
        Polynomial sum = p1.add(p2);
        sum.saveToFile("sum.txt");
        System.out.println("p1 + p2 written to sum.txt");

        // Test multiply
        Polynomial prod = p1.multiply(p2);
        prod.saveToFile("prod.txt");
        System.out.println("p1 * p2 written to prod.txt");

        // Bonus: check if prod evaluates correctly
        System.out.println("prod(2) = " + prod.evaluate(2));
    }
}