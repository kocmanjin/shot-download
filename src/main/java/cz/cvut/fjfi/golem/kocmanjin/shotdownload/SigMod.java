package cz.cvut.fjfi.golem.kocmanjin.shotdownload;


/**
 * Created by kocmanjin on 1/22/17.
 */
public class SigMod {    

    // --------------------- Simple statistics on array----------------------

    public static double getMax(double[] data) {
        double max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (max < data[i]) {
                max = data[i];
            }
        }
        return max;
    }

    public static double getMin(double[] data) {
        double min = data[0];
        for (int i = 1; i < data.length; i++) {
            if (min > data[i]) {
                min = data[i];
            }
        }
        return min;
    }

    public static double getAverage(double[] a) {
        double ret = 0;
        for (int i = 0; i < a.length; i++) {
            ret += a[i];
        }
        return ret / a.length;
    }

    // bad implementation, I know
    public static double getMedian(double[] data) {
        double[] a = new double[data.length];
        System.arraycopy(data, 0, a, 0, data.length);
        double temp;
        int asize = a.length;
        //sort the array in increasing order
        for (int i = 0; i < asize ; i++) {
            for (int j = i+1; j < asize; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        //if it's odd
        if (asize%2 == 1) {
            return a[asize/2];
        }
        else {
            return ((a[asize/2]+a[asize/2 - 1])/2);
        }
    }

    public static double[] averageFilter(double[] data, int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Bad window size!");
        }
        size = size - size%2;
        double[] ret = new double[data.length];

        for (int i = size; i < data.length-size; i++) {
            double[] a = getSubArray(data, i, size/2);
            ret[i] = getAverage(a);
        }
        return ret;
    }

    public static double[] medianFilter(double[] data, int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Bad window size!");
        }
        size = size - size%2;
        double[] ret = new double[data.length];

        for (int i = 0; i < data.length; i++) {
            double[] a = getSubArray(data, i, size/2);
            ret[i] = getMedian(a);
        }
        return ret;
    }

    private static double[] getSubArray(double[] data, int position, int size) {
        int from = position - size;
        if (from < 0) {
            from = 0;
        }
        int to = position + size;
        if (to >= data.length) {
            to = data.length - 1;
        }
        double[] ret = new double[to - from + 1];
        System.arraycopy(data, from, to, 0, ret.length);
//        for (int i = 0; i < ret.length; i++) {
//            int pos = position - size/2 + i;
//            if (pos < 0) {
//                ret[i] = data[0];
//            } else if (pos >= data.length) {
//                ret[i] = data[data.length-1];
//            } else {
//                ret[i] = data[position - size/2 + i];
//            }
//        }
        return ret;
    }

    public static double[] add(double[] data, double cislo) {
        double[] ret = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            ret[i] = data[i] + cislo;
        }
        return ret;
    }

    public static double[] multiplication(double[] data, double number) {
        double[] ret = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            ret[i] = data[i] * number;
        }
        return ret;
    }

    public static double[] add(double[] data1, double[] data2) {
        if (data1.length != data2.length) {
            throw new IllegalArgumentException("Data nemaji stejny pocet radku");
        }

        double[] ret = new double[data1.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = data1[i] + data2[i];
        }
        return ret;
    }

    public static double[] substract(double[] data1, double[] data2) {
        if (data1.length != data2.length) {
            throw new IllegalArgumentException("Data nemaji stejny pocet radku");
        }

        double[] ret = new double[data1.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = data1[i] - data2[i];
        }
        return ret;
    }

    public static double[] abs(double[] data1) {
        double[] ret = new double[data1.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Math.abs(data1[i]);
        }
        return ret;
    }

    // ------------------- Differential --------------------------------

    public static double[] derivate(double time, double[] data) {
        double[] data_ret = new double[data.length];

        for (int i = 0; i < data.length-1; i++) {
            data_ret[i] = (data[i+1]-data[i])/time;
        }
        data_ret[data.length-1] = data_ret[data.length-2];

        return data_ret;
    }

    public static double[] integrate(double time, double[] data) {
        double[] integ = new double[data.length];
        double in = 0;
        for (int i = 0; i < data.length; i++) {
            in += data[i];
            integ[i] = in*time;
        }
        return integ;
    }

    public static double[] integrate(double time, double[] data, int averLine) {
        if (averLine > data.length) {
            throw new IllegalArgumentException("AverLine is greater than the lines in data");
        }
        double[] integ = new double[data.length];

        double aver = 0;
        for (int i = 0; i < averLine; i++) {
            aver += data[i];
        }
        aver = aver / averLine;

        double in = 0;
        for (int i = 0; i < data.length; i++) {
            in += data[i] - aver;
            integ[i] = in*time;
        }
        return integ;
    }

    public static double[] convolution(double[] signal, double[] kernel, int length) {
        double[] result = new double[length];
        for (int i = 0; i < signal.length; i++) {
            for (int j = 0; j < kernel.length; j++) {
                if ( ((i-j) >= 0) && ((i-j) < signal.length) ){
                    result[i] += signal[i - j] * kernel[j];    // convolve: multiply and accumulate
                }
            }
        }
        return result;
    }

