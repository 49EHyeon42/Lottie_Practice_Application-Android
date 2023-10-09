package dev.ehyeon.lottiepracticeapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {

    @GET("/hello")
    Call<Response> hello();
}
