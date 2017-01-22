package cz.cvut.fjfi.golem.kocmanjin.shotdownload.charts;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kocmanjin on 1/22/17.
 */
public abstract class Abstract2DChart {

	private JFrame frame;
	private String xlabel = "Domain (X)";
    private String ylabel = "Range (Y)";
    private double[] xrange;
    private double[] yrange;
    private Dimension size = new Dimension(1000, 700);
    
    public Abstract2DChart() {
    	frame = new JFrame("graph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void setXrange(double low, double high) {
        xrange = new double[2];
        xrange[0] = low;
        xrange[1] = high;
    }

    public void setYrange(double low, double high) {
        yrange = new double[2];
        yrange[0] = low;
        yrange[1] = high;
    }
    
    public JFrame getFrame() {
    	return frame;
    }

    public double[] getYrange() {
    	return yrange;
    }
    
    public double[] getXrange() {
    	return xrange;
    }
    
    public String getXlabel() {
    	return xlabel;
    }
    
    public String getYlabel() {
    	return ylabel;
    }
    
    public void setYlabel(String ylabel) {
        this.ylabel = ylabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }

    public void setSize(int width, int height) {
        size = new Dimension(width, height);
    }
    
    public Dimension getSize() {
    	return size;
    }
	
}
