package suresh.chandra.mumbaitimeslive;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Interface {
    @GET("posts")
    Call<List<WP_post>> getlist();
}
