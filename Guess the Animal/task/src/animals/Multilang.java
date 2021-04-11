package animals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

class Multilang {

    static final String MESS_EN = "src/main/resources/messages.properties";
    static final String MESS_EO = "src/main/resources/messages_eo.properties";

    static Properties messages = new Properties();

    static {
        FileInputStream fileInputStream;

        try {
            if (Locale.getDefault().getLanguage().equals("eo")) fileInputStream = new FileInputStream(MESS_EO);
            else fileInputStream = new FileInputStream(MESS_EN);
            messages.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String getProp(String property) {
        String str = messages.getProperty(property);

/*
//        If you have troubles with encoding
        str = str.replace("Å\u009D", "ŝ");
        str = str.replace("Ä\u0089", "ĉ");
        str = str.replace("Ä\u009D", "ĝ");
        str = str.replace("Ä\u0088", "ĉ");
        str = str.replace("Ä\u009C", "Ĝ");
        str = str.replace("Å\u00AD", "ŭ");
*/

        return str;
    }
}
