package lordtony.ejemplo2;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import lordtony.ejemplo2.fragments.SendDataDialogFragment;
import lordtony.ejemplo2.models.Album;

//Talvez deba poner FrgmentActivity
public class MainActivity extends ActionBarActivity implements SendDataDialogFragment.DialogListener {
    private static final String ALBUM_TYPE = "Tipo de Album";
    private static final String ALBUM_TITLE = "Nombre del Album";

    private boolean favorite = false;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        album = new Album(intent.getStringExtra(ALBUM_TITLE),
                intent.getStringExtra(ALBUM_TYPE));
        Log.e("Tag90:", "Hola");
        ToggleButton toggle_recommendation = (ToggleButton)findViewById(R.id.toggle_button);
        toggle_recommendation.setChecked(true);

        int resource = -1;
        if (album.getAlbumType().equals(album.WEEZER_ALBUM)) {
            resource = R.drawable.album1;
        } else {
            resource = R.drawable.album2;
        }
        Log.e("Tag90:", String.valueOf(resource));
        ImageView img_header = (ImageView)findViewById(R.id.img_header);
        img_header.setImageResource(resource);
        setTitle(album.getAlbumTitle());
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
