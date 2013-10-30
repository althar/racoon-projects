package com.octys.rzd;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import com.octys.rzd.Consts;
import com.octys.rzd.R;
import com.octys.rzd.TicketScannerApplication;

import java.io.IOException;

/**
 * Manages beeps and vibrations for {@link com.octys.rzd.ScanActivity}.
 */
public final class BeepManager {

  private static final String TAG = BeepManager.class.getName();

//  private final Activity activity;
  private TicketScannerApplication mApp;
  private MediaPlayer mediaPlayer;
  private boolean playBeep;
  private boolean vibrate;

  BeepManager(TicketScannerApplication app) {
    this.mApp = app;
    this.mediaPlayer = null;
    updatePrefs();
  }

  void updatePrefs() {
    playBeep = Consts.PLAY_BEEP;
    vibrate = Consts.VIBRATE;
    if (playBeep && mediaPlayer == null) {
      // The volume on STREAM_SYSTEM is not adjustable, and users found it too loud,
      // so we now play on the music stream.
//      AudioManager am = (AudioManager)mApp.getSystemService(Context.AUDIO_SERVICE);
      
 //     activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
      mediaPlayer = buildMediaPlayer(mApp);
    }
  }

  void playBeepSoundAndVibrate() {
    if (playBeep && mediaPlayer != null) {
      mediaPlayer.start();
    }
    if (vibrate) {
      Vibrator vibrator = (Vibrator) mApp.getSystemService(Context.VIBRATOR_SERVICE);
      vibrator.vibrate(Consts.VIBRATE_DURATION);
    }
  }

  
  void Vibrate() {
	    if (vibrate) {
	      Vibrator vibrator = (Vibrator) mApp.getSystemService(Context.VIBRATOR_SERVICE);
	      vibrator.vibrate(Consts.VIBRATE_DURATION_ERR);
	    }
	  }
  
  void playBeepSoundAndVibrate_err() {
	    if (playBeep && mediaPlayer != null) {
	      mediaPlayer.start();
	    }
	    if (vibrate) {
	      Vibrator vibrator = (Vibrator) mApp.getSystemService(Context.VIBRATOR_SERVICE);
	      vibrator.vibrate(Consts.VIBRATE_DURATION_ERR);
	    }
	  }

  private static MediaPlayer buildMediaPlayer(Context activity) {
    MediaPlayer mediaPlayer = new MediaPlayer();
    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    // When the beep has finished playing, rewind to queue up another one.
    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
      public void onCompletion(MediaPlayer player) {
        player.seekTo(0);
      }
    });

    AssetFileDescriptor file = activity.getResources().openRawResourceFd(R.raw.beep);
    try {
      mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
      file.close();
      mediaPlayer.setVolume(Consts.BEEP_VOLUME, Consts.BEEP_VOLUME);
      mediaPlayer.prepare();
    } catch (IOException ioe) {
      Log.w(TAG, ioe);
      mediaPlayer = null;
    }
    return mediaPlayer;
  }

}
