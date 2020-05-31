package cat.udl.tidic.amd.dam_tips.network;

import java.io.IOException;

import cat.udl.tidic.amd.dam_tips.preferences.PreferencesProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ServiceInterceptor implements Interceptor {

    @EverythingIsNonNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String token = PreferencesProvider.providePreferences().getString("token","");

        if(request.header("No-Authentication") == null){
            request = request.newBuilder()
                    .addHeader("Authorization", token)
                    .build();
        }
        return chain.proceed(request);
    }
}
