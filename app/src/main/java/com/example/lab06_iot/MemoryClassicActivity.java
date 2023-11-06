package com.example.lab06_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lab06_iot.databinding.ActivityMemoryClassicBinding;

public class MemoryClassicActivity extends AppCompatActivity {

    ActivityMemoryClassicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemoryClassicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}