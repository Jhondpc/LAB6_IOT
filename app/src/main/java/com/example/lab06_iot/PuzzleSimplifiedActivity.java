package com.example.lab06_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageView;

import com.example.lab06_iot.databinding.ActivityPuzzleSimplifiedBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PuzzleSimplifiedActivity extends AppCompatActivity {

    ActivityPuzzleSimplifiedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleSimplifiedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String imageUri = intent.getStringExtra("imageUri");

        if(imageUri != null){
            Uri uri = Uri.parse(imageUri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                int rows = 3;
                int columns = 3;
                int partWidth = originalBitmap.getWidth() / columns;
                int partHeight = originalBitmap.getHeight() / rows;
                int paddingInDp = 3;

                List<ImageView> imageViewArrayList = new ArrayList<>();
                if (originalBitmap != null) {
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < columns; j++) {
                            if (i < rows - 1 || j < columns - 1) {
                                Bitmap part = Bitmap.createBitmap(originalBitmap, j * partWidth, i * partHeight, partWidth, partHeight);
                                ImageView imageView = new ImageView(this);
                                imageView.setImageBitmap(part);

                                float scale = getResources().getDisplayMetrics().density;
                                int paddingInPixels = (int) (paddingInDp * scale + 0.5f);

                                imageView.setPadding(paddingInPixels, paddingInPixels, paddingInPixels, paddingInPixels);

                                imageViewArrayList.add(imageView);
                            }
                        }
                    }

                    for (ImageView imageView : imageViewArrayList){
                        binding.gridLayout3x3.addView(imageView);
                    }
                } else {
                    Log.d("msg-test", "Error al cargar la imagen");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("msg-test", "Error de lectura de la imagen");
            }


        }

    }


}