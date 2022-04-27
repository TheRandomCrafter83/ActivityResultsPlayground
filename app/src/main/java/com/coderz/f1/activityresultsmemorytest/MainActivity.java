package com.coderz.f1.activityresultsmemorytest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textviewName;
    private TextView textviewEmailAddress;
    private ImageView imageviewPFP;

    ActivityResultLauncher<Intent> getResults = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result == null) return;
            if (result.getData() == null) return;
            if (result.getResultCode() == Activity.RESULT_CANCELED) return;
            String name = result.getData().getStringExtra("name");
            String email = result.getData().getStringExtra("email");
            textviewName.setText(name);
            textviewEmailAddress.setText(email);
        }
    });

    ActivityResultLauncher<String> chooseImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            imageviewPFP.setImageURI(result);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
    }

    private void initWidgets(){
        imageviewPFP = findViewById(R.id.imageview_pfp);
        Button buttonChooseImage = findViewById(R.id.button_browse);
        Button buttonEditInformation = findViewById(R.id.button_edit);
        textviewName = findViewById(R.id.textview_name);
        textviewEmailAddress = findViewById(R.id.textview_email_address);
        buttonChooseImage.setOnClickListener(buttonChooseImageListener);
        buttonEditInformation.setOnClickListener(buttonEditInformationListener);
    }

    final private View.OnClickListener buttonEditInformationListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent getResultsIntent = new Intent(view.getContext(),MainActivity2.class);
            getResults.launch(getResultsIntent);
        }
    };

    final private View.OnClickListener buttonChooseImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            chooseImage.launch("image/*");
        }
    };
}