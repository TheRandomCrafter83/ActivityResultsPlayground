package com.coderz.f1.activityresultsmemorytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    private TextInputEditText edittextName;
    private TextInputEditText edittextEmail;
    private RecyclerView recyclerviewDomains;
    private final ArrayList<String> domains = new ArrayList<>();
    private RecyclerViewDomainAdapter domainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initWidgets();
    }

    private void initWidgets() {
        Button buttonDone = findViewById(R.id.button_done);
        edittextName = findViewById(R.id.edittext_name);
        edittextEmail = findViewById(R.id.edittext_email);
        edittextEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                switch (actionId){
                    case EditorInfo.IME_ACTION_DONE:
                        buttonDone.performClick();
                        break;
                }
                return handled;
            }
        });
        buttonDone.setOnClickListener(buttonDoneListener);
        recyclerviewDomains = findViewById(R.id.domain_bar);
        DividerItemDecoration did = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(this,R.drawable.vertical_divider);
        did.setDrawable(divider);
        recyclerviewDomains.addItemDecoration(did);
        recyclerviewDomains.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        domains.addAll(Arrays.asList("@gmail.com","@yahoo.com","@hotmail.com"));
        domainAdapter = new RecyclerViewDomainAdapter(getLayoutInflater(), domains, new RecyclerViewDomainAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                edittextEmail.getText().append(domains.get(position));
            }
        });
        recyclerviewDomains.setAdapter(domainAdapter);

    }

    private View.OnClickListener buttonDoneListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = edittextName.getText().toString();
            String email = edittextEmail.getText().toString();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("name",name);
            returnIntent.putExtra("email",email);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    };
}