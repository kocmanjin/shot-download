package cz.cvut.fjfi.golem.kocmanjin.shotdownload.charts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by kocmanjin on 1/22/17.
 */
public class LineChart extends Abstract2DChart {

    private XYSeriesCollection dataset;
    private boolean points = false;
    private boolean lines = true;
    
    private ArrayList<MyAnnotation> annotations = new ArrayList<MyAnnotation>();

    public LineChart() {
        dataset = new XYSeriesCollection();
    }

    public void addData(String title, Double[] time, Double[] data) {
    	if (time.length != data.length) {
    		throw new IllegalArgumentException("vstupni data nemaji stejny pocet radku");
    	}
    	
    	XYSeries series1 = new XYSeries(title);
    	for (int i = 0; i < data.length; i++) {
    		series1.add(time[i], data[i]);
    	}
    	dataset.addSeries(series1);
    }
    
    public void addData(String title, List<Double> list) {
    	XYSeries series1 = new XYSeries(title);
    	int i = 0;
    	for (Double data : list) {
    		series1.add(i++, data);
    	}
    	dataset.addSeries(series1);
    }
    
    public void addData(String title, List<Double> time, List<Double> value) {
    	XYSeries series1 = new XYSeries(title);
    	if (time.size() != value.size()) {
    		throw new IllegalArgumentException("vstupni data nemaji stejny pocet radku");
    	}
    	for (int i = 0; i < time.size(); i++) {
    		series1.add(time.get(i), value.get(i));
    	}
    	dataset.addSeries(series1);
    }
    
    public void addData(String title, double[] time, double[] data) {
        if (time.length != data.length) {
            throw new IllegalArgumentException("vstupni data nemaji stejny pocet radku");
        }

        XYSeries series1 = new XYSeries(title);
        for (int i = 0; i < data.length; i++) {
            series1.add(time[i], data[i]);
        }
        dataset.addSeries(series1);
    }

    public void addData(String title, double x, double y) {
        XYSeries series1 = new XYSeries(title);
        series1.add(x, y);
        dataset.addSeries(series1);
    }

    public void addData(String title, double[][] data) {
        if (data.length != 2) {
            throw new IllegalArgumentException("vstupni data nemaji dva sloupce");
        }
        addData(title, data[0], data[1]);
    }

    public void addData(String title, double[][] data, int sloupec) {
        addData(title, data[0], data[sloupec]);
    }

//    public void addData(String title, double[][] data, int sloupec, double multiplication) {
//        double[] column = SignalModification.multiplication(data[sloupec], multiplication);
//        addData(title, data[0], column);
//    }

    public void addData(String title, double[] data) {
        XYSeries series1 = new XYSeries(title);
        for (int i = 0; i < data.length; i++) {
            series1.add(i, data[i]);
        }
        dataset.addSeries(series1);
    }

    public void addData(String title, double[] time, double[] data, int from, int to) {
        if (time.length != data.length) {
            throw new IllegalArgumentException("vstupni data nemaji stejny pocet radku");
        }

        XYSeries series1 = new XYSeries(title);
        for (int i = from; i < to; i++) {
            series1.add(time[i], data[i]);
        }
        dataset.addSeries(series1);
    }

    public void addDataAnot(String title, ArrayList<MyAnnotation> list) {
        XYSeries series1 = new XYSeries(title);
        for (int i = 0; i < list.size(); i++) {
            series1.add(list.get(i).x, list.get(i).y);
            if (list.get(i).text != null) {
                annotations.add(list.get(i));
            }
        }
        dataset.addSeries(series1);
    }
    
    public void setLines(boolean lines) {
        this.lines = lines;
    }

    public void setPoints(boolean points) {
        this.points = points;
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
        cpanel.setPreferredSize(new Dimension(600, 350));
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

    
    
    private JFreeChart createChart(String title, BufferedImage background) {
        if (title == null) {
            title = "data";
        }
        XYLineAndShapeRenderer render = new XYLineAndShapeRenderer(lines, points);
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
        for (MyAnnotation anot : annotations) {
            XYTextAnnotation a = new XYTextAnnotation(anot.text, anot.x, anot.y);
            a.setFont(new Font("SansSerf", Font.PLAIN, 14));
            plot.addAnnotation(a);
            
        }
        return new JFreeChart(title, plot);
    }

    public static class MyAnnotation {

        public String text;
        public double x;
        public double y;

        public MyAnnotation(String text, double x, double y) {
            this.text = text;
            this.x = x;
            this.y = y;
        }
        
    }

}
