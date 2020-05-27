package cat.udl.tidic.amd.dam_tips;

import android.app.Application;
import android.content.Context;

import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesProvider.init(this);
        mContext = this.getApplicationContext();
    }

    public static Context getAppContext(){
        return mContext;
    }
}

