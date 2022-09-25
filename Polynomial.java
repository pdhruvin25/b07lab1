public class Polynomial{
    double [] arr;
    public Polynomial()
    {
        arr = new double[1];
        arr[0] = 0.0;
    }
     public Polynomial(double [] arr)
    {
        this.arr = arr;//this.arr refers to field
    }
    public Polynomial add(Polynomial obj)
    {
        double[] res_arr;
        if(this.arr.length > obj.arr.length)
        {
            res_arr = new double[this.arr.length];
            res_arr = this.arr;
            for(int i =0; i < obj.arr.length; i++)
            {
                res_arr[i] += obj.arr[i];
            }
        }
        else 
        {
            res_arr = new double[obj.arr.length];
            res_arr = obj.arr;
            for(int i =0; i < this.arr.length; i++)
            {
                res_arr[i] += this.arr[i];

            }
        }
        return new Polynomial(res_arr);

    }
    public double evaluate(double x_val)
    {
        double sum = 0.0;
        for(int i = 0; i < this.arr.length; i++)
            sum += this.arr[i] * Math.pow(x_val, i);
        return sum;
    }
    public boolean hasRoot(double root)
    {
        return evaluate(root) == 0;
    }
    
}