package pixboh.testforlogin.WebService;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import pixboh.testforlogin.Application.AppContext;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PIXBOH on 04/03/2017.
 */

public class RetrofitBuilder {
    static AppContext appContext;
    private static final String BASE_URL_API = "https://github.com/Pixboh/TestForLogin/script";

    private static Retrofit.Builder builder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .client(getClient());
    private static Retrofit retrofit = builder.build();

    public static RequestInterface createService() {
        return retrofit.create(RequestInterface.class);
    }

    public static OkHttpClient getClient() {
        File file = new File(appContext.getCacheDir(), "fileforclient");
        OkHttpClient.Builder httpClient = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Cache cache = new Cache(file, 1024 * 1024);
            httpClient = new OkHttpClient.Builder()
                    .cache(cache);
        }
        return httpClient.build();
    }

}