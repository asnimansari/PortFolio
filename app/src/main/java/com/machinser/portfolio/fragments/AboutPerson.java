package com.machinser.portfolio.fragments;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.machinser.portfolio.R;
import com.squareup.picasso.Picasso;

/**
 * Created by asnim on 09/06/17.
 */

public class AboutPerson extends Fragment {
//    ImageView banner;
    WebView about_person_view;
    private boolean simpleXmlCardExpanded = false;
    ImageButton onSimpleXmlExampleToggleActionClicked;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onSimpleXmlExampleToggleActionClicked  = (ImageButton) getActivity().findViewById(R.id.onSimpleXmlExampleToggleActionClicked);
        onSimpleXmlExampleToggleActionClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSimpleXmlExampleToggleActionClicked();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("CREATED VoEW","asdasd");
        return inflater.inflate(R.layout.about_person,container,false);
    }
    public void onSimpleXmlExampleToggleActionClicked() {

        toggleExpandButtonImage(onSimpleXmlExampleToggleActionClicked, simpleXmlCardExpanded);

        final TextView description = (TextView) getView().findViewById(R.id.text_simple_xml_description);
        toggleTextExpanded(description, simpleXmlCardExpanded);

        simpleXmlCardExpanded = !simpleXmlCardExpanded;
    }
    private void toggleExpandButtonImage(ImageButton button, boolean isExpanded) {
        final int newDrawableId = isExpanded ?
                R.drawable.ic_expand_more_black_24dp : R.drawable.ic_expand_less_black_24dp;
        final Drawable newDrawable = AppCompatResources.getDrawable(getActivity(), newDrawableId);
        button.setImageDrawable(newDrawable);
    }
    private void toggleTextExpanded(TextView textView, boolean isExpanded) {
        final int newVisibility = isExpanded ? TextView.GONE : TextView.VISIBLE;
        textView.setVisibility(newVisibility);
    }


}
