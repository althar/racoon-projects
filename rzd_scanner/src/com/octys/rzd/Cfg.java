package com.octys.rzd;

import com.google.zxing.BarcodeFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class Cfg {
//	private static final String TAG = TicketScannerService.class.getSimpleName();
	
//	public String	CFG_PRODUCTION_SERVER_ADDRESS = "http://zdloto.ru/online/";
	public String	CFG_PRODUCTION_SERVER_ADDRESS = "";
//	public String	CFG_PRODUCTION_SERVER_ADDRESS = "http://10.10.2.31:8080/examples/servlets/servlet/RequestParamExample";
	public String	CFG_PRODUCTION_SERVER_USER = ""; // "user"
	public String	CFG_PRODUCTION_SERVER_PASSWORD = ""; // "iyyXt82tn";
	public int		CFG_PRODUCTION_CONNECTION_TIMEOUT = 10 *1000; // ms

	public String	CFG_SETTINGS_DATE = "20120130";
	public int		CFG_SETTINGS_DB_LOOKUP_INTERVAL = 1; //5;
	public String	CFG_SETTINGS_MSG = "";
	public int		CFG_SETTINGS_SESSION_TIMEOUT = 2; // 15 minutes
	public int		CFG_CONF_REFRESH = 60*24; // minutes conf_refresh
	
	public String	CFG_SCAN_TICKET_ID = "CODE_39";
	public String	CFG_SCAN_LOTTERY_ID = "CODE_128";
	public int		CFG_SCAN_TIMEOUT = 15; // seconds
	
	public String	CFG_LAST_UPDATE_VERSION = "";
	public String	CFG_LAST_UPDATE_DATE = "20120101";
	public String	CFG_LAST_UPDATE_MD5 = "";
	public String	CFG_LAST_UPDATE_PATH = "";
	
	public String	CFG_EXIT_CODE = "375812";
	public Pattern	CFG_LOTTERY_REGEXP = Pattern.compile("^270[0-9]{11}$");

	
	public Cfg() {}
	
	public boolean isBarcodeFormat(String s) {
		if (s==null || s.length()==0) return false;
		for (BarcodeFormat bf: BarcodeFormat.values()) {
			if (s.equalsIgnoreCase(bf.toString())) return true;
		}
		return false;
	}
	
	public String optBarcodeFormat(String newFormat, String defFormat) {
		return isBarcodeFormat(newFormat)?newFormat : defFormat;
	}
	
	public Cfg(JSONObject j) throws JSONException {
		JSONObject production_server = j.optJSONObject("production_server");
		if (production_server!=null) {
			CFG_PRODUCTION_SERVER_ADDRESS = production_server.optString("address", CFG_PRODUCTION_SERVER_ADDRESS);
			CFG_PRODUCTION_SERVER_USER = production_server.optString("user", CFG_PRODUCTION_SERVER_USER);
			CFG_PRODUCTION_SERVER_PASSWORD = production_server.optString("password", CFG_PRODUCTION_SERVER_PASSWORD);
		}
		
		JSONObject settings = j.optJSONObject("settings");
		if (settings!=null) {
			CFG_SETTINGS_DATE = settings.optString("date", CFG_SETTINGS_DATE);
			CFG_SETTINGS_DB_LOOKUP_INTERVAL = settings.optInt("db_lookup_interval", CFG_SETTINGS_DB_LOOKUP_INTERVAL);
			CFG_SETTINGS_MSG = settings.optString("msg", null);
			CFG_SETTINGS_SESSION_TIMEOUT = settings.optInt("session_timeout", 15);
			CFG_CONF_REFRESH = settings.optInt("conf_refresh", 60*24);
			CFG_EXIT_CODE = settings.optString("exit_code","375812");
			String regexp = settings.optString("lottery_regexp", null);
			if (regexp==null) CFG_LOTTERY_REGEXP=null;
			else try {
				CFG_LOTTERY_REGEXP=Pattern.compile(regexp);
			} catch (Exception e) { CFG_LOTTERY_REGEXP=null; }
		}
		
		JSONObject scan = j.optJSONObject("scan");
		if (scan!=null) {
			CFG_SCAN_TICKET_ID  = optBarcodeFormat(scan.optString("ticket_id"), "CODE_39");
			CFG_SCAN_LOTTERY_ID = optBarcodeFormat(scan.optString("lottery_id"),"CODE_128");
			CFG_SCAN_TIMEOUT = scan.optInt("timeout", 15);
		}

		JSONObject last_update = j.optJSONObject("last_update");
		if (last_update!=null) {
			CFG_LAST_UPDATE_VERSION = last_update.optString("version", null);
			CFG_LAST_UPDATE_DATE = last_update.optString("date", CFG_LAST_UPDATE_DATE);
			CFG_LAST_UPDATE_MD5 = last_update.optString("md5", CFG_LAST_UPDATE_MD5);
			CFG_LAST_UPDATE_PATH = last_update.optString("path", CFG_LAST_UPDATE_PATH);
		}
	}
	
	public Cfg(String s) throws JSONException {
		this(new JSONObject(s));
	}
	
}
