import java.io.*;
import java.util.Scanner;
public class Polynomial {
    double[] arr;
    int[] expo;
    public Polynomial() {
        arr = new double[1];
        expo = new int[1];
    }
    public Polynomial(double[] arr, int [] expo) {
        this.arr = new double[arr.length];
        this.expo = new int[expo.length];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        for (int i = 0; i < expo.length; i++) {
            this.expo[i] = expo[i];
        }
    }

    public Polynomial(File new_file)
    {
        try {
            Scanner in = new Scanner(new_file);
            String first_line = in.nextLine();
            int[] minuses = new int[first_line.length()/2 + 1];
            int index = 0;
            int count = 0;
            for(int i = 0; i < first_line.length(); i++)
            {
                if (first_line.charAt(i) == '-' | first_line.charAt(i) == '+') {
                    count++;
                    if (first_line.charAt(i) == '-') {
                        minuses[index] = count;
                        index++;
                    }
                }
            }
            minuses[index] = -1;
            String[] splited_array = first_line.split("[-+]");
            this.arr = new double[splited_array.length];
            this.expo = new int[splited_array.length];
            int negative = 0;
            for (int i = 0; i < splited_array.length; i++) {
                negative = 0;
                for (int j = 0; j < minuses.length && minuses[j] != -1; j++)
                {
                    if(i == minuses[j])
                        negative = -1;
                        
                }
                if (splited_array[i].indexOf('x') == -1) {
                    this.expo[i] = 0;
                    if(negative == -1)
                        this.arr[i] = -1 * Double.parseDouble(splited_array[i]);
                    else
                        this.arr[i] = Double.parseDouble(splited_array[i]);

                } else {
                    String[] new_str = splited_array[i].split("x");
                    if (new_str.length == 1) {
                        this.expo[i] = 1;
                        if(negative == -1)
                            this.arr[i] = -1 * Double.parseDouble(new_str[0]);
                        else
                            this.arr[i] = Double.parseDouble(new_str[0]);

                    } else {
                        this.expo[i] = Integer.parseInt(new_str[1]);
                        if(negative == -1)
                            this.arr[i] = -1 * Double.parseDouble(new_str[0]);
                        else
                            this.arr[i] = Double.parseDouble(new_str[0]);

                    }
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public Polynomial add(Polynomial obj) {
        //for expo array
        int result_arr_len = this.expo.length + obj.expo.length, result = 0;
        for (int i = 0; i < this.expo.length; i++) {
            for (int j = 0; j < obj.expo.length; j++) {
                if (this.expo[i] == obj.expo[j]) {
                    result++;
                }
            }
        }
        result_arr_len -= result;
        int[] res_expo = new int[result_arr_len];
        int check = 0, idx = 0;
        //put all element that are distinct in this.expo in res_expo
        for (int i = 0; i < this.expo.length; i++) {
            check = 0;
            for (int j = 0; j < res_expo.length; j++) {
                if (this.expo[i] == res_expo[j])
                    check = 1;
            }
            if (check == 0) {
                res_expo[idx] = this.expo[i];
                idx++;
            }
        }
        //put all elements that are distincin obj.expo in res_expo
        for (int i = 0; i < obj.expo.length; i++) {
            check = 0;
            for (int j = 0; j < res_expo.length; j++) {
                if (obj.expo[i] == res_expo[j])
                    check = 1;
            }
            if (check == 0) {
                res_expo[idx] = obj.expo[i];
                idx++;
            }
        }

        //for all the element in arr
        double[] res_arr = new double[result_arr_len];
        int idx_this = 0, idx_obj = 0;
        idx = 0;
        for (int i = 0; i < res_expo.length; i++)
        {
            idx_this = -1;
            idx_obj = -1;
            for (int j = 0; j < this.expo.length; j++) {
                if (res_expo[i] == this.expo[j]) {
                    idx_this = j;
                    break;
                }
            }
            for (int j = 0; j < obj.expo.length; j++) {
                if (res_expo[i] == obj.expo[j]) {
                    idx_obj = j;
                    break;
                }
            }
            if (idx_this != -1 && idx_obj != -1)
                res_arr[idx] = this.arr[idx_this] + obj.arr[idx_obj];
            if (idx_this == -1 && idx_obj != -1)
                res_arr[idx] = obj.arr[idx_obj];
            if (idx_this != -1 && idx_obj == -1)
                res_arr[idx] = this.arr[idx_this];
            idx++;
        }
        int final_len = result_arr_len;
        for (int i = 0; i < result_arr_len; i++)
        {
            if (res_arr[i] == 0) {
                final_len -= 1;
            }
        }
        if (final_len != result_arr_len) {
            double[] final_arr = new double[final_len];
            int[] final_expo = new int[final_len];

            idx = 0;
            for (int i = 0; i < result_arr_len; i++) {
                if (res_arr[i] != 0) {
                    final_arr[idx] = res_arr[i];
                    final_expo[idx] = res_expo[i];
                }
            }
            return new Polynomial(final_arr, final_expo);
        }
        else
            return new Polynomial(res_arr, res_expo);
    }

    public double evaluate(double x_val) {
        double sum = 0.0;
        for (int i = 0; i < this.arr.length; i++)
            sum += this.arr[i] * Math.pow(x_val, this.expo[i]);
        return sum;
    }

    public boolean hasRoot(double root) {
        return evaluate(root) == 0;
    }

    public Polynomial multiply(Polynomial obj)
    {
        int result_arr_len = this.expo.length * obj.expo.length;

        //creation of expo that may have dupes
        int[] res_expo = new int[result_arr_len];
        int idx = 0;
        for (int i = 0; i < this.expo.length; i++) {
            for (int j = 0; j < obj.expo.length; j++) {
                res_expo[idx] = this.expo[i] + obj.expo[j];
                idx++;
            }
        }
   
        //creation of arr that may have dupes
        double[] res_arr = new double[result_arr_len];
        idx = 0;
        for (int i = 0; i < this.arr.length; i++) {
            for (int j = 0; j < obj.arr.length; j++) {
                res_arr[idx] = this.arr[i] * obj.arr[j];
                idx++;
            }
        }
        for (int i = 0; i < result_arr_len; i++) {
            for (int j = i + 1; j < result_arr_len; j++) {
                if (res_expo[i] == res_expo[j]) {
                    res_arr[i] = res_arr[i] + res_arr[j];
                    res_arr[j] = res_arr[i];
                }
            }
        }
        int w_dupes = result_arr_len, wo_dupes = result_arr_len;
        for (int i = 0; i < result_arr_len; i++) {
            if (res_arr[i] == 0) {
                w_dupes -= 1;
            }
            for (int j = i + 1; j < result_arr_len; j++) {
                if (res_expo[i] == res_expo[j]) {
                    wo_dupes -= 1;
                }
            }
        }
        double[] arr;
        int[] expo;
        if (w_dupes < result_arr_len) {
            arr = new double[w_dupes];
            expo = new int[w_dupes];
            idx = 0;
            for (int i = 0; i < result_arr_len; i++) {
                if (res_arr[i] != 0) {
                    arr[idx] = res_arr[i];
                    expo[idx] = res_expo[i];
                    idx++;
                }
            }
        } else {
            arr = new double[wo_dupes];
            expo = new int[wo_dupes];
            idx = 0;
            int check = 1;
            for (int i = 0; i < result_arr_len; i++) {
                check = 1;
                for (int j = 0; j < wo_dupes; j++) {
                    if (res_arr[i] == arr[j]) {
                        check = 0;
                    }
                }
                if (check == 1) {
                    arr[idx] = res_arr[i];
                    expo[idx] = res_expo[i];
                }
            }
        }
        return new Polynomial(arr, expo);
    }

    public void saveToFile(String file_name)
    {
        try {
            File f = new File(file_name);
            if (!f.exists()) {
                f.createNewFile();
            }
            String string = "";
            for (int i = 0; i < this.arr.length; i++) {
                if (this.expo[i] == 0) {
                    string += this.arr[i];
                } else if (this.expo[i] == 1) {
                    string += this.arr[i] + "x";
                } else {
                    string += this.arr[i] + "x" + this.expo[i];
                }
            }
            FileWriter fw = new FileWriter(file_name);
            fw.write(string);
            fw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}