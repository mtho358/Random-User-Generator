package com.coolcats.randomusergeneratorproject.presenter;

import com.coolcats.randomusergeneratorproject.model.UserResponse;

import java.util.List;

public interface Contract {

    interface Presenter{
        void getResults(int query);
    }

    interface View{
        void displayResults(List<UserResponse> users);
        void setStatus(UserPresenter.Status status);
    }
}
