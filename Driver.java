import java.io.*;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
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
        System.out.println("Coeff:" + Arrays.toString(add.arr) + "Expo:" + Arrays.toString(add.expo)
                +"has root = 0: " + add.hasRoot(0));
        System.out.println("Coeff:"+Arrays.toString(multiply_1.arr)+ "Expo:"+Arrays.toString(multiply_1.expo)+" has root = 0: " + multiply_1.hasRoot(0));
        multiply_1.saveToFile("test.txt");
        Polynomial new_ = new Polynomial(new File("test.txt"));
        Polynomial new_poly = new_.multiply(new Polynomial(new double[] { 1, 1 }, new int[] { 2, 0 }));
        new_poly.saveToFile("test2.txt");
        Polynomial new_poly2 = new Polynomial(new File("test2.txt")).multiply(new Polynomial());
        Polynomial new_poly3 = new Polynomial(new File("test2.txt")).add(new Polynomial());
        
        // add.saveToFile("random.txt");
        // //Adding the 0 polynomial
        // for (int i = 0; i < new_poly3.arr.length; i++) {
        //     System.out.print(new_poly3.arr[i] + "x" + new_poly3.expo[i] + ", ");
        // }
        // System.out.println();

        // //Mutliplying with the 0 polynomial
        // System.out.println("length = " + new_poly2.arr.length);
        // for (int i = 0; i < new_poly2.arr.length; i++) {
        //     System.out.print(new_poly2.arr[i] + "x" + new_poly2.expo[i] + ", ");
        // }

        Polynomial q1 = new Polynomial(new double[] { 3, 3 }, new int[] { 0, 1 });
        q1.saveToFile("test3.txt");
        Polynomial q2 = new Polynomial(new File("test3.txt"));

        Polynomial q3 = new Polynomial(new double[] { -1, -5, -10}, new int[] { 0, 1, 2});
        q3.saveToFile("test4.txt");

        Polynomial q4 = new Polynomial(new File("test4.txt"));
    }
}