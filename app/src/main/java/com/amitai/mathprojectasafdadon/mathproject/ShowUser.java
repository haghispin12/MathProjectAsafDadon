package com.amitai.mathprojectasafdadon.mathproject;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.amitai.mathprojectasafdadon.R;

import java.util.ArrayList;


public class ShowUser extends Fragment {


MainViewModel model;
Uri uri;
private EditText name;
private EditText score;
private EditText rate;
private ImageView photo;
private Button addPic;
private Button addUser;
private RecyclerView rcShowUsers;
private User selectedUser;
ArrayList arr= new ArrayList<>();
    private MenuItem itemDelete;
    private MenuItem itemEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_show_user, container, false);
        name = view.findViewById(R.id.UserName);
        score = view.findViewById(R.id.USerScore);
        rate = view.findViewById(R.id.UserRate);
        photo = view.findViewById(R.id.photo);
        addPic =view.findViewById(R.id.addPic);
        addUser= view.findViewById(R.id.addUser);
        rcShowUsers = view.findViewById(R.id.rcShowUsers);
        main();
        return  view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_meue,menu);
        itemDelete = menu.findItem(R.id.action_delete);
//        itemDelete.setVisible(false);
        itemEdit = menu.findItem(R.id.action_edit);
//        itemEdit.setVisible(false);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_delete:
                model.deleteById(requireActivity(), selectedUser.getId());
                //do something
                return true;

            case R.id.action_edit:
                name.setText(selectedUser.getName());
                score.setText(selectedUser.getScore()+"");
                rate.setText(selectedUser.getRate()+"");
                addUser.setText("update");
                model.useUpdate(requireActivity(), selectedUser);
                return true;
        }
        return false;
    }

    public void main(){
        setHasOptionsMenu(true);





        model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        ActivityResultLauncher<Intent> startCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()== RESULT_OK){
                    photo.setImageURI(uri);
                    model.setUri(uri);
                }
            }
        });

        name.setText(model.getUserName());
        score.setText(model.getScore()+"");
        rate.setText(model.getUserRate()+"");
        model.useSelectAll(requireActivity());




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

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addUser.getText().equals("add user"))
                    model.insertUser(requireActivity());
                else {
                    selectedUser.setName(name.getText()+"");
                    selectedUser.setScore(Integer.parseInt(score.getText().toString()));
                    selectedUser.setRate(Integer.parseInt(rate.getText().toString()));
                    model.useUpdate(requireActivity(), selectedUser);

                }
            }
        });


        model.users.observe(requireActivity(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                arr=users;
                UserAdapter userAdapter = new UserAdapter(arr, new UserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(User item) {
                        selectedUser=item;
                    }
                });

                rcShowUsers.setLayoutManager(new LinearLayoutManager(requireContext()));
                rcShowUsers.setAdapter(userAdapter);
                rcShowUsers.setHasFixedSize(true);


            }
        });



    }




}