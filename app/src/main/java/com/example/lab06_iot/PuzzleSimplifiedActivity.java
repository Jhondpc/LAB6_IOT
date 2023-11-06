package com.example.lab06_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.gridlayout.widget.GridLayout;

import android.view.View;
import android.widget.ImageView;

import com.example.lab06_iot.databinding.ActivityPuzzleSimplifiedBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleSimplifiedActivity extends AppCompatActivity {

    ActivityPuzzleSimplifiedBinding binding;

    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPuzzleSimplifiedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String imageUri = intent.getStringExtra("imageUri");

        if(imageUri != null){
            binding.gridLayout3x3.setVisibility(View.VISIBLE);
            binding.gridLayout4x4.setVisibility(View.GONE);
            binding.gridLayout5x5.setVisibility(View.GONE);
            List<ImageView> list = dividirImagen(3,3, imageUri);
            for (ImageView imageView : list){
                binding.gridLayout3x3.addView(imageView);
            }

            List<ImageView> list4x4 = dividirImagen(4,4, imageUri);
            for (ImageView imageView : list4x4){
                binding.gridLayout4x4.addView(imageView);
            }
            List<ImageView> list5x5 = dividirImagen(5,5, imageUri);
            for (ImageView imageView : list5x5){
                binding.gridLayout5x5.addView(imageView);
            }
            binding.textView3x3.setOnClickListener(view -> {
                binding.gridLayout3x3.setVisibility(View.GONE);
                binding.gridLayout4x4.setVisibility(View.VISIBLE);
                binding.gridLayout5x5.setVisibility(View.GONE);
                binding.textView3x3.setBackgroundResource(R.drawable.background2);
                binding.textView4x4.setBackgroundResource(R.drawable.background3);
                binding.textView5x5.setBackgroundResource(R.drawable.background3);
            });
            binding.textView4x4.setOnClickListener(view -> {
                binding.gridLayout3x3.setVisibility(View.GONE);
                binding.gridLayout4x4.setVisibility(View.VISIBLE);
                binding.gridLayout5x5.setVisibility(View.GONE);
                binding.textView3x3.setBackgroundResource(R.drawable.background3);
                binding.textView4x4.setBackgroundResource(R.drawable.background2);
                binding.textView5x5.setBackgroundResource(R.drawable.background3);
            });
            binding.textView5x5.setOnClickListener(view -> {
                binding.gridLayout3x3.setVisibility(View.GONE);
                binding.gridLayout4x4.setVisibility(View.GONE);
                binding.gridLayout5x5.setVisibility(View.VISIBLE);
                binding.textView3x3.setBackgroundResource(R.drawable.background3);
                binding.textView4x4.setBackgroundResource(R.drawable.background3);
                binding.textView5x5.setBackgroundResource(R.drawable.background2);
            });

            binding.btnComenzarJuego.setOnClickListener(view -> {
                desordenarTableroAleatoriamente(binding.gridLayout3x3, list);
                desordenarTableroAleatoriamente(binding.gridLayout4x4, list4x4);
                desordenarTableroAleatoriamente(binding.gridLayout5x5, list5x5);
            });
        }

    }

    public List<ImageView>  dividirImagen(int columas, int filas, String imageUri){

        Uri uri = Uri.parse(imageUri);
        List<ImageView> imageViewArrayList = new ArrayList<>();
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            int rows = filas;
            int columns = columas;
            int partWidth = originalBitmap.getWidth() / columns;
            int partHeight = originalBitmap.getHeight() / rows;
            int paddingInDp = 3;


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

            } else {
                Log.d("msg-test", "Error al cargar la imagen");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("msg-test", "Error de lectura de la imagen");
        }
        return imageViewArrayList;
    }

    private void desordenarTableroAleatoriamente(GridLayout gridLayout, List<ImageView> imageViews) {
        Collections.shuffle(imageViews); // Se mezcla aleatoriamente la lista de ImageView

        gridLayout.removeAllViews(); // Se limpia el GridLayout

        for (ImageView imageView : imageViews) { // Se agrega las ImageView desordenadas al GridLayout
            gridLayout.addView(imageView);
        }
    }


}