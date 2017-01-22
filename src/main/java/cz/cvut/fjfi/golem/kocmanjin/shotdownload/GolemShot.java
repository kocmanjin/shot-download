/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fjfi.golem.kocmanjin.shotdownload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by kocmanjin on 1/22/17.
 */
public class GolemShot {
	
//	private static final String directory = "shots";
//
//    private static final String prefix = "http://golem.fjfi.cvut.cz/utils/data/";
//    private static final String prefix2 = "http://golem.fjfi.cvut.cz/shots/";
//    private static final String mirnovCoilsPath = "/nistandard6132";
//    private static final String papouchSTPath = "/papouch_st";
//    private static final String papouchJIPath = "/papouch_ji";
//    private static final String basicPath = "/nistandard";
//    private static final String turboPath = "/niturbo";
//    private static final String UbLimitPath = "/Ub";
//    private static final String UcdLimitPath = "/Ucd";
//    private static final String torMagFieldPath = "/toroidal_field.txt";
//    private static final String plasmaStartPath = "/plasma_start";
//    private static final String plasmaEndPath = "/plasma_end";
//    private static final String pressurePath = "/pressure";
//    private static final String UloopPath = "/loop_voltage.txt";
//    private static final String plasmaPath = "/plasma";
//    private static final String breakdownVoltagePath = "/breakdown_voltage";
////    private static final String cameraPath1 = "/diagnostics/Radiation/0211FastCamera.ON/1/plasma_all.png";
//    private static final String cameraPath1 = "/diagnostics/Radiation/0211FastCamera.ON/1/CorrectedRGB.png";
//    private static final String cameraPath2 = "/diagnostics/Radiation/0211FastCamera.ON/2/CorrectedRGB.png";
////    private static final String cameraPath2 = "/diagnostics/Radiation/0211FastCamera.ON/2/plasma_all.png";
//    private static final String inputAmplifierPath = "/input_amplifier";
//    private static final String quadrCurrentPath = "/rogowsky_quadr";
//    private static final String TorMagFieldDerPath = "/btor";
//    private static final String plasmaCurrentPath = "/plasma_current.txt";
////    private static final String NIturbo_RTPath = "/DAS/0513NIturbo_RT.ON/input_data.txt";
//    private static final String NIturbo_RTPath = "/DAS/0513NIturbo_RT.ON/NiData.txt";
//    private static final String NIoctopusPath = "/nioctopus";
//    private static final String commentPath = "/wwwcomment";
//    private static final String tagsPath = "/tags";
//    private static final String tcdPath = "/tcd";
//    private static final String chamberCurrentPath = "/chamber_current";
//
//    private static final boolean saveAnalyses = false;
//
//    private static final int shot600_400 = 12149;
//    private static final int shot800_400 = 11569;
//    private static final int shot1200_400 = 11467;
//    private static final int shot1000_500 = 11800;
//
//
//    public static final double[] btMcCorrel = {0.006, 0.036, 0.00946, 0};
//
//    public static final double mcCoeff = 0.003705;
//    public static final double MirnovPosition_m = 0.093;

    private Config config;
    
    private Integer shotNo;
    private Integer plasma;
    private Integer UbLimit;
    private Integer UcdLimit;
    private Double  pressure;
    private Double  plasmaStart;
    private Double  plasmaEnd;
    private Double  breakdownVoltage;
    private Double  breakdownMagField;
    private Double  Tcd;

    private double[][] nistandard;
    private double[][] niturbo;
    private double[][] mirnovCoils;
    private double[][] mirnovCoilsIntegr;
    private double[][] mirnovCoilsIntegrClean;
    private double[][] torMagField;
    private double[][] torMagFieldDer;
    private double[][] Uloop;
    private double[][] displaceZ;
    private double[][] inputAmplifier;
    private double[][] quadrCurrent;
    private double[][] plasmaCurrent;
    private double[][] papouchST;
    private double[][] papouchJI;
    private double[][] NIturbo_RT;
    private double[][] NIturbo_RT_int;
    private double[][] NIturbo_RT_clear;
    private double[][] chamberCurrent;
    private double[][] NIoctopus;

    private BufferedImage verticalCamera1;
    private BufferedImage verticalCamera2;
    private String comment;
    private String tags;
    private File homeDir;
    
