package cz.cvut.fjfi.golem.kocmanjin.shotdownload;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by kocmanjin on 1/22/17.
 */
public class Config {

    // ----------------------STATIC--------------------------------

    private static final String DEFAULT_CONFIG_FILE = "/cz/cvut/fjfi/golem/kocmanjin/shotdownload/ShotDownload.properties";

    private static Config instance;

    public static Config configFactory() throws IOException {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    // ------------------------INSTANCE----------------------------

    private Properties properties;

    private Config() throws IOException {
        properties = new Properties();
        properties.load(Config.class.getResourceAsStream(DEFAULT_CONFIG_FILE));
    }

    public String getShotsLocalDirectory() {
        return getPropertyRequired("shots.local.directory");
    }

    public String getShotDataURL() {
        return getPropertyRequired("shots.dataURL");
    }

    // ----------------------- DAS ----------------------------------

    public String getNIStandard() {
        return getPropertyRequired("nistandard");
    }

    public String getPapouchST() {
        return getPropertyRequired("papouchST");
    }

    public String getPapouchJI() {
        return getPropertyRequired("papouchJI");
    }

    // --------------------- Basic plasma diagnostics ---------------

    public String getUloop() {
        return getPropertyRequired("uloop");
    }

    public String getTorMagField() {
        return getPropertyRequired("torMagField");
    }

    public String getPlasmaCurrent() {
        return getPropertyRequired("plasmaCurrent");
    }




    private String getPropertyRequired(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Value for key '" + key + "' was not found!");
        }
        return value;
    }

//    private static final String prefix2 = "http://golem.fjfi.cvut.cz/shots/";
//    private static final String mirnovCoilsPath = "/nistandard6132";
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
//    //    private static final String cameraPath1 = "/diagnostics/Radiation/0211FastCamera.ON/1/plasma_all.png";
//    private static final String cameraPath1 = "/diagnostics/Radiation/0211FastCamera.ON/1/CorrectedRGB.png";
//    private static final String cameraPath2 = "/diagnostics/Radiation/0211FastCamera.ON/2/CorrectedRGB.png";
//    //    private static final String cameraPath2 = "/diagnostics/Radiation/0211FastCamera.ON/2/plasma_all.png";
//    private static final String inputAmplifierPath = "/input_amplifier";
//    private static final String quadrCurrentPath = "/rogowsky_quadr";
//    private static final String TorMagFieldDerPath = "/btor";
//    private static final String plasmaCurrentPath = "/plasma_current.txt";
//    //    private static final String NIturbo_RTPath = "/DAS/0513NIturbo_RT.ON/input_data.txt";
//    private static final String NIturbo_RTPath = "/DAS/0513NIturbo_RT.ON/NiData.txt";
//    private static final String NIoctopusPath = "/nioctopus";
//    private static final String commentPath = "/wwwcomment";
//    private static final String tagsPath = "/tags";
//    private static final String tcdPath = "/tcd";
//    private static final String chamberCurrentPath = "/chamber_current";




}
