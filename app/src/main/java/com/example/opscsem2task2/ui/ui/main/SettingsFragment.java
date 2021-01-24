package com.example.opscsem2task2.ui.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.opscsem2task2.R;
import com.example.opscsem2task2.SettingsVariableStorage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private MainViewModel mViewModel;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String currentUserID, TAG;
    private EditText edName;
    private EditText edHeight;
    private Switch swMetric;
    private Switch swImperial;
    private Switch swNatural;
    private Switch swArtistic;
    private Switch swMHistoric;
    SettingsVariableStorage settings = new SettingsVariableStorage();

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_activity, container, false);

        Button btnCompleteSettings = (Button)v.findViewById(R.id.btnSaveSettings);
        btnCompleteSettings.setOnClickListener(this);
        db =  FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        edName = (EditText)v.findViewById(R.id.edtName);
        edHeight = (EditText)v.findViewById(R.id.edtHeight);
        swMetric = (Switch)v.findViewById(R.id.swMetric);
        swImperial = (Switch)v.findViewById(R.id.swImperial);
        swNatural = (Switch)v.findViewById(R.id.swNatural);
        swArtistic = (Switch)v.findViewById(R.id.swArtistic);
        swMHistoric = (Switch)v.findViewById(R.id.swHistorical);
        swMetric.setOnCheckedChangeListener(this);
        swImperial.setOnCheckedChangeListener(this);
        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveSettings:

                settings.setName(edName.getText().toString());
                settings.setEmail(edHeight.getText().toString());
                settings.setMetric(swMetric.isChecked());
                settings.setImperial(swImperial.isChecked());
                settings.setArtistic(swArtistic.isChecked());
                settings.setHistoral(swMHistoric.isChecked());
                settings.setNatural(swNatural.isChecked());

                DocumentReference docRef = db.collection("Users").document(currentUserID);
                docRef.set(settings.CreateMap(settings)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot saved Successfully");
                        Toast.makeText(getActivity(), "Settings Saved Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "DocumentSnapshot Failed to safe.");
                    }
                });
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        final DocumentReference docRef = db.collection("Users").document(currentUserID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        edName.setText(document.get("name").toString());
                        settings.setName(document.get("name").toString());
                        edHeight.setText(document.get("email").toString());
                        settings.setEmail(document.get("email").toString());
                        swMetric.setChecked(document.getBoolean("metric"));
                        settings.setMetric(document.getBoolean("metric"));
                        swImperial.setChecked(document.getBoolean("imperial"));
                        settings.setMetric(document.getBoolean("imperial"));
                        swArtistic.setChecked(document.getBoolean("artistic"));
                        swMHistoric.setChecked(document.getBoolean("historal"));
                        swNatural.setChecked(document.getBoolean("natural"));
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.swMetric:
                if(buttonView.isPressed())
                {
                    if(isChecked)
                    {
                        swImperial.setChecked(false);
                    }
                    break;
                }
            case R.id.swImperial:
                if(buttonView.isPressed())
                {
                    if(isChecked)
                    {
                        swMetric.setChecked(false);
                    }
                    break;
                }
            case R.id.swArtistic:
                if(buttonView.isPressed())
                {
                    if(isChecked)
                    {

                    }
                    break;
                }
            case R.id.swHistorical:
                if(buttonView.isPressed())
                {
                    if(isChecked)
                    {

                    }
                    break;
                }
            case R.id.swNatural:
                if(buttonView.isPressed())
                {
                    if(isChecked)
                    {

                    }
                    break;
                }
                }
        }
    }