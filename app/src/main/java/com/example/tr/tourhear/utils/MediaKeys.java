package com.example.tr.tourhear.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;

import com.example.tr.tourhear.Login;
import com.example.tr.tourhear.entity.MsgCode;


public class MediaKeys extends BroadcastReceiver {
	public static final String TAG = "Media.buttons";

	public MediaKeys() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.d(TAG, "MediaKeys Receiver get action: " + action);

		if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
			KeyEvent event = (KeyEvent) intent
					.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
			if (event != null) {
				int keyCode = event.getKeyCode();
				int keyAction = event.getAction();
				Log.d(TAG, "key code: " + keyCode + " action: " + keyAction);

				Handler uiHandler = Login.getUiHandler();
				if (uiHandler != null) {
					uiHandler.obtainMessage(MsgCode.MC_LINECONTROLBUTTON,
							keyAction, keyCode).sendToTarget();
				} else {
					Log.d(TAG, "media key received, but uiHandler is null");
				}
			}
		}
	}
}
