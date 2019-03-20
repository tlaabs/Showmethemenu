package io.tlaabs.github;

import android.app.Application;
import android.util.Log;

import io.tlaabs.github.model.ApiService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiReposApplication extends Application {
    private Retrofit retrofit;
    private ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        // 어느 Activity에서나 API를 이용할 수 있도록, 이 클래스로 API를 이용한다
        setupAPIClient();
    }

    private void setupAPIClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.30.1.52:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
