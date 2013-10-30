package com.octys.rzd;

//  	 		android:configChanges="keyboardHidden|orientation"  
//             android:screenOrientation="portrait"

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class TicketScannerActivity extends Activity {
	private static final String TAG = TicketScannerActivity.class.getName();

	TicketScannerApplication mApp;

	private static final String INSTANCE_STATE_MULTISCAN = "MULTISCAN";
	private static final String INSTANCE_STATE_SLIP_NUMBER = "RR";
	private static final String INSTANCE_STATE_LT_STICKER_NUMBER = "LT";
	private static final String INSTANCE_STATE_LT_STICKER_SET = "LT_SET";
	private static final String INSTANCE_STATE_DIALOG = "DIALOG";
	private static final String INSTANCE_STATE_ALERT = "ALERT";

	private int dialogExposed = 0;
	private int alertExposed = 0;
	private boolean multiscan_lock = false;
	private String rr_slip_number = null;
	private String lt_sticker_number = null;
	private ArrayList<String> lt_sticker_set = new ArrayList<String>();

	private static final int DIALOG_NO_SDCARD = 1;
	private static final int DIALOG_SDCARD_FULL = 2;
	private static final int DIALOG_UNSUPPORTED = 3;
	private static final int DIALOG_GET_PERSONAL_ID = 4;
	private static final int DIALOG_MAINTENANCE = 5;
	private static final int DIALOG_HOWTO = 6;
	private static final int DIALOG_RR_SCAN_FAILURE = 7;
	private static final int DIALOG_RR_MANUAL = 8;
	private static final int DIALOG_RR_SCAN_SUCCESS = 9;
	private static final int DIALOG_LT_SCAN_FAILURE = 10;
	private static final int DIALOG_LT_MANUAL = 11;
	private static final int DIALOG_LT_SCAN_SUCCESS = 12;
	private static final int DIALOG_MULTI_SCAN_FAILURE = 13;
	private static final int DIALOG_MULTI_MANUAL = 14;
	private static final int DIALOG_MULTI_SUCCESS = 15;
	private static final int DIALOG_MULTI_FINAL = 16;
//	private static final int DIALOG_USB_DEBUG = 17;
	private static final int DIALOG_NON_MARKET = 18;
	private static final int DIALOG_ALMOST_FULL = 19;

	private static final int STATE_SCAN_RR_SLIP = 1;
	private static final int STATE_SCAN_LT_STICKER = 2;
	private static final int STATE_SCAN_MULTI = 3;
	private int state = STATE_SCAN_RR_SLIP;
	
//	private boolean auto_relaunch = true;

	@Override
	protected void onDestroy() {
		Log.w(TAG, "rzd_onDestroy " + this);
//		Debug.stopMethodTracing();
		mApp.setActivity(null);
		unregisterReceiver(mMediaStateReceiver);
		unregisterReceiver(mScreenOnReceiver);
//		if (auto_relaunch) mApp.relaunch(); 
		super.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle state) {
		super.onSaveInstanceState(state);
//		Log.w(TAG, "onSaveInstanceState " + this);
		state.putInt(INSTANCE_STATE_DIALOG, dialogExposed);
		state.putInt(INSTANCE_STATE_ALERT, alertExposed);
		state.putBoolean(INSTANCE_STATE_MULTISCAN, multiscan_lock);
		state.putString(INSTANCE_STATE_SLIP_NUMBER, rr_slip_number);
		state.putString(INSTANCE_STATE_LT_STICKER_NUMBER, lt_sticker_number);
		state.putStringArrayList(INSTANCE_STATE_LT_STICKER_SET, lt_sticker_set);
	}

	void RestoreInstanceState(Bundle state) {
		if (state != null) {
			dialogExposed = state.getInt(INSTANCE_STATE_DIALOG, 0);
			alertExposed = state.getInt(INSTANCE_STATE_ALERT, 0);
			multiscan_lock = state.getBoolean(INSTANCE_STATE_MULTISCAN, false);
			rr_slip_number = state.getString(INSTANCE_STATE_SLIP_NUMBER); // default
																			// null
			lt_sticker_number = state.getString(INSTANCE_STATE_LT_STICKER_NUMBER); // default
																					// null
			ArrayList<String> list = state.getStringArrayList(INSTANCE_STATE_LT_STICKER_SET);
			if (list != null)
				lt_sticker_set = list;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		Log.w(TAG, "rzd_onCreate " + this);

//		Debug.startMethodTracing("NumericDialog");

		if (Consts.CAR_DOCK) {
			UiModeManager ui_mm = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
			ui_mm.enableCarMode(0);
		}

		mApp = (TicketScannerApplication) getApplication();
		mApp.setActivity(this);
		RestoreInstanceState(savedInstanceState);
		startService(new Intent(TicketScannerActivity.this, TicketScannerService.class));

		setTitle(getString(R.string.app_name) + " v" + mApp.strVersionName);

		setContentView(R.layout.greeting);
		findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mApp.manageUpdates())
					return;
				if (!mApp.is_session_expired()) {
					state = STATE_SCAN_RR_SLIP;
					scan_rr();
				} else {
					mApp.terminate_session();
					if (Consts.isDeviceSupported())
						dialog(DIALOG_GET_PERSONAL_ID);
					else
						dialog(DIALOG_UNSUPPORTED);
				}

			}
		});

		IntentFilter filter;
		filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		registerReceiver(mScreenOnReceiver, filter);

		filter = new IntentFilter();

		filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		filter.addDataScheme("file");
		registerReceiver(mMediaStateReceiver, filter);

		new Handler().postDelayed(new Runnable() {
			public void run() {
				showWarning();
			}
		}, 100);
	}

	public void showWarning() {
		close_alert();
		if (!mApp.isNonMarketAppsAllowed())
			alert(DIALOG_NON_MARKET);
//		else if (!mApp.isDebuggingEnabled())
//			alert(DIALOG_USB_DEBUG);
		else if (!SDCardLogger.isSDPresent())
			alert(DIALOG_NO_SDCARD);
		else if (SDCardLogger.availableSize() < Consts.SDCARD_FREE_MIN)
			alert(DIALOG_SDCARD_FULL);
		else if (SDCardLogger.availableSize() < Consts.SDCARD_WARNING)
			alert(DIALOG_ALMOST_FULL);
	}

	public void manageSessionTimeout() {
		if (mApp.getPersonalIdString() != null && mApp.is_session_expired()) {
			mApp.terminate_session();
			close_dialog();
		}
	}

	BroadcastReceiver mScreenOnReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
