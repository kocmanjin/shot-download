/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fjfi.golem.kocmanjin.shotdownload;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author klostha
 */
public class IOFile {
    
    private static final String channelOpen = "Otevírám kanál: ";
    
    static {
        Locale.setDefault(Locale.ENGLISH);
    }
    
    public static File getFile(String nazev) {
		if ((nazev == null)||(nazev.length() == 0)) {
			throw new IllegalArgumentException("Nazev souboru nesmi byt prazdny");
		}
		if (nazev.startsWith(File.separator)) {
			nazev = nazev.substring(1);
		}
		if (nazev.lastIndexOf(File.separator) > 0) {
			String path = nazev.substring(0, nazev.lastIndexOf(File.separator));
			File outputfile = new File(path);
			outputfile.mkdirs();
		}
        File ret = new File(nazev);
		return ret;
	}
    
    /** Scanner pro nacteni vstupnch dat */
    /** Vystupni proud */
//    private static PrintStream output;
    
    public static String loadStringFromURL(String address) {
        URL url = null;
        Scanner input;
        String ret = null;
        try {
            url = new URL(address);
            System.out.println(channelOpen + address);
            input = new Scanner(url.openStream());
            ret = input.nextLine();
        } catch (MalformedURLException ex) {
            System.err.println("Chybna adresa "+url.getPath()+". " + ex.getLocalizedMessage());
            return null;
        } catch (IOException ex) {
            System.err.println("Chyba otevírání souboru na adrese "+url.getPath()+". " + ex.getLocalizedMessage());
            return null;
        }
        
        return ret;
    }
    
    
    public static double loadDoubleFromURL(String address) {
        Scanner input;
        URL url = null;
        try {
            url = new URL(address);
            System.out.println(channelOpen + address);
            input = new Scanner(url.openStream());
        } catch (MalformedURLException ex) {
            System.err.println("Chybna adresa "+url.getPath()+". " + ex.getLocalizedMessage());
            return 0;
        } catch (IOException ex) {
            System.err.println("Chyba otevírání souboru na adrese "+url.getPath()+". " + ex.getLocalizedMessage());
            return 0;
        }

        return input.nextDouble();
    }
    
        public static int loadIntFromURL(String address) {
        Scanner input;
        URL url = null;
        try {
            url = new URL(address);
            System.out.println(channelOpen + address);
            input = new Scanner(url.openStream());
        } catch (MalformedURLException ex) {
            System.err.println("Chybna adresa "+url.getPath()+". " + ex.getLocalizedMessage());
            return 0;
        } catch (IOException ex) {
            System.err.println("Chyba otevírání souboru na adrese "+url.getPath()+". " + ex.getLocalizedMessage());
            return 0;
        }

        return input.nextInt();
    }
        

    public static String loadStrng(String nazev) {
        BufferedReader reader;
        String ret = null;
        try {
            reader = new BufferedReader(new FileReader(nazev));
            System.out.println(channelOpen + nazev);
            ret = reader.readLine();
            reader.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            System.err.println("Chyba pri cteni souboru " + nazev + ".");
            return null;
        }
        return ret;
    }
    
    
    public static double loadDouble(String nazev) {
        Scanner input;
        double data = 0;
        try {
            input = new Scanner(new File(nazev));
            data = input.nextDouble();
        }
        catch (FileNotFoundException e) {
            System.err.println("Chyba otevírání souboru "+nazev+".");
            return data;
        }
        return data;
    }
    
