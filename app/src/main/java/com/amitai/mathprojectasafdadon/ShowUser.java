package com.amitai.mathprojectasafdadon;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class ShowUser extends Fragment {

MainViewModel model;
Uri uri;
private ImageView photo;
private Button addPic;
private Button addUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_show_user, container, false);
        photo = view.findViewById(R.id.photo);
        addPic =view.findViewById(R.id.addPic);
        main();
        addUser= view.findViewById(R.id.addUser);

        return  view;
    }

    public void main(){
        model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        ActivityResultLauncher<Intent> startCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()== RESULT_OK){
                    photo.setImageURI(uri);
                }
            }
        });

        addPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startCamera.launch(cameraIntent);
            }
        });

    }



}