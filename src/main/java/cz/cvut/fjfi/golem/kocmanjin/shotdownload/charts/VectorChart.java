package cz.cvut.fjfi.golem.kocmanjin.shotdownload.charts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by kocmanjin on 1/22/17.
 */
public class VectorChart extends Abstract2DChart {

	private VectorSeriesCollection dataset;
	
	public VectorChart() {
		dataset = new VectorSeriesCollection();
	}
	
	public void addData(String title, double[] x, double[] y, double[] dx, double[] dy) {
		if ((x.length != y.length)||(dx.length != dy.length)||(dx.length != y.length)) {
			throw new IllegalArgumentException("Arrays are not the same length");
		}
		VectorSeries vs = new VectorSeries(title);
		for (int i = 0; i < x.length; i++) {
			vs.add(x[i], y[i], dx[i], dy[i]);
		}
		dataset.addSeries(vs);
	}
	
	public void addData(String title, List<Double> x, List<Double> y, List<Double> dx, List<Double> dy) {
		if ( (x.size() != y.size())||(dx.size() != dy.size())||(x.size() != dx.size()) ) {
			throw new IllegalArgumentException("Arrays are not the same length");
		}
		VectorSeries vs = new VectorSeries(title);
		for (int i = 0; i < x.size(); i++) {
			vs.add(x.get(i), y.get(i), dx.get(i), dy.get(i));
		}
		dataset.addSeries(vs);
	}
	
	private JFreeChart createChart(String title, BufferedImage background) {
        if (title == null) {
            title = "data";
        }
        VectorRenderer render = new VectorRenderer();
        ValueAxis xtic = new NumberAxis(getXlabel());
        ValueAxis ytic = new NumberAxis(getYlabel());
        if (getXrange() != null) {
            xtic.setRange(getXrange()[0], getXrange()[1]);
        }
        if (getYrange() != null) {
            ytic.setRange(getYrange()[0], getYrange()[1]);
        }
        XYPlot plot = new XYPlot(dataset, xtic, ytic, render);
        if (background != null) {
            plot.setBackgroundImage(background);
        }
        return new JFreeChart(title, plot);
    }
	
	public void show() {
        show("data", null);
    }

    public void show(BufferedImage background) {
        show("data", background);
    }

    public void show(String title) {
        show(title, null);
    }

    public void show(String title, BufferedImage background) {
        
        JFreeChart chart = createChart(title, background);

        ChartPanel cpanel = new ChartPanel(chart);
        cpanel.setPreferredSize(new Dimension(500, 500));
        getFrame().add(cpanel);
        getFrame().pack();
        getFrame().setVisible(true);
    }

    public void save(String name) {
        save(name, "data", null);
    }

    public void save(String name, String title) {
        save(name, title, null);
    }

    public void save(String name, String title, BufferedImage background) {
        JFreeChart chart = createChart(title, background);
        try {
            BufferedImage bi = chart.createBufferedImage((int) getSize().getWidth(), (int) getSize().getHeight());
            File outputfile = new File(name);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.err.println("Nepodařilo se zapsat");
        }
    }

	
	
	
}