    public GolemShot(int shotNo) {
        if (config == null) {
            try {
                config = Config.configFactory();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.shotNo = shotNo;
        homeDir = IOFile.getFile(config.getShotsLocalDirectory() + File.separator + shotNo + "/");
    }
    
    
    public int getShotNo() {
        return shotNo;
    }
    
//    public String getTags() {
//        if (tags == null) {
//            tags = getString(tagsPath);
//        }
//        return tags;
//    }

    
    public double[][] getPlasmaCurrent() {
        if (plasmaCurrent == null) {
            plasmaCurrent = getData(config.getPlasmaCurrent());
        }
        return plasmaCurrent;
    }
    
//    public double[][] getChamberCurrent() {
//    	if (chamberCurrent == null) {
//    		chamberCurrent = getData(chamberCurrentPath);
//    	}
//    	if (saveAnalyses) {
//    		IOFile.saveData(homeDir.getPath() + "/Ichamber.txt", chamberCurrent);
//    	}
//    	return chamberCurrent;
//    }

    public double[][] getPapouchST() {
    	if (papouchST== null) {
    		papouchST = getData(config.getPapouchST());
    	}
    	return papouchST;
    }

    public double[][] getPapouchJI() {
    	if (papouchJI== null) {
    		papouchJI = getData(config.getPapouchJI());
    	}
    	return papouchJI;
    }

    public double[][] getTorMagFieldDer() {
        if (torMagFieldDer == null) {
            torMagFieldDer = getData(config.getTorMagField());
        }
        return torMagFieldDer;
    }
    
//    public double[][] getNIturbo_RT() {
//        if (NIturbo_RT == null) {
////            double[][] data = IOFile.loadDataFromNITurbo(prefix2 + getShotNo() + NIturbo_RTPath);
////            NIturbo_RT = new double[data.length-1][];
////            for (int i = 1; i < data.length; i++) {
////                NIturbo_RT[i-1] = data[i];
////            }
////            NIturbo_RT[0] = SignalModification.plus(NIturbo_RT[0], -NIturbo_RT[0][0]);
////            NIturbo_RT[0] = SignalModification.multiplication(NIturbo_RT[0], 1e-9);
//            NIturbo_RT = getData2(NIturbo_RTPath);
//        }
//        return NIturbo_RT;
//    }
//
//    public double[][] getNIoctopus() {
//        if (NIoctopus == null) {
//            NIoctopus = getData(NIoctopusPath);
//        }
//        return NIoctopus;
//    }
//
//    public double[][] getNIturbo_RT_int() {
//        if (NIturbo_RT_int == null) {
//            NIturbo_RT_int = new double[getNIturbo_RT().length][];
//            NIturbo_RT_int[0] = NIturbo_RT[0];
//            for (int i = 1; i < getNIturbo_RT().length; i++) {
//                NIturbo_RT_int[i] = SigMod.integrate(2e-5, tyristor(0.005, 50000, NIturbo_RT[i]), 150, mcCoeff);
//            }
//        }
//        return NIturbo_RT_int;
//    }
//
//    public double[][] getNIturbo_RT_clear() {
//        if (NIturbo_RT_clear == null) {
//            NIturbo_RT_clear = new double[3][getNIturbo_RT()[0].length];
//            for (int i = 0; i < NIturbo_RT_clear[0].length; i++) {
//                NIturbo_RT_clear[0][i] = NIturbo_RT[0][i];
//                NIturbo_RT_clear[1][i] = NIturbo_RT[1][i];
//                NIturbo_RT_clear[2][i] = NIturbo_RT[2][i];
//            }
//
//            double sum1 = 0;
//            double sum2 = 0;
//            double sum3 = 0;
//            for (int i = 0; i < 20; i++) {
//                sum1 += NIturbo_RT_clear[1][i];
//                sum2 += NIturbo_RT_clear[2][i];
//                sum3 += NIturbo_RT[3][i];
//            }
//            sum1 = sum1/20;
//            sum2 = sum2/20;
//            sum3 = sum3/20;
//            for (int i = 0; i < NIturbo_RT_clear[0].length; i++) {
//                NIturbo_RT_clear[1][i] -= (NIturbo_RT[3][i] - sum3)*0.35 + sum1;
//                NIturbo_RT_clear[2][i] -= (NIturbo_RT[3][i] - sum3)*0.7 + sum2;
//            }
//        }
//        return NIturbo_RT_clear;
//    }
//
////    public double[][] getNIturbo_RT_disZ() {
////        double[][] NIturbo_RT_disZ = new double[2][];
////        NIturbo_RT_disZ[0] = getNIturbo_RT_clear()[0];
////        NIturbo_RT_disZ[1] = new double[NIturbo_RT_clear[1].length];
////        double[] mc5 = getNIturbo_RT_clear()[1];
////        double[] mc13 = getNIturbo_RT_clear()[2];
////        for (int i = 0; i < NIturbo_RT_disZ[0].length; i++) {
////            NIturbo_RT_disZ[1][i] = (mc5[i] - mc13[i]) * MirnovPosition_m / (mc5[i] + mc13[i]);
////        }
////        return NIturbo_RT_disZ;
////    }
//
//    public double[][] getNITurbo_RT_disZNew(boolean correction) {
//        double[][] NIturbo_RT_disZ = new double[2][];
//
//        NIturbo_RT_disZ[0] = getNIturbo_RT_clear()[0];
//        NIturbo_RT_disZ[1] = new double[NIturbo_RT_clear[1].length];
//
//        double[] mc5  = getNIturbo_RT_clear()[1];
//        double[] mc13 = getNIturbo_RT_clear()[2];
//
//        for (int i = 0; i < NIturbo_RT_disZ[0].length; i++) {
//            NIturbo_RT_disZ[1][i] = (mc5[i] - mc13[i]) * MirnovPosition_m / (mc5[i] + mc13[i]);
//        }
//        return NIturbo_RT_disZ;
//    }
//
//    public double[][] getInputAmplifier() {
//        if (inputAmplifier == null) {
//            inputAmplifier = getData(inputAmplifierPath);
//        }
//        return inputAmplifier;
//    }
//
//    public double[][] getQuadrCurrent() {
//        if (quadrCurrent == null) {
//            quadrCurrent = getData(quadrCurrentPath);
////            if (SignalModification.getMax(quadrCurrent[1]) < 0.01) {
////                quadrCurrent = new double[2][quadrCurrent[1].length];
////            }
//            quadrCurrent[1] = SigMod.multiplication(quadrCurrent[1], 1/0.005);
//        }
//        if (saveAnalyses) {
//            IOFile.saveData(homeDir.getPath() + "/quadrCurrent.txt", quadrCurrent);
//        }
//        return quadrCurrent;
//    }
//
//    public double[][] getDisplaceZ(boolean correction) {
//        if (displaceZ == null) {
//            displaceZ = new double[2][];
//            int start = (int) (getPlasmaStart()*1e6) + 100;
//            int end = (int) (getPlasmaEnd()*1e6) -100;
//            displaceZ[0] = new double[end-start];
//            displaceZ[1] = new double[displaceZ[0].length];
//            double[] mc5 = getMirnovCoilsIntegrClear(correction)[2];
//            double[] mc13 = getMirnovCoilsIntegrClear(correction)[4];
//            for (int i = 0; i < displaceZ[0].length; i++) {
////                mc5[i] *= -1;
////                mc13[i] *= -1;
//                displaceZ[0][i] = (i+start)*1e-6;
//                displaceZ[1][i] = ( mc5[i+start] - mc13[i+start]) * MirnovPosition_m / (mc5[i+start] + mc13[i+start]);
//            }
//        }
//        if (saveAnalyses) {
//            IOFile.saveData(homeDir.getPath() + "/displaceZ.txt", displaceZ);
//        }
//
//        return displaceZ;
////        getMirnovCoils();
////        double[] mc5 = SignalModification.integrate(mirnovCoils[0][1], mirnovCoils[2], 4500, mcCoeff);
////        double[] mc13 = SignalModification.integrate(mirnovCoils[0][1], mirnovCoils[4], 4500, mcCoeff);
////        GolemShot vacuum = new GolemShot(shotVacuum);
////        double[][] mc = vacuum.getMirnovCoils();
////        double[] vac5 = SignalModification.integrate(mc[0][1], mc[2], 4500, mcCoeff);
////        double[] vac13 = SignalModification.integrate(mc[0][1], mc[4], 4500, mcCoeff);
////        double[] dis = new double[mc13.length];
////        for (int i = 0; i < mc13.length; i++) {
////            mc5[i] -= vac5[i];
////            mc13[i] -= vac13[i];
////            mc13[i] *= -1;
////            // mc13 je otočená, proto se násobí minus jedničkou
////            dis[i] = ( mc5[i] - mc13[i]) * MirnovPosition_m / (mc5[i] + mc13[i]);
////        }
////        return dis;
//    }
//
    public double[][] getNIStandard() {
        if (nistandard == null) {
            nistandard = getData(config.getNIStandard());
        }
        return nistandard;
    }
//
//    public double[][] getNITurbo() {
//        if (niturbo == null) {
//            niturbo = getData(turboPath);
//        }
//        return niturbo;
//    }
//
//
//    public double[][] getMirnovCoils() {
//        if (mirnovCoils == null) {
//            mirnovCoils = getData(mirnovCoilsPath);
//            if (shotNo < 12080) {
//                mirnovCoils[4] = SigMod.multiplication(mirnovCoils[4], -1);
//            }
//        }
//        return mirnovCoils;
//    }
//
//    public double[][] getMirnovCoilsIntegr() {
//        if (mirnovCoilsIntegr == null) {
//            mirnovCoilsIntegr = getMirnovCoils();
//            for (int i = 1; i < mirnovCoilsIntegr.length; i++) {
//                mirnovCoilsIntegr[i] = SigMod.integrate(mirnovCoilsIntegr[0][1], mirnovCoilsIntegr[i], 4500, mcCoeff);
//            }
//        }
//        return mirnovCoilsIntegr;
//    }
//
//    public double[][] getMirnovCoilsIntegrClear(boolean correction) {
//        if (mirnovCoilsIntegrClean == null) {
//            mirnovCoilsIntegrClean = new double[getMirnovCoilsIntegr().length][];
//            for (int i = 0; i < getMirnovCoilsIntegr().length; i++) {
//                mirnovCoilsIntegrClean[i] = new double[getMirnovCoilsIntegr()[i].length];
//                System.arraycopy(getMirnovCoilsIntegr()[i], 0, mirnovCoilsIntegrClean[i], 0, getMirnovCoilsIntegr()[i].length);
//            }
////            mirnovCoilsIntegrClean = getMirnovCoilsIntegr();
//            GolemShot shot = getVacuumShot();
//            double[][] mirnOld = shot.getMirnovCoilsIntegr();
//            for (int i = 0; i < mirnovCoilsIntegrClean[0].length; i++) {
//                mirnovCoilsIntegrClean[1][i] -= mirnOld[1][i];
//                mirnovCoilsIntegrClean[2][i] -= mirnOld[2][i];
//                mirnovCoilsIntegrClean[3][i] -= mirnOld[3][i];
//                mirnovCoilsIntegrClean[4][i] -= mirnOld[4][i];
//            }
//            if (correction) {
//                double[][] quadr = getQuadrCurrent();
//                for (int i = 0; i < mirnovCoilsIntegrClean[1].length; i++) {
////                    mirnovCoilsIntegrClean[2][i] -= quadr[1][i]*7.33e-6;
//                    mirnovCoilsIntegrClean[2][i] -= quadr[1][i]*0.00146*0.005;
////                    mirnovCoilsIntegrClean[4][i] += quadr[1][i]*5.62e-6;
//                    mirnovCoilsIntegrClean[4][i] += quadr[1][i]*0.00112*0.005;
//                }
//            }
//        }
//        return mirnovCoilsIntegrClean;
//    }
//
//    public GolemShot getVacuumShot() {
//        int shotVacuum;
//        switch (getUbLimit()) {
//            case 600:
//                shotVacuum = shot600_400;
//                break;
//            case 800:
//                shotVacuum = shot800_400;
//                break;
//            case 1000:
//                shotVacuum = shot1000_500;
//                break;
//            case 1200:
//                shotVacuum = shot1200_400;
//                break;
//            default:
//                throw new IllegalArgumentException("nenalezen vakuový shot s odpovidajicimi parametry");
//        }
//        return new GolemShot(shotVacuum);
//    }
//
//    public double[][] getToroidalMagField() {
//        if (torMagField == null) {
//            torMagField = getData(torMagFieldPath);
//        }
//        return torMagField;
//    }
//
    public double[][] getUloop() {
        if (Uloop == null) {
            Uloop = getData(config.getUloop());
        }
        return Uloop;
    }
//
//    public boolean getPlasma() {
//        if (plasma < 0) {
//            double p = getDouble(plasmaPath);
//            if (p > 0.5) {
//                plasma = 1;
//            } else {
//                plasma = 0;
//            }
//
////            plasma = getInt(plasmaPath);
//        }
//        if (plasma == 1) {
//            return true;
//        }
//        return false;
//    }
//
//    public int getUbLimit() {
//        if (UbLimit < 0) {
//            UbLimit = (int) getDouble(UbLimitPath);
//        }
//        return UbLimit;
//    }
//
//    public double getTcd() {
//        if (Tcd<0) {
//            Tcd = getDouble(tcdPath);
//        }
//        return Tcd;
//    }
//
//    public int getUcdLimit() {
//        if (UcdLimit < 0) {
//            UcdLimit = (int) getDouble(UcdLimitPath);
//        }
//        return UcdLimit;
//    }
//
//    public double getPlasmaStart() {
//        if (plasmaStart < 0) {
//            plasmaStart = getDouble(plasmaStartPath);
//        }
//        return plasmaStart;
//    }
//
//    public double getPlasmaEnd() {
//        if (plasmaEnd < 0) {
//            plasmaEnd = getDouble(plasmaEndPath);
//        }
//        return plasmaEnd;
//    }
//
//    public double getPressure() {
//        if (pressure < 0) {
//            pressure = getDouble(pressurePath);
//        }
//        return pressure;
//    }
//
//    public double getBreakdownVoltage() {
//        if ((breakdownVoltage < 0) && getPlasma()) {
//            breakdownVoltage = getDouble(breakdownVoltagePath);
//        }
////        getUloop();
////        double max = 0;
////        double[] ul = Uloop[1];
////        for (int i = 0; i < 15000; i++) {
////            if (ul[i] > max) {
////                max = ul[i];
////            }
////        }
////        return max;
//        return breakdownVoltage;
//    }
//
//    public double getBreakdownVoltageMagField() {
//        if (breakdownMagField < 0) {
//            for (int i = 0; i < getToroidalMagField()[1].length; i++) {
//                if (getToroidalMagField()[0][i] > getPlasmaStart()) {
//                    breakdownMagField =  getToroidalMagField()[1][i];
//                }
//            }
//        }
//        return breakdownMagField;
//    }
//
//    public BufferedImage getVerticalCamera1() {
//        if (verticalCamera1 == null) {
//            verticalCamera1 = getImage(cameraPath1);
//        }
//        return verticalCamera1;
//    }
//
//    public BufferedImage getVerticalCamera2() {
//        if (verticalCamera2 == null) {
//            verticalCamera2 = getImage(cameraPath2);
//        }
//        return verticalCamera2;
//    }
//
//    private String getString(String name) {
//        String data;
//        File file = new File(homeDir.getPath() + "//" + name);
//        if (!file.exists()) {
//            data = IOFile.loadStringFromURL(prefix + shotNo + name);
//            IOFile.saveString(file.getPath(), data);
//        } else {
//            data = IOFile.loadStrng(file.getPath());
//        }
//        return data;
//    }
//
//    private BufferedImage getImage(String name) {
//        BufferedImage img;
//        File file = new File(homeDir.getPath() + File.separator + name);
//        if (!file.exists()) {
//            try {
//                img = IOFile.loadImageFromURL(prefix2 + shotNo + name);
//                IOFile.saveImage(file.getPath(), img);
//            } catch (IllegalArgumentException ex) {
//                System.err.println(ex.getMessage());
//                return null;
//            }
//        } else {
//            img = IOFile.loadImage(file.getPath());
//        }
//        return img;
//    }
//
//    private int getInt(String name) {
//        int data;
//        File file = new File(homeDir.getPath() + "//" + name);
//        if (!file.exists()) {
//            data = IOFile.loadIntFromURL(prefix + shotNo + name);
//            IOFile.saveInt(file.getPath(), data);
//        } else {
//            data = IOFile.loadInt(file.getPath());
//        }
//        return data;
//
//    }
//
//
//    private double getDouble(String name) {
//        double data;
//        File file = new File(homeDir.getPath() + "//" + name);
//        if (!file.exists()) {
//            data = IOFile.loadDoubleFromURL(prefix + shotNo + name);
//            IOFile.saveDouble(file.getPath(), data);
//        } else {
//            data = IOFile.loadDouble(file.getPath());
//        }
//        return data;
//    }
    private double[][] getData(String name) {
        double[][] data;
        File file = new File(homeDir.getPath() + "//" + name);
        if (!file.exists()) {
            data = IOFile.loadDataFromURL(config.getShotDataURL() + shotNo + name);
            IOFile.saveData(file.getPath(), data);
        } else {
            data = IOFile.loadData(file.getPath());
        }
        return data;
    }

//    private double[][] getData2(String name) {
//        double[][] data;
//        File file = new File(homeDir.getPath() + "//" + name);
//        if (!file.exists()) {
//            data = IOFile.loadDataFromURL(prefix2 + shotNo + name);
//            IOFile.saveData(file.getPath(), data);
//        } else {
//            data = IOFile.loadData(file.getPath());
//        }
//        return data;
//    }
//
//    public static double[] tyristor(double time, double freq, double[] data) {
//        double[] ret = new double[data.length];
//        System.arraycopy(data, 0, ret, 0, data.length);
//        int point = (int) (time * freq);
//        for (int i = point -10; i < point + 10; i++) {
//            ret[i] = 0;
//        }
//        return ret;
//    }
//
//    public static boolean isInRange(double in, double fit, double range) {
//        if ((in < fit + range)&&(in > fit - range)) {
//            return true;
//        }
//        return false;
//    }
    
}
