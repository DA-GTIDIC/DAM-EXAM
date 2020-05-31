package cat.udl.tidic.amd.dam_tips.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.io.File;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Account;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {

    private TextView nombre;
    private TextView apellido;
    private TextView correo;
    private TextView telefono;
    private TextView genero;
    private TextView puntuacion;
    private TextView username;
    private AccountDAO accountDAO;
    private SharedPreferences mPreferences;
    private String token;
    private String TAG;
    private ImageView imagen_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);
        Intent intent = getIntent();
        readPermission();
        nombre = findViewById(R.id.text_nombre);
        apellido = findViewById(R.id.text_apellido);
        correo = findViewById(R.id.text_correo);
        telefono = findViewById(R.id.text_telefono);
        genero = findViewById(R.id.text_genero);
        puntuacion = findViewById(R.id.text_puntuacion);
        username = findViewById(R.id.text_username);
        imagen_perfil = findViewById(R.id.imagen_perfil);

        imagen_perfil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDialog();
            }
        });
        readPermission();

        accountDAO = RetrofitClientInstance.
                getRetrofitInstance().create(AccountDAO.class);

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");
        System.out.print(this.accountDAO);
        Call<Account> call_get = this.accountDAO.getUserProfile();
        call_get.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {

                if (response.code() == 200) {
                    Account u = response.body();
                    nombre.setText(u.getName());
                    apellido.setText(u.getSurname());
                    username.setText(u.getUsername());
                    correo.setText(u.getEmail());
                    telefono.setText(u.getPhone());
                    puntuacion.setText(u.getPoints());

                    Uri path = intent.getData();
                    Picasso.get().load(path).into(imagen_perfil); // No em funciona del tot, quan
                    // l'actualitzes la primera vegada si que es veu pero si surts i tornes a
                    // entrar al perfil desapareix, tot i que s'ha guardat correctament a
                    // la base de dades.

                    if(u.getGenere().equals("M")){
                        genero.setText("Hombre");
                    } else if (u.getGenere().equals("F")){
                        genero.setText("Mujer");
                    } else {
                        genero.setText("Desconocido");
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });

    }

    public void openDialog() {
        DialogImage dialogImage = new DialogImage().newInstance(this);
        dialogImage.show(getSupportFragmentManager(),"Profile Photo Dialog");
    }

    private boolean readPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(getApplicationContext(),"I need access to images", Toast.LENGTH_LONG);
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    21);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Dialog result: " + resultCode);

        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            Picasso.get().load(path).into(imagen_perfil);

            File file1 = new File(getRealPathFromURI(path, this));
            RequestBody requestFile2 = RequestBody.create(MediaType.parse(getContentResolver().getType(data.getData())), file1);
            MultipartBody.Part body2 = MultipartBody.Part.createFormData("image_file", file1.getName(), requestFile2);

            Call<ResponseBody> call_update = this.accountDAO.uploadImage(body2);
            call_update.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d(TAG, "Success: " + response.code());
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d(TAG, "Error: " + t.getMessage());
                }
            });

        }


    }

    public String getRealPathFromURI(Uri uri, Activity activity) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }
    public void cargarImagen() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent, 10);
    }

}
