package con.gionee.bloodsoulnote.retrofit2;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IUserBiz {

    @GET("tips?api_key=9dac6633be895da152187b9c1a5c0042&module=topic&api_sign=dc13c4012caeb48a6b05a961397d3318")
    Call<User> getUsers();

    @GET("tips")
    Call<User> getUsers2(@Query("api_key") String api_key, @Query("module") String module, @Query("api_sign") String api_sign);

    @POST("tips")
    @FormUrlEncoded
    Call<User> getUsers3(@Field("api_key") String api_key, @Field("module") String module, @Field("api_sign") String api_sign);

    @POST("tips")
    @Multipart
    Call<User> getUsers4(@Part MultipartBody.Part photo, @Part("username") RequestBody name, @Part("password") RequestBody password);

}
