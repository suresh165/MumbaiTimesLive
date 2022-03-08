package suresh.chandra.mumbaitimeslive;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_covertor {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://mumbaitimes.live/wp-json/wp/v2/";

    public static Retrofit getRetrofit() {
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
