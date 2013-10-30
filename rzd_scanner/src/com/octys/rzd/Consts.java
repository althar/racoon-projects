package com.octys.rzd;

// adb shell setprop TICKET_SCANNER_CONFIG http://tools.octys.ru/anima/json.php

import android.hardware.Camera;
import android.os.Build;

public class Consts {
	
	public enum SupportedHardware {
		ZTE_LIBRA		("ZTE", "blade"),
		ZTE_BLADE_PLUS	("ZTE", "bladeplus");
		private String mManufacturer;
		private String mDevice;
		
		SupportedHardware(String manufacturer, String device) {
			mManufacturer = manufacturer;
			mDevice = device;
		}
		public static boolean inList(String manufacturer, String device) {
			for (SupportedHardware x : SupportedHardware.values()) {
				if (x.mManufacturer.equalsIgnoreCase(manufacturer) && x.mDevice.equalsIgnoreCase(device)) return true;
			}
			return false;
		}
	}
	
	public static final boolean	CAR_DOCK = false;
	
	public static final String	DEFAULT_OPERATOR_ID = "123456";
	public static final String	SDCARD_LOG_DIR = "ticket_scanner_logs";
	public static final long	SDCARD_LOG_MAX = 1024*1024;
	public static final long	SDCARD_FREE_MIN = 1024*1024;
	public static final long	SDCARD_WARNING = 100*1024*1024;
	public static final String	CFG_CONFIG_SERVER_ADDRESS = "http://passloto.ru/system/ifaces/mobile_scan/common/json.php"; // new
//	public static final String	CFG_CONFIG_SERVER_ADDRESS = "http://passloto.ru/system/sandbox/ifaces/mobile_scan/common/json.php"; // sandbox
	public static final String	CFG_CONFIG_SERVER_LOGIN = "mobile";
	public static final String	CFG_CONFIG_SERVER_PASSWORD = "1e4sshzu7j";
	
	public static final boolean	KEEP_SCREEN_ON = false;
	public static final boolean	ALLOW_DOWNLOAD_IN_ROAMING = true;
	public static final int		ZOOM = 0;
	public static final String	FOCUS_MODE = Camera.Parameters.FOCUS_MODE_AUTO;
	public static final boolean	CONTINUOUS_AUTOFOCUS = true;
	public static final boolean	MANUAL_AUTOFOCUS = false;
    public static final long	AUTOFOCUS_INTERVAL_MS = 1500L;

    public static final boolean	VIBRATE = true;
    public static final boolean	PLAY_BEEP = true;
    public static final float	BEEP_VOLUME = 0.10f;
    public static final long	VIBRATE_DURATION = 30L; 
    public static final long	VIBRATE_DURATION_ERR = 70L; 
    
	public static final int		MIN_FRAME_WIDTH = 320;
	public static final int		MAX_FRAME_WIDTH = 800; //600
	public static final int		MAX_FRAME_HEIGHT = 200;
	public static final int		MIN_FRAME_HEIGHT = 200;

	
	public static final boolean	TRY_HARDER = false;
	public static final boolean	USE_MAX_PREVIEW_SIZE = true;
	
	public static final int		RAILROAD_TICKET_NUMBER_LENGTH_MIN = 14; 
	public static final int		RAILROAD_TICKET_NUMBER_LENGTH_MAX = 14; 
	public static final int		LOTTERY_TICKET_NUMBER_LENGTH_MIN = 14; 
	public static final int		LOTTERY_TICKET_NUMBER_LENGTH_MAX = 14; 
	public static final int		PERSONAL_NUMBER_LENGTH_MIN = 6; 
	public static final int		PERSONAL_NUMBER_LENGTH_MAX = 8; 
	
	public static final int		DATABASE_VERSION = 4;
	public static final String	DATE_FORMAT = "ddMMyyyy";
	
	public static final int		HTTP_TIMEOUT = 10 * 1000; // milliseconds
	
	public static final int		RESTART_DELAY_RECONFIG = 2000; // 2 secs
	public static final int		RESTART_DELAY_UPGRADE = 0; // 10 secs
	
	public static final boolean	KILL_ALL = true;
	
	public static final float	MINIMAL_AMBIENT_LIGHT = 1500f;

	public static boolean isDeviceSupported() {
		return SupportedHardware.inList(Build.MANUFACTURER, Build.DEVICE);
	}
}
