package com.octys.rzd;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import com.octys.rzd.R;

public final class PreferencesActivity extends PreferenceActivity
    implements OnSharedPreferenceChangeListener {

  public static final String KEY_PLAY_BEEP = "preferences_play_beep";
  public static final String KEY_VIBRATE = "preferences_vibrate";
  public static final String KEY_FRONT_LIGHT = "preferences_front_light";

  public static final String KEY_HELP_VERSION_SHOWN = "preferences_help_version_shown";

  @Override
  protected void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    addPreferencesFromResource(R.xml.preferences);

    PreferenceScreen preferences = getPreferenceScreen();
    preferences.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }

  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
  }


}
