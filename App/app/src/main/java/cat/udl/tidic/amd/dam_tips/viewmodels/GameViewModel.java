package cat.udl.tidic.amd.dam_tips.viewmodels;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Queue;

import cat.udl.tidic.amd.dam_tips.App;
import cat.udl.tidic.amd.dam_tips.R;
import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
import cat.udl.tidic.amd.dam_tips.models.Answer;
import cat.udl.tidic.amd.dam_tips.models.Player;
import cat.udl.tidic.amd.dam_tips.models.Question;
import cat.udl.tidic.amd.dam_tips.network.RetrofitClientInstance;
import cat.udl.tidic.amd.dam_tips.network.ServiceInterceptor;
import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import cat.udl.tidic.amd.dam_tips.views.GameActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameViewModel
{
    private AccountDAO userService;
    private Context c;
    private GameActivity gameActivity;
    private MutableLiveData<Question> question = new MutableLiveData<>();
    public LiveData<Question> getAQ(){ return question; }
    private MutableLiveData<Boolean> validate = new MutableLiveData<>();
    public LiveData<Boolean> validateAnswer(){ return validate; }

    public GameViewModel(Context c, GameActivity ga)
    {
        this.c = c;
        gameActivity = ga;
    }


    public void getAQuestion(String filterCategory)
    {
        userService = RetrofitClientInstance.getRetrofitInstance().create(AccountDAO.class);
        String token = PreferencesProvider.providePreferences().getString("token", "");
        Call<Question> call = userService.getAQuestion(filterCategory);
        //noinspection NullableProblems
        call.enqueue(new Callback<Question>()
        {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response)
            {
                try
                {
                    question.setValue(response.body());
                } catch (Exception e)
                {
                    Log.d("GameViewModel", e.getMessage() + "ERROR ON GETTING QUESTIONS");
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t)
            {
                gameActivity.ErrorDialog();
                Log.e("GameViewModel", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void validatUserAnswer(Question q, String answer)
    {
        userService = RetrofitClientInstance.getRetrofitInstance().create(AccountDAO.class);
        String token = PreferencesProvider.providePreferences().getString("token", "");
        Call<Boolean> call = userService.validateQuestion(q.getId(), answer);
        //noinspection NullableProblems
        call.enqueue(new Callback<Boolean>()
        {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response)
            {
                try
                {
                    validate.setValue(response.body());
                } catch (Exception e)
                {
                    Log.d("GameViewModel", e.getMessage() + "ERROR ON VALIDATING ANSWER");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t)
            {
                gameActivity.ErrorDialog();
                Log.e("GameViewModel", Objects.requireNonNull(t.getMessage()));
            }
        });
    }



}
