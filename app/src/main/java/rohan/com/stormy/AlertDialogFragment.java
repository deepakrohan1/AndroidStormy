package rohan.com.stormy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by rohan on 3/9/16.
 */
public class AlertDialogFragment  extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.error_message)
                .setMessage(R.string.message_error)
                .setPositiveButton(R.string.positive_button_dialog, null);

        AlertDialog dialog = builder.create();
        return  dialog;



    }


}
