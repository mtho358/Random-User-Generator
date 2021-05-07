package com.coolcats.randomusergeneratorproject.model.network;

import com.coolcats.randomusergeneratorproject.model.UserResponse;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class UserRetrofit {

//https://randomuser.me/api/?results=50
//BaseURL = https://randomuser.me
//Path = api/

    private UserService userService = createRetrofit().create(UserService.class);

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<UserResponse> getUserResponses(int value){
        return userService.getUsers(value);
    }

    interface UserService{
        @GET("api")
        Call<UserResponse> getUsers(@Query("q") int query);
    }

}
