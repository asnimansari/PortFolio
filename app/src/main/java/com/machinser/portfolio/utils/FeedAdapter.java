package com.machinser.portfolio.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.machinser.portfolio.R;
import com.machinser.portfolio.fragments.DetailedNews;
import com.machinser.portfolio.models.Feed;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

//import com.machinser.portfolioadmin.models.News;

/**
 * Created by asnim on 26/05/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>{

    final static String TAG = "FeedAdapter";
    static ArrayList<Feed> feedArrayList;
    FragmentManager fragmentManager;

    private final int JPG  = 1;
    private final int PNG = 2;

    Context context;
    public FeedAdapter(ArrayList<Feed> feedArrayList, Context context, FragmentManager fragmentManager){
        this.feedArrayList = feedArrayList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.individual_news_card,parent,false);
        return new ViewHolder(view,context,fragmentManager);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String event_title = feedArrayList.get(position).event_title;
        String event_body = feedArrayList.get(position).event_body;
        String event_image = feedArrayList.get(position).event_image;

        holder.event_title.setText( event_title);
        holder.event_body.setText(event_body);
        String updated_before = feedArrayList.get(position).getDisplayDateString();
        if(updated_before !=null){
            holder.added_before.setText(updated_before);
        }


        if (event_image !=null){
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageReference = storage.getReferenceFromUrl(event_image);
//            Log.e("EVENT_IMAGE",event_image);
//
            Picasso.with(context)
                    .load(event_image)
                    .fit()
                    .placeholder(R.drawable.mini_icon)
                    .into(holder.event_image);
//            final String file_name = storageReference.getName();
//

//            int file_
//            int file_type = 0;
//
//            if (event_image.contains(".png")){
//                file_type = PNG;
//            }
//            else if (event_image.contains(".jpg")){
//                file_type = JPG;
//            }

//            String file_name1 = getOnlyFileNameWithExtention(file_name,file_type);

//
//            storageReference.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//                @Override
//                public void onSuccess(byte[] bytes) {
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                    Picasso.with(context)
//                            .load(getImageUri(context,bitmap,file_name))
//                            .fit()
//                            .into(holder.event_image);
//                }
//            });
        }

    }

    private String getOnlyFileNameWithExtention(String file_name, int file_type) {
        int last_postion;

        switch (file_type){
            case JPG:
                last_postion = file_name.lastIndexOf(".jpg") + 4;
                break;

            case PNG:
                last_postion = file_name.lastIndexOf(".png") + 4;
                break;
            default:
                return null;

        }
        String file_only_name  = file_name.substring(file_name.lastIndexOf("feed"),last_postion);
        Log.e("FILE_ONY",file_only_name);
        return file_only_name;

    }

    @Override
    public int getItemCount() {
        return feedArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView event_title;
        public TextView event_body;
        public TextView added_before;
        public ImageView event_image;

        Context context;
        FragmentManager fragmentManger;

        public ViewHolder(View itemView,Context context,FragmentManager fragmentManager) {
            super(itemView);
            event_title = (TextView)itemView.findViewById(R.id.event_title);
            event_body = (TextView)itemView.findViewById(R.id.event_content);
            added_before = (TextView)itemView.findViewById(R.id.added_before);
            event_image = (ImageView)itemView.findViewById(R.id.event_image);
            this.context = context;
            this.fragmentManger = fragmentManager;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position= getAdapterPosition();
            Log.e(TAG,"Postion = " + position);

            Bundle bundle = new Bundle();
            bundle.putString("event_title",feedArrayList.get(position).event_title);
            bundle.putString("event_body",feedArrayList.get(position).event_body);

            if(feedArrayList.get(position).date_string !=null){
                bundle.putString("date_string",feedArrayList.get(position).date_string.toString());
            }

            if(feedArrayList.get(position).event_image !=null){
                bundle.putString("event_image",feedArrayList.get(position).event_image);
            }


            Fragment fragment = new DetailedNews();
            fragment.setArguments(bundle);
            fragmentManger.beginTransaction().replace(R.id.content,fragment).commit();


        }
    }
    public static Uri getImageUri(Context inContext, Bitmap inImage,String file_name) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, file_name, null);
        return Uri.parse(path);
    }
}