package com.machinser.portfolio;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.machinser.portfolio.fragments.AboutPerson;
import com.machinser.portfolio.fragments.Complaints;
import com.machinser.portfolio.fragments.NewsFeed;

import java.lang.reflect.Field;

import static com.machinser.portfolio.R.id.toolbar;

public class NavigationPage extends AppCompatActivity {

    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return displaySelectedScreen(item.getItemId());

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(1).setChecked(true);
        displaySelectedScreen(R.id.navigation_feed);

        Toolbar toolbar  = (Toolbar)findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);


        TextView yourTextView = null;
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/musketbold.otf");

        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            yourTextView = (TextView) f.get(toolbar);
            yourTextView.setTypeface(face);
            yourTextView.setTextColor(0xffffffff);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }
    public boolean displaySelectedScreen(int itemID){
        Fragment fragment = null;
        Log.e("WHERE","displaySelectedScreen()");
        switch (itemID){
            case R.id.navigation_about:
                fragment = new AboutPerson();
                break;

            case R.id.navigation_feed:
             fragment = new NewsFeed();
                break;

        case R.id.navigation_complaint:
             fragment = new Complaints();
             break;




        }
        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content,fragment);
            ft.commit();
        }

        return true;
    }


}
