package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Account;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    public static final String EXTRA_USERNAME =
            "cat.udl.tidic.amd.dam_tips.EXTRA_USERNAME";

    private TextView username;
    private AccountDAO accountDAO;
    private SharedPreferences mPreferences;
    private TextView genero;
    private String TAG ="PUBLIC";
    private TextView puntuacion;
    private ImageView photo;
    private String token;
    //private TextView nombre;
    //private TextView apellido;
    //private TextView correo;
    //private TextView telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        accountDAO = RetrofitClientInstance.
                getRetrofitInstance().create(AccountDAO.class);

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");

        this.username = findViewById(R.id.text_username);
        genero = findViewById(R.id.text_genero);
        puntuacion = findViewById(R.id.text_puntuacion);
        photo = findViewById(R.id.imageView_photo);

        //nombre = findViewById(R.id.text_nombre);
        //apellido = findViewById(R.id.text_apellido);
        //correo = findViewById(R.id.text_correo);
        //telefono = findViewById(R.id.text_telefono);

        String username;
        Intent intent = getIntent();
        this.username.setText(intent.getStringExtra(EXTRA_USERNAME));
        username = this.username.getText().toString();
        Call<Account> call_get = accountDAO.getPerfilPublico(username.trim());
        call_get.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Log.d(TAG,""+response.code());
                if(response.code()==200) {
                    Account account = response.body();
                    // Per actualitzar la foto de perfil amb la que pertany a cada usuari utilitzaria aquesta linia, pero no em funciona be.
                    // Picasso.get().load(account.getPhoto()).into(photo);
                    if(account.getGenere().equals("M")){
                        genero.setText("Hombre");
                    } else if (account.getGenere().equals("F")){
                        genero.setText("Mujer");
                    } else {
                        genero.setText("Desconocido");
                    }
                    puntuacion.setText(account.getPoints());
                    //nombre.setText(account.getName());
                    //apellido.setText(account.getSurname());
                    //correo.setText(account.getEmail());
                    //telefono.setText(account.getPhone());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });


    }
}
