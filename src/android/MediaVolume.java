package com.priyansh.cordova.plugin;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class MediaVolume extends CordovaPlugin {
    private static final String TAG = "Media Volume";

    private Context context;
    private AudioManager manager;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        context = cordova.getActivity().getApplicationContext();
        manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getDeviceMediaVolume")) {
            this.getDeviceMediaVolume(callbackContext);
            return true;
        }
        return false;
    }

    private void getDeviceMediaVolume(CallbackContext callbackContext) {
        int currVol = getCurrentVolume();
        callbackContext.success((int)currVol);
        Log.d(TAG, "Media Volume: " + currVol);
    }

    private int getCurrentVolume(){
        try{
            int volLevel;
            int maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int currSystemVol = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
            volLevel = Math.round((currSystemVol * 100) / maxVolume);

            return volLevel;
        } catch (Exception e) {
            Log.e(TAG, "Get volume error: " + e.getMessage(), e);
            return 1;
        }
    }
}
