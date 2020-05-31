package cat.udl.tidic.amd.dam_tips;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_tips.views.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainClassViewModel extends MainPage implements LifecycleOwner {
    //private UserModel user = new UserModel();
    public UserServices userService;
    private MutableLiveData<List<DataModel.Favour>> allFavours = new MutableLiveData<>();
    public LiveData<List<DataModel.Favour>> getAllFavours(){ return allFavours; }
    private Context c;
    public List<DataModel.Favour> listOfFavours;
    public DataModel.Favour[] favours;
    MainPage mainPage;
    DataModel.Favour[] eventList;
    public DataModel.Favour favour;
    public MutableLiveData<UserModel> userModelMutableLiveData = new MutableLiveData<>();
    private int ratingProgress;




    public MainClassViewModel(Context c, MainPage m)
    {

        this.c = c;

        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
        mainPage = m;
        SharedPreferences mPreferences = PreferencesProvider.providePreferences();
        String token = mPreferences.getString("all_favours", "");
        Log.d("Token:", token);
        getFavours(0);
    }





    public void getFavours(int listnumber)
    {

        //TODO
        //Aqui es fa la crida depenent del listnumber
        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        String token = PreferencesProvider.providePreferences().getString("token","");
        Log.d("Token-------", token);
        Call<List<DataModel.Favour>> call = userService.getFavours(null);
        //Call<DataModel.Favour> call = userService.getAllFavours(null);
        listOfFavours = null;
        //LoadingPanel.enableLoading(c,true);
        //noinspection NullableProblems
        Log.d("Before enqueue", "------------");
        call.enqueue(new Callback<List<DataModel.Favour>>()
        {

            @Override
            public void onResponse(Call<List<DataModel.Favour>> call, Response<List<DataModel.Favour>> response)
            {
                Log.d("Enqueue----------","Dins");
                try
                {
                    Log.d("MainClassViewModel",""+response.code());
                   List<DataModel.Favour> response_ = response.body();

                    assert response_ != null;
                    for (int i = 0; i < response_.size(); i++)
                    {
                        response_.get(i).setIcon();
                        //Log.d("QUEST>IONS---------------", response_.get(0).name);

                    }

                    favours = (DataModel.Favour[]) response_.toArray(new DataModel.Favour[response_.size()]);
                    allFavours.setValue(response_);
                    //LoadingPanel.enableLoading(c,false);

                }
                catch (Exception e) { Log.d("Salta el catch -------", e.getMessage() + "ERROR");}
            }

            @Override
            public void onFailure(Call<List<DataModel.Favour>> call, Throwable t)
            {

                Log.e("onFailure ------------", Objects.requireNonNull(t.getMessage()));
            }
        });
    }



    private void onGetFavoursData(List<DataModel.Favour> all_f) {
        eventList = all_f.toArray(new DataModel.Favour[0]);
    }


    public void postRate(DataModel.Favour currentFavourData)
    {
        String token = PreferencesProvider.providePreferences().getString("token","");
        Log.d("RATINGS token ------", token);
        JsonObject user_json = new JsonObject();
        user_json.addProperty("rating", currentFavourData.rating);
        Log.d("RATING-------",""+currentFavourData.rating);
        Call<Void> call = userService.postRate(currentFavourData.id,user_json);
        //noinspection NullableProblems
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    Log.d("onResponse", "Ratings");
                    Context c = App.getAppContext();
                    Toast.makeText(c , "Moltes gràcies per valorar.", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    //Atrapar error usuari existent / correu existent
                    assert response.errorBody() != null;
                    Log.d("Update ratings else", ""+response.code());

                }


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                //sendMessage("Error en pujar el Favor");
                Log.d("Upadate ratings",""+t.getLocalizedMessage());
            }
        });
    }




}
