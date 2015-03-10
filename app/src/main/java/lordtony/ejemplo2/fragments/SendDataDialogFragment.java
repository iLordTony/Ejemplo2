package lordtony.ejemplo2.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import lordtony.ejemplo2.R;

/**
 * Created by USER on 07/03/2015.
 */
public class SendDataDialogFragment extends DialogFragment {
    public interface DialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    DialogListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            listener = (DialogListener)activity;
        }catch (ClassCastException e){

        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Mi titulo")
                .setSingleChoiceItems(R.array.dialog_options, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Click " + which, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton(R.string.msg_yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogPositiveClick(SendDataDialogFragment.this);
                    }
                }).setNegativeButton(R.string.msg_no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onDialogNegativeClick(SendDataDialogFragment.this);
            }
        });


        return builder.create();
    }
}