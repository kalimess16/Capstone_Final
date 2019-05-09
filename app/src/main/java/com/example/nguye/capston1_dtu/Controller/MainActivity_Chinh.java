package com.example.nguye.capston1_dtu.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity_Chinh extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView userName,userEmail;
    ImageView bmImage;
    Button userLogout;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    NavigationView nvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__chinh);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        homePage page = new homePage();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.search_edit_frame, page).commit();


        userLogout = findViewById(R.id.btnLogout);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        //setContentView(R.layout.nav_header_main_activity__chinh);
        nvView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        userName = header.findViewById(R.id.textViewName);
        userEmail = header.findViewById(R.id.textViewEmail);
        bmImage = header.findViewById(R.id.imageView);
        userName.setText(firebaseUser.getDisplayName()+"");
        userEmail.setText(firebaseUser.getEmail()+"");
//        bmImage.setImageURI(firebaseUser.getPhotoUrl());
        Log.e("Image", firebaseUser.getPhotoUrl() + "");
        if(firebaseUser.getPhotoUrl() != null)
            Picasso.get().load(firebaseUser.getPhotoUrl().toString()).into(bmImage);

    }

    @Override
    public void onBackPressed() {
       /* DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
       final AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setMessage("Bạn có muốn thoát ?");
       builder.setCancelable(true);
       builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               moveTaskToBack(true);
               android.os.Process.killProcess(android.os.Process.myPid());
               System.exit(1);
           }
       });
       builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
           }
       });
       AlertDialog alertDialog = builder.create();
       alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity__chinh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_homePage) {
            fragment = new homePage();
        } else if (id == R.id.nav_cong_thuc) {
            fragment = new Show_CT(); // done
        } else if (id == R.id.nav_bai_hoc) {
            fragment = new BaiHoc();
        } else if (id == R.id.nav_test) {
            fragment = new TesT_List();
        } else if (id == R.id.nav_tu_van) {
            startActivity(new Intent(this, Chatmail.class));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(this, Share.class));
        } else if (id == R.id.nav_Video) {
            fragment = new List_Video();
        } else if (id == R.id.nav_logout) {
           if (isOnline()){
               FirebaseAuth.getInstance().signOut();
               LoginManager.getInstance().logOut();
               startActivity(new Intent(this, LoginActivity.class));
           }
           else {
               Toast.makeText(getApplicationContext(),"Kết nối bị lỗi", Toast.LENGTH_LONG).show();
           }
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.search_edit_frame, fragment);

            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     * check online
     */
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isOnline()){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Vui lòng kết nối internet");
            builder.setCancelable(true);
            builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

}
