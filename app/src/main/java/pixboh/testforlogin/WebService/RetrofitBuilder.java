package pixboh.testforlogin.WebService;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PIXBOH on 04/03/2017.
 */

public class RetrofitBuilder {
    private static final String BASE_URL_API = "http://10.0.2.2/login/";
    private static Gson gson = new GsonBuilder().setLenient().create();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(getIntercept());
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL_API).
            client(httpClient.build())

            .addConverterFactory(GsonConverterFactory.create(gson));
    private static Retrofit retrofit = builder.build();


    public static RequestInterface createService() {
        return retrofit.create(RequestInterface.class);
    }
    static  Interceptor getIntercept(){


        return new IntercepClass();
    }

    static class IntercepClass implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long timeStart = System.nanoTime();
            Log.e("Interception requete", String.format("Envoi requete %s à %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            Response response=chain.proceed(request);
            long timeEnd = System.nanoTime();
            Log.e("Interecption requete", String.format("Recoit  reponse depuis %s en %.1fms%n%s",
                    response.request().url(), (timeEnd - timeStart) / 1e6d, response.headers()));
//            Log.e("body", response.body().string());

            return response;
        }
    }
}