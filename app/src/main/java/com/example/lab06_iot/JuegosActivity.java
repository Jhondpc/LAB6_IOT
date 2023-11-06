package com.example.lab06_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.lab06_iot.databinding.ActivityJuegosBinding;

public class JuegosActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;
    ActivityJuegosBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJuegosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");

        binding.textViewUser.setText(usuario);

        binding.btnJuegoPuzzle.setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent1, REQUEST_IMAGE_PICK);
        });

        binding.btnJuegoMemory.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, MemoryClassicActivity.class);
            startActivity(intent1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            Intent puzzleIntent = new Intent(this, PuzzleSimplifiedActivity.class);
            puzzleIntent.putExtra("imageUri", selectedImageUri.toString());
            startActivity(puzzleIntent);
        }
    }

}