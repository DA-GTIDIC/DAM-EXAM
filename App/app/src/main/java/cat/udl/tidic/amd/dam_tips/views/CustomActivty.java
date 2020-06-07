package cat.udl.tidic.amd.dam_tips.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import cat.udl.tidic.amd.dam_tips.R;

public class CustomActivty extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void goTo(Class _class){
        Log.d(TAG, "goTo() -> Navigate to " + _class.getSimpleName());
        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }
}
