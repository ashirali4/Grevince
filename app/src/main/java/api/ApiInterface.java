package api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("login.php")
    Call<User> performLogin(@Query("username") String username, @Query("password") String password);

    @GET("register.php")
    Call<User> register(@Query("username") String username,@Query("fullname") String fullname,@Query("email") String email, @Query("password") String password);

    @GET("fetch.php")
    Call<List<User>> getHeroes(@Query("username") String username);

    @GET("response.php")
    Call<List<Responses>> getresponses(@Query("reportid") String reportid);

    @GET("complainadd.php")
    Call<User> addcomplain(@Query("username") String username,@Query("fullname") String fullname,@Query("email") String email,@Query("userid") String userid, @Query("department") String department,@Query("category") String category,@Query("subject") String subject,@Query("message") String message,@Query("identity") String identity);

}