//				Log.w(TAG, "screen on");
				manageSessionTimeout();
			}
		}
	};

	BroadcastReceiver mMediaStateReceiver = new BroadcastReceiver() {
		public void onReceive(Context ctxt, Intent intent) {
			String action = intent.getAction();
//			Log.w(TAG, "Media state changed " + action);
			if (Intent.ACTION_MEDIA_UNMOUNTED.equals(action) || Intent.ACTION_MEDIA_MOUNTED.equals(action)) {
				showWarning();
			}
		}
	};

	public void log(String message, Object... parameters) {
		mApp.sdcard_log(message, parameters);
	}

	protected void dialog(int id) {
//		Log.w(TAG, "dialog(" + id + "): alertExposed=" + alertExposed + "; dialogExposed=" + dialogExposed);
		close_dialog();
		dialogExposed = id;
		showDialog(id);
	}

	protected void alert(int id) {
//		Log.w(TAG, "alert(" + id + "): alertExposed=" + alertExposed + "; dialogExposed=" + dialogExposed);
		close_alert();
		alertExposed = id;
		showDialog(id);
	}

	void close_dialog() {
		if (dialogExposed > 0) {
			try {
				dismissDialog(dialogExposed);
			} catch (Throwable t) {
			}
			dialogExposed = 0;
		}
	}

	void close_alert() {
		if (alertExposed > 0) {
			try {
				dismissDialog(alertExposed);
			} catch (Throwable t) {
			}
			alertExposed = 0;
		}
	}

	/*
	 * @Override protected void onStart() { super.onStart(); Log.w(TAG,
	 * "rzd_onStart " + this); }
	 *
	 * @Override protected void onStop() { super.onStop(); Log.w(TAG,
	 * "rzd_onStop " + this); }
	 *
	 * @Override public void onConfigurationChanged(Configuration newConfig) {
	 * super.onConfigurationChanged(newConfig); Log.w(TAG,
	 * "onConfigurationChanged"); }
	 */
	@Override
	protected void onPause() {
		//w(TAG, "rzd_onPause " + this);
	//	Log.w(TAG, "alertExposed=" + alertExposed + "; dialogExposed=" + dialogExposed);

		super.onPause();
	}

	// onResume();
	@Override
	protected void onResume() {
		super.onResume();
//		Log.w(TAG, "rzd_onResume " + this);
//		Log.w(TAG, "alertExposed=" + alertExposed + "; dialogExposed=" + dialogExposed);
		manageSessionTimeout();
		showWarning();
	}

	public void maintenance() {
		dialog(DIALOG_MAINTENANCE);
	}

	private void new_rr(String rr) {
		log("SR {0}", rr);
		rr_slip_number = rr;
		dialog(DIALOG_RR_SCAN_SUCCESS);
	}

	private void new_lt(String lt) {
		log("SL {0}", lt);
		mApp.putData(rr_slip_number, lt, true);
		lt_sticker_number = lt;
		dialog(DIALOG_LT_SCAN_SUCCESS);
	}

	private void multi_lt(String lt) {
		if (lt_sticker_set.contains(lt)) {
			mApp.toast(R.string.msg_duplicate_lt);
		} else if (lt.equals(rr_slip_number)) {
			mApp.toast(R.string.msg_same_numbers);
			mApp.mBeepManager.Vibrate();
			if (lt_sticker_set.isEmpty()) {
				state = STATE_SCAN_RR_SLIP;
				scan_rr();
				return;
			}
		} else {
			log("SM {0}", lt);
			mApp.putData(rr_slip_number, lt, lt_sticker_set.isEmpty());
			lt_sticker_set.add(lt);
			lt_sticker_number = lt;
		}
		dialog(DIALOG_MULTI_SUCCESS);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case DIALOG_NO_SDCARD:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.sdcard);
				}
			};
			break;
		case DIALOG_SDCARD_FULL:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.sdcard);
					((TextView) findViewById(R.id.text_sdcard)).setText(R.string.msg_sdcard_full);
				}
			};
			break;
		case DIALOG_UNSUPPORTED:
			dialog = new DialogLZD(this) {
				@Override
				public void onBackPressed() {
					close_dialog();
					dialog(DIALOG_GET_PERSONAL_ID);
				}

				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.warning);
					findViewById(R.id.button_continue_anyway).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							dialog(DIALOG_GET_PERSONAL_ID);
						}
					});
				}
			};
			break;
		case DIALOG_NON_MARKET:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.warning);
					((TextView) findViewById(R.id.warning)).setText(R.string.msg_non_market);
					Button b = (Button) findViewById(R.id.button_continue_anyway);
					b.setText(R.string.button_settings);
					b.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							launchSettingsApp();
						}
					});
				}
			};
			break;