    public static int loadInt(String nazev) {
        Scanner input;
        int data = 0;
        try {
            input = new Scanner(new File(nazev));
            data = input.nextInt();
        }
        catch (FileNotFoundException e) {
            System.err.println("Chyba otevírání souboru "+nazev+".");
            return data;
        } catch (NoSuchElementException ex) {
            System.err.println(nazev);
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        return data;
    }
    
//    public static double[][] loadDataFromNITurbo(String address) {
//        Scanner input = null;
//        BufferedReader reader;
//        URL url = null;
//        int lines = 0;
//        try {
//            url = new URL(address);
//            System.out.println("Oteviram kanal");
//            reader = new BufferedReader(new InputStreamReader(url.openStream()));
//            System.out.println("Kanal otevren");
//            while (reader.readLine() != null) {
//                lines++;
//            }
//            reader.close();
//        } catch (MalformedURLException ex) {
//            System.err.println("Chybna adresa "+address+". " + ex.getLocalizedMessage());
//        } catch (IOException ex) {
//            System.err.println("Chyba otevírání souboru na adrese "+address+". " + ex.getLocalizedMessage());
//        }
//        System.out.println("Pocet radku: " + lines);
//        
//        try {
//            input = new Scanner(url.openStream());
//        } catch (IOException ex) {
//            System.err.println("Chyba otevírání souboru na adrese "+address+". " + ex.getLocalizedMessage());
//        }
//        
//        String radek = input.nextLine();
//        String delimiter = "\u0009";
//        if (radek.contains("\u0020")) {
//            delimiter = "\u0020";
//        }
//        int pocet_sloupcu = radek.split(delimiter).length;
//        double[][] data = new double[pocet_sloupcu][lines];
//        for (int i = 1; i < pocet_sloupcu; i++) {
//            data[i][0] = Double.parseDouble(radek.split(delimiter)[i]);
//        }
//        
//        System.out.println("Nacitam " + url.getPath());
//        input.useLocale(Locale.ENGLISH);
//        for (int i = 1; i < lines; i++) {
//            for (int j = 1; j < pocet_sloupcu; j++) {
//                data[j][i] = input.nextDouble();
//            }
//        }
//        System.out.println("data nacteny");
//        return data;
//        
//    }
//    
//    
    public static double[][] loadDataFromURL(String address) {
        Scanner input = null;
        BufferedReader reader;
        URL url = null;
        int lines = 0;
        try {
            url = new URL(address);
            System.out.println(channelOpen + address);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
        } catch (MalformedURLException ex) {
            System.err.println("Chybna adresa "+address+". " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            System.err.println("Chyba otevírání souboru na adrese "+address+". " + ex.getLocalizedMessage());
        }
        try {
            input = new Scanner(url.openStream());
        } catch (IOException ex) {
            System.err.println("Chyba otevírání souboru na adrese "+address+". " + ex.getLocalizedMessage());
        }
        
        String radek = input.nextLine();
        String delimiter = "\u0009";
        if (radek.contains("\u0020")) {
            delimiter = "\u0020";
        }
        int pocet_sloupcu = radek.split(delimiter).length;
        double[][] data = new double[pocet_sloupcu][lines];
        for (int i = 0; i < pocet_sloupcu; i++) {
            data[i][0] = Double.parseDouble(radek.split(delimiter)[i]);
        }
        
        System.out.println("\tNacitam " + url.getPath());
        input.useLocale(Locale.ENGLISH);
        int procento = lines/100;
        int pocetProcent = 0;
        System.out.print("[");
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println("]");
        System.out.print("[");
        for (int i = 1; i < lines; i++) {
            for (int j = 0; j < pocet_sloupcu; j++) {
                data[j][i] = input.nextDouble();
            }
            if (i/procento != pocetProcent) {
                pocetProcent = i/procento;
                System.out.print("=");
            }
        }
        System.out.println("]");
        System.out.println("\tdata nacteny");
        return data;
        
    }
    
    
    public static double[][] loadData(String nazev) {
        Scanner input = null;
        int lines = 0;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(nazev));
            System.out.println(channelOpen + nazev);
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            System.err.println("Chyba pri cteni souboru " + nazev + ".");
            return null;
        }
//        System.out.println("poce radek " + lines);
        if (lines == 0) {//soubor je prazdny, vracim null
            return null;
        }
        
        try {
            input = new Scanner(new File(nazev));
        }
        catch (FileNotFoundException e) {
            System.err.println("Chyba otevírání souboru "+nazev+".");
        }
        
        String radek = input.nextLine();
        String delimiter = "\u0009";
        if (radek.contains("\u0020")) {
            delimiter = "\u0020";
        }
        int pocet_sloupcu = radek.split(delimiter).length;
        double[][] data = new double[pocet_sloupcu][lines];
        for (int i = 0; i < pocet_sloupcu; i++) {
            data[i][0] = Double.parseDouble(radek.split(delimiter)[i]);
        }
        
        input.useLocale(Locale.ENGLISH);
        int hotovo = 0;
        int procento = lines/100;
        System.out.print("[");
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println("]");
        System.out.print("[");
        for (int i = 1; i < lines; i++) {
            for (int j = 0; j < pocet_sloupcu; j++) {
                data[j][i] = input.nextDouble();
            }
            if (i/procento != hotovo) {
                hotovo = i/procento;
                System.out.print("=");
            }
        }
        System.out.println("=]");
        input.close();
        return data;
    }
   
    
    public static BufferedImage loadImageFromURL(String address) {
        BufferedImage img;
        URL url;
        try {
            url = new URL(address);
            System.out.println(channelOpen + address);
            return ImageIO.read(url);
        } catch (MalformedURLException ex) {
            System.err.println("Chybna adresa "+address+". " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            System.err.println("Chyba otevírání souboru na adrese "+address+". " + ex.getLocalizedMessage());
        }
        return null;
    }
    
    
    public static BufferedImage loadImage(String nazev) {
        BufferedImage image  = null;
        try {
            return ImageIO.read(new File(nazev));
        } catch (IOException ex) {
            System.err.println("Image could not be loaded: " + ex.getLocalizedMessage());
        }
        return null;
    }
    
    public static boolean saveImage(String nazev, BufferedImage img) {
        try {
            ImageIO.write(img, "png", getFile(nazev));
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public static boolean saveString(String nazev, String value) {
        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter(getFile(nazev)));
        } catch (SecurityException ex) { 
            System.err.println("Nemate pravo zapisu" + ex.getLocalizedMessage());
            return false;
        } catch (FileNotFoundException ex) {
            System.err.println("Chyba pri vytvareni souboru" + ex.getLocalizedMessage());
            return false;
        } catch (IOException ex) {
            System.err.println("Chyba pri zapisovani do souboru" + ex.getLocalizedMessage());
            return false;
        }
        
        try {
            output.write(value);
            output.close();
        } catch (IOException ex) {
            System.err.println("Chyba pri zapisovani do souboru" + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
    public static boolean saveData(String nazev, double[]... data) {
        return saveData(nazev, false, data);
    }

    public static boolean saveData(String nazev, boolean append, double[]... data) {
    	for (int i = 1; i < data.length; i++) {
    		if (data[i-1].length != data[i].length) {
    			throw new IllegalArgumentException("Zadana matice neni obdelnikova!");
    		}
    	}
        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter(getFile(nazev), append));
        } catch (SecurityException ex) { 
            System.err.println("Nemate pravo zapisu" + ex.getLocalizedMessage());
            return false;
        } catch (FileNotFoundException ex) {
            System.err.println("Chyba pri vytvareni souboru" + ex.getLocalizedMessage());
            return false;
        } catch (IOException ex) {
            System.err.println("Chyba pri zapisovani do souboru" + ex.getLocalizedMessage());
            return false;
        }
        
        
        System.out.println("zacinam ukladat");
        int hotovo = 0;
        int procento = data[0].length/100;
        System.out.print("[");
        for (int i = 0; i < 100; i++) {
            System.out.print("=");
        }
        System.out.println("]");
        System.out.print("[");
        try {
            for (int j = 0; j < data[0].length; j++) {
                for (int i = 0; i < data.length; i++) {
                    output.write(String.format("%.6f\t", data[i][j]));
                }
                output.newLine();
                if (j/procento != hotovo) {
                    hotovo = j/procento;
                    System.out.print("=");
                }
            }
            System.out.println("=]");
            output.close();
        } catch (IOException ex) {
            System.err.println("Chyba pri zapisovani do souboru" + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
    
    public static boolean saveData(String nazev, ArrayList<Double> data) {
    	 BufferedWriter output;
         try {
             
             output = new BufferedWriter(new FileWriter(getFile(nazev)));
         } catch (SecurityException ex) { 
             System.err.println("Nemate pravo zapisu" + ex.getLocalizedMessage());
             return false;
         } catch (FileNotFoundException ex) {
             System.err.println("Chyba pri vytvareni souboru" + ex.getLocalizedMessage());
             return false;
         } catch (IOException ex) {
             System.err.println("Chyba pri zapisovani do souboru" + ex.getLocalizedMessage());
             return false;
         }
         
         System.out.println("zacinam ukladat");
         try {
             for (Double doub : data) {
                 output.write(String.format("%.10f\t", doub));
             }
             output.close();
         } catch (IOException ex) {
             System.err.println("Chyba pri zapisovani do souboru" + ex.getLocalizedMessage());
             return false;
         }
         return true;
    }
    
    public static boolean saveData(String nazev, ArrayList<Double> time, ArrayList<Double> data) {
    	
    	if (time.size() != data.size()) {
    		throw new IllegalArgumentException("Zadana matice neni obdelnikova!");
    	}
    	
    	BufferedWriter output;
    	try {
    		output = new BufferedWriter(new FileWriter(getFile(nazev)));
    	} catch (SecurityException ex) { 
    		System.err.println("Nemate pravo zapisu" + ex.getLocalizedMessage());
    		return false;
    	} catch (FileNotFoundException ex) {
    		System.err.println("Chyba pri vytvareni souboru" + ex.getLocalizedMessage());
    		return false;
    	} catch (IOException ex) {
    		System.err.println("Chyba pri zapisovani do souboru" + ex.getLocalizedMessage());
    		return false;
    	}

    	System.out.println("zacinam ukladat");
    	try {
    		for (int i = 0; i < time.size(); i++) {
    			output.write(String.format("%.10f\t%.10f\n", time.get(i), data.get(i)));
    		}
    		output.close();
    	} catch (IOException ex) {
    		System.err.println("Chyba pri zapisovani do souboru" + ex.getLocalizedMessage());
    		return false;
    	}
    	return true;
    }
    
    
    public static boolean saveDouble(String nazev, double data) {
        FileWriter output;
        try {
            output = new FileWriter(getFile(nazev));
            output.write(data + "");
            output.close();
        } catch (IOException ex) {
            System.err.println("Chyba pri zapisovani do souboru: " + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
    
    public static boolean saveInt(String nazev, int data) {
        FileWriter output;
        try {
            output = new FileWriter(getFile(nazev));
            output.write(data + "");
            output.close();
        } catch (IOException ex) {
            System.err.println("Chyba pri zapisovani do souboru: " + ex.getLocalizedMessage());
            return false;
        }
        return true;
    }
    
}
