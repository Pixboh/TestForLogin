package pixboh.testforlogin.WebService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PIXBOH on 04/03/2017.
 */

public class RetrofitBuilder {
    ;
    private static final String BASE_URL_API = "http://10.0.2.2/login/";

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public static RequestInterface createService() {
        return retrofit.create(RequestInterface.class);
    }

   /* public static OkHttpClient getClient() {
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
    }*/

}
