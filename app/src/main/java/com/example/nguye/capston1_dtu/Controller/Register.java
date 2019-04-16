package com.example.nguye.capston1_dtu.Controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.capston1_dtu.Adapter_controller.AnhAdapter;
import com.example.nguye.capston1_dtu.Adapter_controller.CityAdapter;
import com.example.nguye.capston1_dtu.Model.Anh;
import com.example.nguye.capston1_dtu.Model.City;
import com.example.nguye.capston1_dtu.Model.User;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.Validate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<City> mCityList;
    private CityAdapter mAdapter;
    ArrayAdapter<City> cityArrayAdapter;
    String[] categories={"Tất cả","Đà Nẵng","Hà Nội"};

    private TextView textViewDK,btnDangNhap;

    ArrayList<City> listCity;
    private String name;
    private Spinner SpinnerCity,myListView;
    private Button btnDangKi;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebase;
    private TextInputLayout textInputEmail,textInputNAME,textInputPassword, textInputConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        Anhxa();
        initializeViews();

        btnDangKi.setOnClickListener(this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, LoginActivity.class));
            }
        });

    }

    private void DangkiRealtime( String email,String city, String password, String school){
       /* String username = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();
        String cfpassword = editTextConfirm.getText().toString().trim();*/
        User newUser = new User(email,city,password,school);
        mDatabase.child("UserID").child(city).child(school).push().setValue(newUser);
    }
    public void Anhxa(){
        btnDangKi = findViewById(R.id.btnDangki);
        btnDangNhap=findViewById(R.id.btnDangNhap);
        textInputNAME = findViewById(R.id.textInput_NAME);
        textInputEmail = findViewById(R.id.textInput_Email);
        textInputPassword=findViewById(R.id.textInput_Password);
        textInputConfirmPassword=findViewById(R.id.textInputConfirm_Password);
        SpinnerCity=findViewById(R.id.spinnerCity);
        myListView=findViewById(R.id.myListView);
//        Log.e("abc",SpinnerCity.toString());
    }

    private void getSelectedCategoryData(int categoryID) {
//arraylist to hold selected cosmic bodies
        ArrayList<City> citySchool = new ArrayList<>();
        if(categoryID == 0)
        {
            cityArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getSchool());
        }else{
//filter by id
            for (City city : getSchool()) {
                if (city.getCategoryId() == categoryID) {
                    citySchool.add(city);
                }
            }
//instatiate adapter a
            cityArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, citySchool);
        }
//set the adapter to GridView
        myListView.setAdapter(cityArrayAdapter);
    }

    private void initializeViews() {
        SpinnerCity = findViewById(R.id.spinnerCity);
        SpinnerCity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));
        myListView = findViewById(R.id.myListView);
        myListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getSchool()));
//spinner selection events
        SpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemID) {
                if (position >= 0 && position < categories.length) {
                    getSelectedCategoryData(position);
                } else {
                    Toast.makeText(Register.this, "Selected Category Does not Exist!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private ArrayList<City> getSchool() {
        ArrayList<City> data = new ArrayList<>();
        data.clear();
        data.add(new City("THPT Thái Phiên",1));
        data.add(new City("THPT Trần Phú",1));
        data.add(new City("THPT Nguyễn Hiền",1));
        data.add(new City("THPT Phan Châu Trinh",1));
        data.add(new City("THPT SkyLine",1));
        data.add(new City("THPT Thanh Khê",1));
        data.add(new City("THPT Ngô Quyền",1));
        data.add(new City("THPT Hai Bà Trưng",2));
        return data;
    }

    /** Load các trường vào Spinner*/
   /* private List<String> LoadListCity(){

        mCityList = new ArrayList<>();
        mCityList.add(new City("Đà Nẵng"));
        mCityList.add(new City("THPT Thái Phiên"));

        List<String> cityName = new ArrayList<>();
        for(City city: mCityList){
            cityName.add(city.getmSchool());
        }
        return cityName;

    }*/
   /* private void loadCity(){
        Log.e("abcd",SpinnerCity.getSelectedItem().toString());
        if(SpinnerCity.getSelectedItem().toString().equals("Đà Nẵng")){
            mDatabase=FirebaseDatabase.getInstance().getReference("City").child("Đà Nẵng");
            final City[] city= {null};
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        String school = (String) snapshot.child("trường").getValue().toString();
//                      String image = (String) snapshot.child("image").getValue();
                        city[0] = new City(school);
                        listCity.add(city[0]);
                    }
                    mAdapter = new CityAdapter(Register.this,listCity);
                    SpinnerCity.setAdapter(mAdapter);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }*/
   /* private void CityAdapter(){
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,LoadListCity());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//       mAdapter = new CityAdapter(this,  );
        SpinnerCity.setAdapter(aa);
        SpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) parent.getItemAtPosition(position);
//                String clickedCountryName = clickedItem.getmSchool();
                Toast.makeText(Register.this, clickedItem + " selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    private  void Dangki(){
        final String email = textInputEmail.getEditText().getText().toString().trim();
        final String password = textInputPassword.getEditText().getText().toString().trim();
        final String city = SpinnerCity.getSelectedItem().toString().trim();
        final String school = myListView.getSelectedItem().toString().trim();
        String email1 = textInputEmail.getEditText().getText().toString().trim();
        String password1 = textInputPassword.getEditText().getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "ĐĂNG KÍ TÀI KHOẢN THÀNH CÔNG, VÀO EMAIL CỦA BẠN ĐỂ XÁC NHẬN ĐĂNG KÍ",
                                        Toast.LENGTH_LONG).show();
                                btnDangNhap.setText("Trờ về đăng nhập");
                                DangkiRealtime(email,city,password,school);
                            }else{
                                Toast.makeText(Register.this,  task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Register.this, task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /** thêm cái jj */
    @Override
    public void onClick(View v) {
        Validate validate = new Validate();
//
//        String username = textInputNAME.getEditText().getText().toString().trim();
        String email = textInputEmail.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();
        String cfpassword = textInputConfirmPassword.getEditText().getText().toString().trim();
        if(v==btnDangKi){
            if(validate.validateEmail(email,textInputEmail)
                    && validate.validatePassword(password, textInputPassword)
                    && validate.validateConfirmPassword(password,cfpassword,textInputConfirmPassword)){
                Dangki();
//
                return;
            }
            if(validate.validatePassword(password,textInputPassword)){
                Dangki();
//
                return;

            }
            if(validate.validateConfirmPassword(password,cfpassword,textInputConfirmPassword)){
                Dangki();
//
                return;

            }
//            String input = "FullName: " + textInputNAME.getEditText().getText().toString();
//            input += "\n";
//            input += "Email " + textInputEmail.getEditText().getText().toString();
//            input += "\n";
//            input += "Password: " + textInputPassword.getEditText().getText().toString();
//            input += "\n";
//            input +="Confirm Password: " + textInputConfirmPassword.getEditText().getText().toString();
//            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }

    }
}
