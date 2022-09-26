public class Polynomial {
    double[] arr;
    public Polynomial() {
        arr = new double[1];
    }
    public Polynomial(double[] arr) {
        this.arr = new double[arr.length];
        for (int i = 0; i < arr.length; i++){
            this.arr[i] = arr[i];
        }
    }

    public Polynomial add(Polynomial obj) {
        double[] res_arr;
        if (this.arr.length > obj.arr.length){
            res_arr = new double[this.arr.length];
            for (int i = 0; i < this.arr.length; i++)
                res_arr[i] = this.arr[i];
            for (int i = 0; i < obj.arr.length; i++)
                res_arr[i] += obj.arr[i];
        } else{
            res_arr = new double[obj.arr.length];
            for (int i = 0; i < obj.arr.length; i++)
                res_arr[i] = obj.arr[i];
            for (int i = 0; i < this.arr.length; i++)
                res_arr[i] += this.arr[i];
        }
        return new Polynomial(res_arr);
    }

    public double evaluate(double x_val) {
        double sum = 0.0;
        for (int i = 0; i < this.arr.length; i++)
            sum += this.arr[i] * Math.pow(x_val, i);
        return sum;
    }

    public boolean hasRoot(double root) {
        return evaluate(root) == 0;
    }

}