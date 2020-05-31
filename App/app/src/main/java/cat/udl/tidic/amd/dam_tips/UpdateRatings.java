package cat.udl.tidic.amd.dam_tips;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateRatings {

    private UserServices userService;
    private  Context c;

    public UpdateRatings(Context c){
        this.c = c;
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
    }

    public void postFavour(DataModel.Favour currentFavourData, DataModel.Favour upFav)
    {
        String token = PreferencesProvider.providePreferences().getString("token","");
        JsonObject user_json = new JsonObject();
        user_json.addProperty("rating", currentFavourData.rating);
        Call<Void> call = userService.postRate(token,currentFavourData.id,user_json);
        //noinspection NullableProblems
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {


                }
                else
                {
                    //Atrapar error usuari existent / correu existent
                    assert response.errorBody() != null;

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
