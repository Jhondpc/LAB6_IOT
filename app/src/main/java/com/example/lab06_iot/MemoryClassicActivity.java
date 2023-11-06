package com.example.lab06_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.lab06_iot.databinding.ActivityMemoryClassicBinding;

public class MemoryClassicActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;

    private int nroImagenes = 0;
    ActivityMemoryClassicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemoryClassicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAgregarImg.setOnClickListener(view -> {
            Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent1, REQUEST_IMAGE_PICK);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            Uri uri = Uri.parse(selectedImageUri.toString());
            nroImagenes = nroImagenes + 1;
            binding.textViewCount.setText(String.valueOf(nroImagenes));

            ImageView imageView = new ImageView(this);
            imageView.setImageURI(uri);

            binding.gridLayout.addView(imageView);
        }
    }
}