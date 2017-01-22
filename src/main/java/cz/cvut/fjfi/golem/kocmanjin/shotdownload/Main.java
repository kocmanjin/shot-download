package cz.cvut.fjfi.golem.kocmanjin.shotdownload;

import cz.cvut.fjfi.golem.kocmanjin.shotdownload.charts.LineChart;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import javafx.scene.paint.LinearGradient;

import java.util.Arrays;

/**
 * Created by kocmanjin on 1/22/17.
 */
public class Main {

    public static void main(String[] args) {

        GolemShot shot = new GolemShot(23034);

        LineChart chart = new LineChart();
        chart.addData("loop voltage", shot.getUloop());
        chart.addData("toroidal mag field", shot.getTorMagFieldDer());
        chart.addData("plasma current", shot.getPlasmaCurrent());
        chart.show();




//        double f0 = 307;
//        double Fs = 1e6;
//        double dt = 1/Fs;
//        int M =(int) Fs;
//
//        double[] signal = new double[M];
//
//        for (int i = 0; i < signal.length; i++) {
//            signal[i] = Math.sin(2 * Math.PI * 300 * dt * i) + Math.cos(2*Math.PI*dt*i*670);
//        }
//
//        double[] ret = Arrays.copyOf(signal, signal.length * 2);
//        DoubleFFT_1D fft = new DoubleFFT_1D(signal.length);
//        fft.realForward(ret);
//
//        double[] mag = new double[signal.length];
//        double[] real = new double[signal.length];
//        double[] complex = new double[signal.length];
//        double[] freq = new double[signal.length];
//
//        for (int i = 0; i < mag.length; i++) {
//            real[i] = ret[2*i];
//            complex[i] = ret[2*i + 1];
//            mag[i] = Math.sqrt(real[i]*real[i] + complex[i]*complex[i]);
//            freq[i] = i * Fs / M;
//        }
//
//        LineChart source = new LineChart();
//        source.addData("test", signal);
//        source.show();
//
//        LineChart result = new LineChart();
////        result.addData("real", freq, real);
////        result.addData("complex", freq,  complex);
//        result.addData("mag", freq,  mag);
//        result.show();
    }

}
