package com.didomi;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.ValueCallback;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.didomi.sdk.Didomi;
import io.didomi.sdk.exceptions.DidomiNotReadyException;
import io.didomi.sdk.events.EventListener;
import io.didomi.sdk.events.ConsentChangedEvent;
import org.apache.cordova.CordovaInterface;
import io.didomi.sdk.DidomiInitializeParameters;  

public class CDVDidomi extends CordovaPlugin {

    public CDVDidomi() {}

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException { 
        if (action.equals("initialize")) { 
            final String apiKey = args.getString(0); 
            final String noticeId = args.getString(1); 
            this.init(apiKey, noticeId, callbackContext);
            return true;
        }
        if (action.equals("showPreferences")) { 
            this.showPreferences();
            return true;
        }
        if (action.equals("showNotice")) { 
            this.showNotice();
            return true;
        }
        if (action.equals("isReady")) { 
          return this.isReady();
        }
        if (action.equals("shouldUserStatusBeCollected")) { 
          this.shouldUserStatusBeCollected(callbackContext); 
          return true;
        }
        if (action.equals("setUserAgreeToAll")) {
          this.setUserAgreeToAll();
          return true;
        }
        if (action.equals("setUserDisagreeToAll")) {
          this.setUserDisagreeToAll();
          return true;
        }
        return false;
    }
  
    
    public void init(String key, String noticeId, CallbackContext callbackContext){
        this.setupUI(); 
        try {
        Didomi.getInstance().onReady(() -> { 
          callbackContext.success();  
        });
        Didomi.getInstance().onError(() -> {
          callbackContext.error("Didomi was not inited"); 
        });
        Didomi.getInstance().initialize(
          cordova.getActivity().getApplication(),
            new io.didomi.sdk.DidomiInitializeParameters(
              /*apiKey*/key,
              /*localConfigurationPath*/ null,
              /*remoteConfigurationURL*/ null,
              /*providerID*/null,
              /*disableDidomiRemoteConfig*/ false,
              /*languageCode*/ "en",
              /*noticeID*/ noticeId
              /*tvNoticeId*/// null,
              /*androidTvEnabled*/// false
            )
      ); 
      } catch (Exception e) {
        Log.d("error : ","initialize error"); 
        callbackContext.error(("Didomi error")); 
      } 
    }

    public void setupUI(){
      try {
        Didomi.getInstance().setupUI(cordova.getActivity());
      } catch (Exception e) {
          Log.d("error :"," setupUI error");
      }
    } 

    public boolean isReady(){
      return Didomi.getInstance().isReady();
    }

    public void shouldUserStatusBeCollected(CallbackContext callbackContext){ 
      try { 
      if(Didomi.getInstance().shouldUserStatusBeCollected())
      callbackContext.success("true");
      else
      callbackContext.success("false");
      } catch (Exception e) { 
      callbackContext.success("false");
     }
    }

    public void setUserDisagreeToAll(){
      try { 
        Didomi.getInstance().setUserDisagreeToAll(); 
      } catch (Exception e) { 
        Log.d("error :"," setUserDisagreeToAll ");
      }
    }

    public void setUserAgreeToAll(){
      try { 
        Didomi.getInstance().setUserAgreeToAll(); 
      } catch (Exception e) { 
        Log.d("error :"," setUserAgreeToAll error");
      }
    }

    public void showPreferences(){
      try { 
        Didomi.getInstance().showPreferences(cordova.getActivity()); 
      } catch (Exception e) { 
        Log.d("error :"," showPreferences error");
      }
    }

    public void showNotice(){
      try { 
        Didomi.getInstance().showNotice(cordova.getActivity());
      } catch (Exception e) {
        Log.d("error :"," showNotice error");
      }
    } 
}
