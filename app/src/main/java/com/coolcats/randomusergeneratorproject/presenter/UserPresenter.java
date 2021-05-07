package com.coolcats.randomusergeneratorproject.presenter;

import android.util.Log;

import com.coolcats.randomusergeneratorproject.model.UserResponse;
import com.coolcats.randomusergeneratorproject.model.network.UserRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements Contract.Presenter{

    private Contract.View view;

    private UserRetrofit userRetrofit = new UserRetrofit();

    public UserPresenter(Contract.View view) {
        this.view = view;
    }

    List<String> responseList = new ArrayList<>();
    @Override
    public void getResults(int query) {
       view.setStatus(Status.LOADING);
       userRetrofit.getUserResponses(query).enqueue(new Callback<UserResponse>() {
           @Override
           public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
               if(response.body() != null){
                   Log.d("TAG_M", "Data: " + response.body().toString());
                   List<UserResponse> responseList = new ArrayList<UserResponse>(){};
                   UserResponse userResponse = new UserResponse(response.body().getGender(), response.body().getName(),
                                   response.body().getLocation(), response.body().getEmail(), response.body().getLogin(),
                                   response.body().getDob(), response.body().getRegistered(), response.body().getPhone(),
                                   response.body().getCell(), response.body().getId(), response.body().getPicture(),
                                   response.body().getNat());
                   responseList.add(userResponse);

                   view.displayResults(responseList);
                   view.setStatus(Status.COMPLETE);
               }else{
                   view.setStatus(Status.ERROR);
               }
           }

           @Override
           public void onFailure(Call<UserResponse> call, Throwable t) {
                view.setStatus(Status.ERROR);
           }
       });

    }


    public enum Status {
        LOADING,
        COMPLETE,
        ERROR
    }
}
