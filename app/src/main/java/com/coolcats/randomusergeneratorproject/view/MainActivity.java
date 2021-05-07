package com.coolcats.randomusergeneratorproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.coolcats.randomusergeneratorproject.databinding.ActivityMainBinding;
import com.coolcats.randomusergeneratorproject.model.UserResponse;
import com.coolcats.randomusergeneratorproject.presenter.Contract;
import com.coolcats.randomusergeneratorproject.presenter.UserPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private ActivityMainBinding binding;
    private UserAdapter adapter = new UserAdapter(new ArrayList<>());


    private Contract.Presenter presenter = new UserPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.userRecyclerview.setAdapter(adapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.userRecyclerview);

        binding.generateListButton.setOnClickListener(view ->{
            int value = 0;
            if(binding.searchEdittext.getText().toString().isEmpty()){
                showToast("A number must be inserted");
            }else if(value == Integer.parseInt(binding.searchEdittext.getText().toString().trim())){
                showToast("Cannot generate a list of zero users.");
            }else{
                value = Integer.parseInt(binding.searchEdittext.getText().toString().trim());
                presenter.getResults(value);
            }
        });

    }

    @Override
    public void displayResults(List<UserResponse> responses) {
        adapter.setResponses(responses);
    }

    @Override
    public void setStatus(UserPresenter.Status status) {
        switch (status){
            case LOADING:
                binding.progressbar.setVisibility(View.VISIBLE);
                break;
            case COMPLETE:
                binding.progressbar.setVisibility(View.GONE);
                break;
            case ERROR:
                showToast("An error occured.");
                binding.progressbar.setVisibility(View.GONE);
                break;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}