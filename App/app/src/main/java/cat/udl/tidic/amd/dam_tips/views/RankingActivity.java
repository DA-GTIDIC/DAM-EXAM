package cat.udl.tidic.amd.dam_tips.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Account;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView lista;
    private AccountDAO accountDao;
    private UserAdapter userAdapter;
    private String TAG= "RANKING";
    public static final int EDIT_EVENT = 2;
    ArrayList<Account> players_data = new ArrayList<>();
    private SharedPreferences mPreferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Intent intent = getIntent();

        lista = findViewById(R.id.RecyclerView);
        lista.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(new cat.udl.tidic.amd.dam_tips.views.UserDiffCallback());
        lista.setAdapter(userAdapter);

        userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Account user) {
                Log.d(TAG, user.getUsername());
                Intent intent = new Intent(RankingActivity.this, Profile.class);
                intent.putExtra(Profile.EXTRA_USERNAME, user.getUsername());
                startActivityForResult(intent, EDIT_EVENT);
            }
        });

        accountDao = RetrofitClientInstance.
                getRetrofitInstance().create(AccountDAO.class);

        this.mPreferences = PreferencesProvider.providePreferences();
        token = this.mPreferences.getString("token", "");

        populateList();

    }


    public void populateList(){

        Call<List<Account>> call_get_players = accountDao.getUsers();
        call_get_players.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                Log.d(TAG,"code:"+response.code());
                if (response.code() == 200){

                    players_data = (ArrayList<Account>) response.body();
                    userAdapter.submitList(players_data);
                } else{
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item= menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (newText.equals("") ){
                    userAdapter.submitList(players_data);
                    Log.d(TAG, "|Players| = " + players_data.size());
                }
                else {
                    ArrayList<Account> filteredModelList = (ArrayList<Account>) players_data.stream()
                            .filter(player -> player.getName().contains(newText))
                            .collect(Collectors.toList());

                    Log.d(TAG, "|Players| = " + filteredModelList.size());

                    userAdapter.submitList(filteredModelList);
                }
                lista.scrollToPosition(0);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
