import java.io.*;

public class Driver {
    public static void main(String[] args) {
        // Polynomial p = new Polynomial();
        // System.out.println(p.evaluate(3));
        // double[] c1 = { 6, 0, 0, 5 };
        // Polynomial p1 = new Polynomial(c1);
        // double[] c2 = { 0, -2, 0, 0, -9 };
        // Polynomial p2 = new Polynomial(c2);
        // Polynomial s = p1.add(p2);
        // System.out.println("s(0.1) = " + s.evaluate(0.1));
        // if (s.hasRoot(1))
        // System.out.println("1 is a root of s");
        // else
        // System.out.println("1 is not a root of s");
        double[] coefficeints_1 = { 1, -1 };
        double[] coefficeints_2 = { 1, 1 };
        int[] exponents_1 = { 1, 0 };
        int[] exponents_2 = { 1, 0 };
        Polynomial p1 = new Polynomial(coefficeints_1, exponents_1);
        System.out.println(p1.evaluate(2.0));
        Polynomial p2 = new Polynomial(coefficeints_2, exponents_2);
        System.out.println(p2.evaluate(2.0));
        Polynomial add = p1.add(p2);
        System.out.println("add(6) = " + add.evaluate(6));
        Polynomial multiply_1 = p1.multiply(p2);
        System.out.println("multiply(6) = " + multiply_1.evaluate(6));
        System.out.println("add has root = 0: " + add.hasRoot(0));
        System.out.println("multiply has root = 0: " + multiply_1.hasRoot(0));
        multiply_1.saveToFile("test.txt");
        Polynomial new_ = new Polynomial(new File("test.txt"));

        Polynomial new_poly = new_.multiply(new Polynomial(new double[] { 1, 1 }, new int[] { 2, 0 }));
        new_poly.saveToFile("test2.txt");
        Polynomial new_poly2 = new Polynomial(new File("test2.txt")).multiply(new Polynomial());
        Polynomial new_poly3 = new Polynomial(new File("test2.txt")).add(new Polynomial());

        //Adding the 0 polynomial
        for (int i = 0; i < new_poly3.arr.length; i++) {
            System.out.print(new_poly3.arr[i] + "x" + new_poly3.expo[i] + ", ");
        }
        System.out.println();

        //Mutliplying with the 0 polynomial
        System.out.println("length = " + new_poly2.arr.length);
        for (int i = 0; i < new_poly2.arr.length; i++) {
            System.out.print(new_poly2.arr[i] + "x" + new_poly2.expo[i] + ", ");
        }
        System.out.println();
    }
}