/*		case DIALOG_USB_DEBUG:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.warning);
					((TextView) findViewById(R.id.warning)).setText(R.string.msg_usb_debug);
					Button b = (Button) findViewById(R.id.button_continue_anyway);
					b.setText(R.string.button_settings);
					b.setOnClickListener(new android.view.View.OnClickListener() {
						;
						public void onClick(View v) {
							close_dialog();
							launchSettingsApp();
						}
					});
				}
			};
			break;
*/		case DIALOG_ALMOST_FULL:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.warning);
					((TextView) findViewById(R.id.warning)).setText(R.string.msg_sdcard_almost_full);
					findViewById(R.id.button_continue_anyway).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							dialog(DIALOG_GET_PERSONAL_ID);
						}
					});
				}
			};
			break;
		case DIALOG_GET_PERSONAL_ID:
			dialog = new NumericDialog(this, getString(R.string.msg_enter_id), Consts.PERSONAL_NUMBER_LENGTH_MIN,
					Consts.PERSONAL_NUMBER_LENGTH_MAX) {
				private static final long serialVersionUID = DIALOG_GET_PERSONAL_ID;

				@Override
				public void onBackPressed() {
					close_dialog();
					mApp.terminate_session();
				}

				@Override
				public void onComplete(String number) {
					// removeDialog(DIALOG_GET_PERSONAL_ID);
					mApp.sdcard_log("OP {0}", number);
					close_dialog();
					mApp.start_session(number);
					dialog(DIALOG_HOWTO);
					mApp.toast(mApp.mCfg.CFG_SETTINGS_MSG);
				}

			};
			break;
		case DIALOG_MAINTENANCE:
			dialog = new NumericDialog(this, getString(R.string.msg_enter_code), 0, 32) {
				private static final long serialVersionUID = DIALOG_MAINTENANCE;

				@Override
				public void onBackPressed() {
					close_dialog();
				}

				@Override
				public void onComplete(String number) {
                    close_dialog();
					if (mApp.mCfg.CFG_EXIT_CODE.equals(number)) {
						mApp.sdcard_log("EXIT {0}", number);
//						mApp.exit();
//						auto_relaunch = false;
//						finish();
						launchSettingsApp();
					} else {
						mApp.sdcard_log("WRONG {0}", number);
					}
				}
			};
			break;
		case DIALOG_HOWTO:
			dialog = new DialogLZD(this) {
				@Override
				public void onBackPressed() {
					close_dialog();
					dialog(DIALOG_GET_PERSONAL_ID);
				}

				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.howto);
					Button b = (Button) findViewById(R.id.button_scan_slip);
					b.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							state = STATE_SCAN_RR_SLIP;
							scan_rr();
						}
					});
					// b.setText(Html.fromHtml(getString(R.string.button_scan_slip)));
				}
			};
			break;
		case DIALOG_RR_SCAN_FAILURE:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.scan_failure);
					findViewById(R.id.button_scan_again).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							scan_rr();
						}
					});
					findViewById(R.id.button_manual).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							dialog(DIALOG_RR_MANUAL);
						}
					});
				}

				@Override
				public void onBackPressed() {
					close_dialog();
					dialog(DIALOG_HOWTO);
				}
			};
			break;
		case DIALOG_RR_MANUAL:
			dialog = new NumericDialog(this, getString(R.string.msg_enter_rr), Consts.RAILROAD_TICKET_NUMBER_LENGTH_MIN,
					Consts.RAILROAD_TICKET_NUMBER_LENGTH_MAX) {
				private static final long serialVersionUID = 13L;

				@Override
				public void onComplete(String number) {
					log("MR {0}", number);
					new_rr(number);
				}

				@Override
				public void onCancel(DialogInterface dialog) {
					dialog(DIALOG_RR_SCAN_FAILURE);
				}

                @Override
                public void onBackPressed() {
                    close_dialog();
                    dialog(DIALOG_RR_SCAN_FAILURE);
                }
			};
			break;
		case DIALOG_RR_SCAN_SUCCESS:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.slip_scan_success);
					findViewById(R.id.button_scan_lottery).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							CheckBox lock = (CheckBox) findViewById(R.id.button_lock);
							multiscan_lock = lock.isChecked();
							close_dialog();
							if (multiscan_lock) {
								lt_sticker_set.clear();
								state = STATE_SCAN_MULTI;
							} else
								state = STATE_SCAN_LT_STICKER;
							scan_lt();
						}
					});
				}

				@Override
				public void onBackPressed() {
					close_dialog();
					state = STATE_SCAN_RR_SLIP;
					scan_rr();
				}

			};
			break;
		case DIALOG_LT_SCAN_FAILURE:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.scan_failure);
					findViewById(R.id.button_scan_again).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							scan_lt();
						}
					});
					findViewById(R.id.button_manual).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							dialog(DIALOG_LT_MANUAL);
						}
					});
					((TextView) findViewById(R.id.text_scan_failure1)).setText(R.string.msg_scan_failure1a);
				}

				@Override
				public void onBackPressed() {
					close_dialog();
					dialog(DIALOG_RR_SCAN_SUCCESS);
				}
			};
			break;
		case DIALOG_LT_MANUAL:
			dialog = new NumericDialog(this, getString(R.string.msg_enter_lt), Consts.LOTTERY_TICKET_NUMBER_LENGTH_MIN,
					Consts.LOTTERY_TICKET_NUMBER_LENGTH_MAX, "", LotteryTicketValidator) {
				private static final long serialVersionUID = 14L;

				@Override
				public void onComplete(String number) {
					close_dialog();
					if (number.equals(rr_slip_number)) {
						mApp.toast(R.string.msg_same_numbers);
						mApp.mBeepManager.Vibrate();
						state = STATE_SCAN_RR_SLIP;
						scan_rr();
					} else {
						log("ML {0}", number);
						new_lt(number);
					}
				}

				@Override
				public void onCancel(DialogInterface dialog) {
					close_dialog();
					dialog(DIALOG_LT_SCAN_FAILURE);
				}

                @Override
                public void onBackPressed() {
                    close_dialog();
                    dialog(DIALOG_LT_SCAN_FAILURE);
                }
			};
			break;
		case DIALOG_LT_SCAN_SUCCESS:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.lottery_scan_success);
					findViewById(R.id.button_continue_scan).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							state = STATE_SCAN_RR_SLIP;
							scan_rr();
						}
					});
					findViewById(R.id.button_done).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							mApp.terminate_session();
						}
					});
				}

				@Override
				public void onBackPressed() {
					close_dialog();
					dialog(DIALOG_RR_SCAN_SUCCESS);
				}
			};
			break;
		case DIALOG_MULTI_SCAN_FAILURE:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.scan_failure_multi);
					findViewById(R.id.button_scan_again).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							scan_lt();
						}
					});
					findViewById(R.id.button_manual).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							dialog(DIALOG_MULTI_MANUAL);
						}
					});
					findViewById(R.id.button_multi_stop).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							dialog(DIALOG_MULTI_FINAL);
						}
					});
				}

				@Override
				public void onBackPressed() {
				}
			};
			break;
		case DIALOG_MULTI_MANUAL:
			dialog = new NumericDialog(this, getString(R.string.msg_enter_lt), Consts.LOTTERY_TICKET_NUMBER_LENGTH_MIN,
					Consts.LOTTERY_TICKET_NUMBER_LENGTH_MAX, "", LotteryTicketValidator) {
				private static final long serialVersionUID = 15L;

				@Override
				public void onComplete(String number) {
					close_dialog();
					log("MM {0}", number);
					multi_lt(number);
				}

				@Override
				public void onCancel(DialogInterface dialog) {
					close_dialog();
					dialog(DIALOG_MULTI_SCAN_FAILURE);
				}

                @Override
                public void onBackPressed() {
                    close_dialog();
                    dialog(DIALOG_MULTI_SCAN_FAILURE);
                }

			};
			break;
		case DIALOG_MULTI_SUCCESS:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.multi_scan_success);
					findViewById(R.id.button_multi_next).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							scan_lt();
						}
					});
					findViewById(R.id.button_multi_stop).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							dialog(DIALOG_MULTI_FINAL);
						}
					});
				}

				@Override
				public void onBackPressed() {
				}
			};
			break;
		case DIALOG_MULTI_FINAL:
			dialog = new DialogLZD(this) {
				@Override
				public void onCreate(Bundle savedInstanceState) {
					super.onCreate(savedInstanceState);
					setContentView(R.layout.multi_final);
					findViewById(R.id.button_continue_scan).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							state = STATE_SCAN_RR_SLIP;
							scan_rr();
						}
					});
					findViewById(R.id.button_done).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							close_dialog();
							mApp.terminate_session();
						}
					});
				}

				@Override
				public void onBackPressed() {
				}
			};
			break;
		default:
			dialog = null;
		}
		return dialog;

	}

	private void scan(int statusTextId) { // request_code is
											// ScanActivity.ACTIVITY_LOTTERY_NUMBER_SCAN
											// or
											// ScanActivity.ACTIVITY_RAILROAD_NUMBER_SCAN
		Intent intent = new Intent(TicketScannerActivity.this, com.octys.rzd.ScanActivity.class);
		intent.putExtra(ScanActivity.STATUS_TEXT_ID, statusTextId);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivityForResult(intent, 0);
	}

	private void scan_rr() {
		scan(R.string.msg_scan_railroad_ticket);
	}

	private void scan_lt() {
		scan(R.string.msg_scan_lottery_ticket);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mApp.restart_session_timer();

		String r = "";
		String f = "";
		if (resultCode == RESULT_OK) {
			Bundle b = data.getExtras();
			r = b.getString(ScanActivity.BARCODE_DATA);
			f = b.getString(ScanActivity.BARCODE_FORMAT);
		}

		switch (state) {
		case STATE_SCAN_RR_SLIP:
			if (resultCode == RESULT_OK) {
				if (mApp.mCfg.CFG_SCAN_TICKET_ID.equalsIgnoreCase(f) && Inspector.rr_valid(r)) {
					mApp.mBeepManager.playBeepSoundAndVibrate();
					new_rr(r);
				} else {
					mApp.mBeepManager.Vibrate();
					mApp.toast(R.string.msg_mistake_rr);
					scan_rr();
				}
			} else {
				// mApp.mBeepManager.Vibrate();
				dialog(DIALOG_RR_SCAN_FAILURE);
			}
			break;
		case STATE_SCAN_LT_STICKER:
			if (resultCode == RESULT_OK) {
				if (mApp.mCfg.CFG_SCAN_LOTTERY_ID.equalsIgnoreCase(f) && Inspector.lt_valid(r, mApp.mCfg.CFG_LOTTERY_REGEXP)) {
					if (r.equals(rr_slip_number)) {
						mApp.toast(R.string.msg_same_numbers);
						mApp.mBeepManager.Vibrate();
						state = STATE_SCAN_RR_SLIP;
						scan_rr();
					} else {
						mApp.mBeepManager.playBeepSoundAndVibrate();
						new_lt(r);
					}
				} else {
					mApp.mBeepManager.Vibrate();
					mApp.toast(R.string.msg_mistake_lt);
					scan_lt();
				}
			} else {
				// mApp.mBeepManager.Vibrate();
				dialog(DIALOG_LT_SCAN_FAILURE);
			}
			break;
		case STATE_SCAN_MULTI:
			if (resultCode == RESULT_OK) {
				if (mApp.mCfg.CFG_SCAN_LOTTERY_ID.equalsIgnoreCase(f) && Inspector.lt_valid(r, mApp.mCfg.CFG_LOTTERY_REGEXP)) {
					mApp.mBeepManager.playBeepSoundAndVibrate();
					multi_lt(r);
				} else {
					// mApp.mBeepManager.Vibrate();
					mApp.toast(R.string.msg_mistake_lt);
					scan_lt();
				}
			} else {
				mApp.mBeepManager.Vibrate();
				dialog(DIALOG_MULTI_SCAN_FAILURE);
			}
			break;
		}

	}

	/*
	 * private String [] getArray(HashSet<String>hs) { String [] a = new
	 * String[hs.size()]; int idx=0; Iterator<String> i = hs.iterator(); while
	 * (i.hasNext()) a[idx++] = i.next(); return a; }
	 */
	private String getText(ArrayList<String> hs) {
		String s = "";
		int idx = 1;
		Iterator<String> i = hs.iterator();
		while (i.hasNext()) {
			s += idx + " : " + i.next();
			if (i.hasNext())
				s += '\n';
			idx++;
		}
		return s;
	}

	@Override
	protected void onPrepareDialog(final int id, final Dialog dialog) {
        DialogLZD d = (DialogLZD)dialog;
		switch (id) {
		case DIALOG_GET_PERSONAL_ID:
            ((NumericDialog)dialog).setText(mApp.getPersonalIdString());
			break;
        case DIALOG_RR_MANUAL:
        case DIALOG_LT_MANUAL :
        case DIALOG_MULTI_MANUAL:
        case DIALOG_MAINTENANCE:
            ((NumericDialog)dialog).setText("");
            break;
		case DIALOG_RR_SCAN_SUCCESS:
			((TextView) dialog.findViewById(R.id.rr_slip_number)).setText(rr_slip_number);
			((CheckBox) dialog.findViewById(R.id.button_lock)).setChecked(false);
			break;
		case DIALOG_LT_SCAN_SUCCESS:
			((TextView) dialog.findViewById(R.id.rr_slip_number)).setText(rr_slip_number);
			((TextView) dialog.findViewById(R.id.lt_sticker_number)).setText(lt_sticker_number);
			((TextView) dialog.findViewById(R.id.ticket_count)).setText("" + mApp.getTicketCount());
			break;
		case DIALOG_MULTI_SCAN_FAILURE:
			((TextView) dialog.findViewById(R.id.rr_slip_number)).setText(rr_slip_number);
			break;
		case DIALOG_MULTI_SUCCESS:
			// ((TextView)dialog.findViewById(R.id.rr_slip_number)).setText(rr_slip_number);
			((TextView) dialog.findViewById(R.id.lt_sticker_number)).setText(lt_sticker_number);
			break;
		case DIALOG_MULTI_FINAL:
			((TextView) dialog.findViewById(R.id.rr_slip_number)).setText(rr_slip_number);
			((TextView) dialog.findViewById(R.id.ticket_count)).setText("" + mApp.getTicketCount());
			/*
			 * ArrayAdapter<String>aa = new ArrayAdapter<String>(this,
			 * R.layout.list_element, getArray(lt_sticker_set));
			 * ((ListView)dialog
			 * .findViewById(R.id.lt_sticker_set)).setAdapter(aa);
			 */
			((TextView) dialog.findViewById(R.id.lt_sticker_set)).setText(getText(lt_sticker_set));
			break;
		}
        d.onPrepare();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			event.startTracking();
			return true;
		}

		if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA || keyCode == KeyEvent.KEYCODE_SEARCH) {
			// event.startTracking();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	static final NumericDialog.Validator LotteryTicketValidator = new NumericDialog.Validator() {
		// private static final long serialVersionUID = 24L;
		public boolean isValid(String s) {
			return Inspector.lt_valid(s, TicketScannerApplication.getApp().mCfg.CFG_LOTTERY_REGEXP);
		}
	};

	private void launchSettingsApp() {
		// Create an intent to launch SettingsTwo activity
		Intent launchSettingsIntent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
		startActivity(launchSettingsIntent);
		// finish();
	}

}
