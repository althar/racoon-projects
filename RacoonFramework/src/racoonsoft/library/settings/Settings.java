package racoonsoft.library.settings;

import java.util.HashMap;


public class Settings {
    private static HashMap<String, String> settings_map = new HashMap<String, String>();

    public static void loadSettings(HashMap<String, String> settings) {
        settings_map = settings;
    }

    public static String getStringSetting(String name) {
        return settings_map.get(name);
    }

    public static Integer getIntegerSetting(String name) {
        try {
            return Integer.valueOf(settings_map.get(name));
        } catch (Exception ex) {
            return null;
        }
    }
    public static Long getLongSetting(String name) {
        try {
            return Long.valueOf(settings_map.get(name));
        } catch (Exception ex) {
            return null;
        }
    }

    public static Double getDoubleSetting(String name) {
        try {
            return Double.valueOf(settings_map.get(name));
        } catch (Exception ex) {
            return null;
        }
    }

    public static Boolean getBooleanSetting(String name) {
        try {
            return Boolean.valueOf(settings_map.get(name));
        } catch (Exception ex) {
            return null;
        }
    }
}
