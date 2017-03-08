package pixboh.testforlogin.WebService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PIXBOH on 04/03/2017.
 */

public class RetrofitBuilder {
    private static   Gson gson=new GsonBuilder().setLenient().create();
    private static final String BASE_URL_API = "http://10.0.2.2/login/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL_API).
            client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson));
    private static Retrofit retrofit = builder.build();




    public static RequestInterface createService() {
        return retrofit.create(RequestInterface.class);
    }




}
