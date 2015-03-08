package lordtony.ejemplo2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

//Talvez deba poner FrgmentActivity
public class MainActivity extends ActionBarActivity implements SendDataDialogFragment.DialogListener {
    private boolean favorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton toggle_recommendation = (ToggleButton)findViewById(R.id.toggle_button);
        toggle_recommendation.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_fav) {
            Drawable icon = null;
            //Si es favorito y se hace click
            if(favorite){
                icon = getResources().getDrawable(R.drawable.ic_action_not_important);
                Log.e("Estatus90:", "Entro al if");
            }else{
                //Si no es favorito y se hace click
                icon = getResources().getDrawable(R.drawable.ic_action_important);
            }
            favorite = !favorite; //esto se podria meter en el if
            item.setIcon(icon);//Insertamos el icono
            return true;
        }else{
            if(id == R.id.action_share){
                Intent share = new Intent();
                Resources resources = getResources();
                Uri img_res = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + R.drawable.album1); //obtenemos el Archivo
                Log.e("La ruta90:", String.valueOf(img_res));
                String msg = getResources().getString(R.string.msg_share); //Obtenemos el mensaje

                share.setAction(Intent.ACTION_SEND);//Estamos usando la accion compartir
                share.putExtra(Intent.EXTRA_SUBJECT, "Album1");
                share.putExtra(Intent.EXTRA_TEXT, msg); //Le agregamos más datos
                share.putExtra(Intent.EXTRA_STREAM, img_res); //Agremamos la imagen
                share.setType("image/*"); //Asignamos el tipo de lo que vamos a compartir

                startActivity(Intent.createChooser(share, "Plis comparte"));

                return true;
            }else{
                if(id == R.id.action_dialog){
                    SendDataDialogFragment f = new SendDataDialogFragment();
                    f.show(getFragmentManager(), "Dialogo");
                    return true;
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleClicked(View view) {
        Toast.makeText(getApplicationContext(), "Toggle", Toast.LENGTH_SHORT).show();
        Log.e("TAG90:", "Toggle");
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        Log.e("TAG90:", "Positivo");
        Toast.makeText(getApplicationContext(), "Click " + getResources().getString(R.string.msg_yes), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

        Log.e("TAG90:", "Negativo");
        Toast.makeText(getApplicationContext(), "Click " + getResources().getString(R.string.msg_no), Toast.LENGTH_SHORT).show();
    }
}

class SendDataDialogFragment extends DialogFragment{
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
                .setSingleChoiceItems(R.array.dialog_options, 0, null)
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
