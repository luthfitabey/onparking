package com.anjilibey.onpark;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by hp on 06/05/2018.
 */

public interface BaseApiService {

    // Fungsi ini untuk memanggil API login http opo cok
    @Headers({
            "Accept : application/json",
            "Content-type : application/json"
    })
    @FormUrlEncoded
    @POST("http://10.203.254.215:8000/api/auth/login/")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API login http opo cok
    @Headers({
            "Accept : application/json",
            "Content-type : application/json"
    })
    @FormUrlEncoded
    @POST("http://10.203.254.215:8000/api/auth/student/register/")
    Call<ResponseBody> registerRequest(
            @Field("name") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("nif") String nif,
            @Field("majors") String prodi
    );
    @Headers({
            "Accept : application/json",
            "Content-type : application/json"
    })
    @FormUrlEncoded
    @POST("http://10.203.254.215:8000/api/auth/operator/register/")
    Call<ResponseBody> registerOpRequest(
            @Field("name") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("operator_number") String noPeg
    );

}