package com.machinser.portfolio.fragments;


import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.machinser.portfolio.R;
import com.machinser.portfolio.models.FeedBack;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by asnim on 09/06/17.
 */

public class Complaints extends Fragment {

    EditText complaint_subject,complaint_body;
    Button submit_feedback_btn;

    TextInputLayout complaint_subject_layout,complaint_details_layout;
    private DatabaseReference databaseReference;

    public static final int RequestPermissionCode = 1;
    MediaRecorder myAudioRecorder;

    String output_file;
    private File mOutputFile;
    private File mOutputDir;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    FloatingActionButton fabRecordMessage;

    boolean isRecording = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        complaint_subject = (EditText) getView().findViewById(R.id.complaint_subject);
        complaint_body = (EditText) getView().findViewById(R.id.complaint_body);
        submit_feedback_btn = (Button) getView().findViewById(R.id.submit_feedback_btn);
        fabRecordMessage = (FloatingActionButton) getView().findViewById(R.id.fabRecordMessage);
//        RECORDINF SECTION

//
        complaint_subject_layout = (TextInputLayout) getView().findViewById(R.id.complaint_subject_layout);
        complaint_details_layout = (TextInputLayout) getView().findViewById(R.id.complaint_details_layout);

        String about_person_string = getActivity().getResources().getString(R.string.about_person);
//        RECORDING SECTION
        output_file = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ UUID.randomUUID()+".mp3";
//        (TODO) CHANGE AS EMAIL ID AND UUI FORTMAT
//        (TODO) CHECK FOR INTERNET

        fabRecordMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRecording){
                    if(myAudioRecorder!=null){
                        myAudioRecorder.stop();
                        myAudioRecorder.release();
                        myAudioRecorder = null;
                        uploadFile();

                    }
                    else{
                        Log.e("ER","Some Error");
                    }
                    isRecording = false;
                    fabRecordMessage.setImageResource(R.drawable.ic_mic_black_24dp);

                }
                else{
                    checkPermission();
                    if(checkPermission()){
                        MediaRecorderReady();
                        fabRecordMessage.setImageResource(R.drawable.ic_close_white_24dp);
                        Toast.makeText(getActivity(), "Start Recording Your Message", Toast.LENGTH_SHORT).show();
                        isRecording = true;
                    }
                    else{
                        requestPermission();

                    }
                }
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference();
        submit_feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = complaint_subject.getText().toString().trim();
                String body = complaint_body.getText().toString().trim();
                if(subject.length() == 0 || body.length() == 0){
                    Toast.makeText(getActivity(), "Cant Send Without a Subject or Body", Toast.LENGTH_SHORT).show();
                }
                else{
                    FeedBack feedBack = new FeedBack(subject,"user",body);
                    databaseReference.child("feedback").push().setValue(feedBack);
                    Toast.makeText(getActivity(), "Your Feed Back Has Been Registered", Toast.LENGTH_SHORT).show();
                    complaint_subject.setText("");
                    complaint_body.setText("");
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("CREATED VoEW","asdasd");
        return inflater.inflate(R.layout.complaint_page,container,false);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean StoragePermission1 = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission && StoragePermission1) {
                        Toast.makeText(getActivity(), "Permission Granted",
                                Toast.LENGTH_LONG).show();
                        MediaRecorderReady();
                    } else {
                        Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                RECORD_AUDIO);
        int result2 = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2  == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO,READ_EXTERNAL_STORAGE}, RequestPermissionCode);
    }
    public void MediaRecorderReady(){
        mOutputDir = getContext().getCacheDir();

        myAudioRecorder=new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        myAudioRecorder.setOutputFile(output_file);
        try {
            myAudioRecorder.prepare();
            myAudioRecorder.start();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    private void uploadFile(){
        Uri uploadfile = Uri.fromFile(new File(output_file));
        StorageReference audioRef = storageRef.child("feedbacks/"+uploadfile.getLastPathSegment());
        UploadTask uploadTask = audioRef.putFile(uploadfile);

        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Log.e("UPLOAD","Upload Completed");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("UPLOAD","Upload Failed");
            }
        });
    }
}