//    private static int loadInt(String vyzva) {
//        Scanner input = new Scanner(System.in);
//        int vysledek = 0;
//        boolean zadano = false;
//        while(!zadano) {
//            System.out.println(vyzva);
//            try {
//                vysledek = input.nextInt();
//                zadano = true;
//            }
//            catch(NoSuchElementException ex) {
//                System.err.println("Neplatny vstup, zadejte proism znovu");
//                input.nextLine();
//                zadano = false;
//            }
//        }
//        input.nextLine();
//        return vysledek;
//    }
//
//
//    public static double[] integrate(double dt, double[] data, int averLine, double coef) {
//        if (averLine > data.length) {
//            throw new IllegalArgumentException("AverLine is greater than the lines in data");
//        }
//        double[] integ = new double[data.length];
//
//        double aver = 0;
//        for (int i = 0; i < averLine; i++) {
//            aver += data[i];
//        }
//        aver = aver / averLine;
//
//        double in = 0;
//        for (int i = 0; i < data.length; i++) {
//            in += data[i] - aver;
//            integ[i] = in*dt/coef;
//        }
//        return integ;
//    }

//    public static double[] integrate(double dt, double[] data, int averLine, double coef, double limit) {
//        if (averLine > data.length) {
//            throw new IllegalArgumentException("AverLine is greater than the lines in data");
//        }
//        double[] integ = new double[data.length];
//
//        double aver = 0;
//        for (int i = 0; i < averLine; i++) {
//            aver += data[i];
//        }
//        aver = aver / averLine;
//
//        double in = 0;
//        for (int i = 0; i < data.length; i++) {
//            if (Math.abs(data[i] - aver) > limit) {
//                in += 0;
//            } else {
//                in += data[i] - aver;
//            }
//            integ[i] = in*dt/coef;
//        }
//        return integ;
//    }

//    public static double[] getPowerSpectrumTime(int delka, double celk_cas) {
//        double[] time = new double[delka];
//        for (int i = 0; i < time.length; i++) {
//            time[i] = i / celk_cas;
//        }
//        return time;
//    }
    
//    public static double[] removeSpectrum(double[] data, double from, double to, double celk_cas) {
//        from = (from*celk_cas)*2;
//        to   = (to  *celk_cas)*2;
//        double[] arbit = new double[data.length];
//        System.arraycopy(data, 0, arbit, 0, data.length);
//        RealDoubleFFT fft = new RealDoubleFFT(arbit.length);
//        double real, imag;
//        double[] magnitude = new double[arbit.length];
//        fft.ft(arbit);
//        if (to > arbit.length) {
//            to = arbit.length;
//        }
//        for (int i = (int) from; i < to; i++) {
//            arbit[i] = 0;
//        }
//        fft.bt(arbit);
//        for (int i = 0; i < arbit.length; i++) {
//            arbit[i] = arbit[i] / fft.norm_factor;
//        }
//        return arbit;
//    }
    
//    public static double[][] getPowerSpectrum(double[] data, double celk_cas)
//    {
//        double[] arbit = new double[data.length];
//        System.arraycopy(data, 0, arbit, 0, data.length);
//        RealDoubleFFT fft = new RealDoubleFFT(arbit.length);
//        double real, imag;
//        double[] magnitude = new double[arbit.length];
//        fft.ft(arbit);
//        try {
//        for (int i = 0; i < magnitude.length; i++) {
//            real = arbit[2*i];
//            imag = arbit[2*i+1];
//            magnitude[i] = Math.sqrt(real*real + imag*imag);
//        }
//        } catch (ArrayIndexOutOfBoundsException ex) {}
//
//        double[][] ret = new double[2][];
//        ret[0] = getPowerSpectrumTime(magnitude.length, celk_cas);
//        ret[1] = magnitude;
//        return ret;
//    }
    

    
    




    
//    public static double[] convolution(double[] signal, double[] kernel) {
//    	double[] result = new double[signal.length + kernel.length-1];
//        int hotovo = 0;
//        int procento = result.length/50;
//        System.out.print("[");
//        for (int i = 0; i < 50; i++) {
//            System.out.print("=");
//        }
//        System.out.println("]");
//        System.out.print("[");
//        long start = System.currentTimeMillis();
//    	for (int i = 0; i < result.length; i++ )
//    	{
//    	    result[i] = 0;                       // set to zero before sum
//    	    for (int j = 0; j < kernel.length; j++ )
//    	    {
//    	    	if ( ((i-j) >= 0) && ((i-j) < signal.length) ){
//    	            result[i] += signal[i - j] * kernel[j];    // convolve: multiply and accumulate
//                }
//    	    }
////            if (i/procento != hotovo) {
////                hotovo = i/procento;
////                System.out.print("=");
////            }
//    	}
//        long end = System.currentTimeMillis();
//        System.out.println("=]");
//        System.out.println("Hotovo za " + (end-start)/1000.0 + "s");
//        return result;
//    }

//    public static double[] deconv(double[] g, double[] f) {
//        double[] h = new double[g.length - f.length + 1];
//        for (int n = 0; n < h.length; n++) {
//            h[n] = g[n];
//            int lower = Math.max(n - f.length + 1, 0);
//            for (int i = lower; i < n; i++)
//                h[n] -= h[i] * f[n - i];
//            h[n] /= f[0];
//        }
//        return h;
//    }



}
