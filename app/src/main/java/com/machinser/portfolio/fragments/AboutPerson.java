package com.machinser.portfolio.fragments;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.content.res.AppCompatResources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.machinser.portfolio.R;
import com.squareup.picasso.Picasso;

/**
 * Created by asnim on 09/06/17.
 */

public class AboutPerson extends Fragment {
    ImageView banner;
    ImageView round_back;
    WebView about_person_view;
    private boolean simpleXmlCardExpanded = false;
//    ImageButton onSimpleXmlExampleToggleActionClicked;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DisplayMetrics disp = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(disp);
        int heigh=disp.heightPixels/3;
        int set=disp.heightPixels/10;
        round_back = (ImageView) getActivity().findViewById(R.id.rd);
        banner = (ImageView)getActivity().findViewById(R.id.profile_image);
        heigh=heigh-60;
        RelativeLayout.LayoutParams l=new RelativeLayout.LayoutParams(210,210);
//        l.setMargins(0,heigh,0,0);
//        banner.setMaxHeight(set);
//        banner.setMinimumHeight(set);
//        banner.setMaxWidth(set);
//        banner.setMinimumWidth(set);
//
//        banner.setLayoutParams(l);
//        onSimpleXmlExampleToggleActionClicked  = (ImageButton) getActivity().findViewById(R.id.onSimpleXmlExampleToggleActionClicked);
//        onSimpleXmlExampleToggleActionClicked.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSimpleXmlExampleToggleActionClicked();
//            }
//        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("CREATED VoEW","asdasd");
        return inflater.inflate(R.layout.about_person,container,false);
    }

}
