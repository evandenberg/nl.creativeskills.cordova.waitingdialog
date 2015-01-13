package nl.creativeskills.cordova.waitingdialog;

import android.app.ProgressDialog;
import android.os.Build;
import android.view.ContextThemeWrapper;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class echoes a string called from JavaScript.
 */
public class WaitingDialog extends CordovaPlugin {
    private ProgressDialog progressDialog;
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        switch ( action )
        {
            case "show":
                String text = "Please wait";
                try {
                    text = args.getString(0);
                } catch (Exception e) {
                    LOG.d("WaitingDialog", "Text parameter not valid, using default");
                }
                showWaitingDialog(text);
                callbackContext.success();
                return true;
            case "hide":
                hideWaitingDialog();
                callbackContext.success();
                return true;
            case "canceled":
                hideWaitingDialog();
                callbackContext.success();
                return true;
            default:
                callbackContext.error("None existing method");
                return false;
        }
    }
    
    public void showWaitingDialog(String text)
    {

        if ( progressDialog != null )
        {
            progressDialog.setMessage( text );
            LOG.d("WaitingDialog", "Dialog updated message");
        } else {

            if ( android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
            {
                progressDialog = new ProgressDialog(new ContextThemeWrapper( this.cordova.getActivity() , android.R.style.Theme_Holo_Light_Dialog));
            } else {
                progressDialog = new ProgressDialog( this.cordova.getActivity() );
            }

            progressDialog.setMessage( text );
            progressDialog.show();
            LOG.d("WaitingDialog", "Dialog shown, waiting hide command");
        }
    }
    
    public void hideWaitingDialog() {
        if ( progressDialog != null ) {
            progressDialog.dismiss();
            LOG.d("WaitingDialog", "Dialog dismissed");
        } else {
            LOG.d("WaitingDialog", "Nothing to dismiss");
        }
    }
}
