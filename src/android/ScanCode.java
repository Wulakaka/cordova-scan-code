package io.cordova.hellocordova;
import android.widget.Toast;
// import android.R;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;



/**
 * ToastDemo
 *
 * @author lmr
 * @date 2018-08-19
 */
public class ScanCode extends CordovaPlugin {


    private ValueBroadcastReceiver valueBroadcastReceiver;

    //public static CallbackContext t;

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        // startBarcodeBroadcastReceiver();
        if ("getCode".equals(action)){
            // 获取activity和context --> cordova.getActivity()和cordova.getContext()
            //Toast.makeText(cordova.getContext(),args.getString(0),Toast.LENGTH_SHORT).show();
            //t = callbackContext;
            startBarcodeBroadcastReceiver(callbackContext);
            return true;
        }
	if("stopCode".equals(action)){
	stopBarcodeBroadcastReceiver();
	return true;
	}
        return false;
    }
    
    // IntentFilter intentFilter = new IntentFilter();
    // intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
    // this.receiver = new BroadcastReceiver() {
    //     @Override
    //     public void onReceive(Context context, Intent intent) {
    //         updateBatteryInfo(intent);
    //     }
    // };
    // webView.getContext().registerReceiver(this.receiver, intentFilter);

    private class ValueBroadcastReceiver extends BroadcastReceiver{

        private CallbackContext x;

        @Override
        public void onReceive(Context context, Intent intent){
            String value = intent.getStringExtra("lachesis_barcode_value_notice_broadcast_data_string");
            //TextView resultView = (TextView)findViewById(R.id.resultView);
            //resultView.setText(value);
            //context.success(value);
            //CallbackContext x = t;
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK,value);
            pluginResult.setKeepCallback(true);
            x.sendPluginResult(pluginResult);
            //x.success(value);
            //Toast.makeText(cordova.getContext(),value,Toast.LENGTH_SHORT).show();
            // return value;
        }
    }

    private void startBarcodeBroadcastReceiver(CallbackContext context) {
         //context.success("abc");
        if (valueBroadcastReceiver == null) {
            valueBroadcastReceiver = new ValueBroadcastReceiver();
            valueBroadcastReceiver.x = context;
        }
        webView.getContext().registerReceiver(valueBroadcastReceiver, new IntentFilter("lachesis_barcode_value_notice_broadcast"));
    }

    // /**
    //  * 停止监听
    //  */
    private void stopBarcodeBroadcastReceiver() {
        if (valueBroadcastReceiver != null) {
            webView.getContext().unregisterReceiver(valueBroadcastReceiver);
            valueBroadcastReceiver = null;
        }
    }



}