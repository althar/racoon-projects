package com.octys.rzd.camera;

import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

public class LEDhack {
	private static Method m_setFlashlightEnabled = null;
	private static Object mHM;

	private static Object getInterface(String ServiceName, String StubClassName) {
		try {
			Method getService = Class.forName("android.os.ServiceManager")
					.getMethod("getService", String.class);
			IBinder bind = (IBinder) getService.invoke(null, ServiceName); // Context.WINDOW_SERVICE
			Class<?> stub = Class.forName(StubClassName); // "android.os.IWindowService$Stub"
			Method asInterface = stub.getMethod("asInterface",
					IBinder.class);
			return asInterface.invoke(null, bind);
		} catch (Exception e) {
			Log.w("LEDhack getInterface", e);
			return null;
		}
	}

	public static void init() {
		try {
			mHM = getInterface("hardware", "android.os.IHardwareService$Stub");
			if (mHM != null) {
				Class<?> c_hm = mHM.getClass();
				m_setFlashlightEnabled = c_hm.getMethod("setFlashlightEnabled",
						boolean.class);
			}

		} catch (Exception e) {
			Log.w("LEDhack init", e);
		}

	}

	public static void set(boolean state) {
		if (m_setFlashlightEnabled != null)
			try {
				Log.w("LED hack", "new state = "+state);
				m_setFlashlightEnabled.invoke(mHM, state);
			} catch (Exception e) {
				Log.w("LEDhack set", e);
			}
	}

}
