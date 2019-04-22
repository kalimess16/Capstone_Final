package com.example.nguye.capston1_dtu.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<City> mCityList;
    private CityAdapter mAdapter;
    ArrayAdapter<City> cityArrayAdapter;
    String[] categories = {"Tất cả", "Đà Nẵng", "Quảng Nam"};

    private TextView textViewDK, btnDangNhap;

    ArrayList<City> listCity;
    private String name;
    private Spinner SpinnerCity, myListView;
    private Button btnDangKi;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebase;
    private TextInputLayout textInputEmail, textInputNAME, textInputPassword, textInputConfirmPassword;

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

    private void DangkiRealtime(String email, String city, String password, String school) {
       /* String username = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();
        String cfpassword = editTextConfirm.getText().toString().trim();*/
        User newUser = new User(email, city, password, school);
        mDatabase.child("UserID").child(city).child(school).push().setValue(newUser);
    }

    public void Anhxa() {
        btnDangKi = findViewById(R.id.btnDangki);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        textInputNAME = findViewById(R.id.textInput_NAME);
        textInputEmail = findViewById(R.id.textInput_Email);
        textInputPassword = findViewById(R.id.textInput_Password);
        textInputConfirmPassword = findViewById(R.id.textInputConfirm_Password);
        SpinnerCity = findViewById(R.id.spinnerCity);
        myListView = findViewById(R.id.myListView);
//        Log.e("abc",SpinnerCity.toString());
    }

    /**
     * chọn dữ liệu từ spinner
     */
    private void getSelectedCategoryData(int categoryID) {
//arraylist to hold selected cosmic bodies
        ArrayList<City> citySchool = new ArrayList<>();
        if (categoryID == 0) {
            cityArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getSchool());
        } else {
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

    /**
     * Load dữ liệu lên 2 Spinner
     */
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

    /**
     * List các trường
     */
    private ArrayList<City> getSchool() {
        ArrayList<City> data = new ArrayList<>();
        data.clear();
        data.add(new City("THPT Thái Phiên", 1));
        data.add(new City("THPT Diên Hồng", 1));
        data.add(new City("THPT Trần Phú", 1));
        data.add(new City("THPT Nguyễn Hiền", 1));
        data.add(new City("THPT Phan Châu Trinh", 1));
        data.add(new City("THPT SkyLine", 1));
        data.add(new City("THPT Thanh Khê", 1));
        data.add(new City("THPT Ngô Quyền", 1));
        data.add(new City("THPT Quang Trung", 1));
        data.add(new City("THPT Hoàng Hoa Thám", 1));
        data.add(new City("THPT Chuyên Lê Quý Đôn", 1));
        data.add(new City("THPT Tôn Thất Tùng", 1));
        data.add(new City("THPT Ngũ Hành Sơn", 1));
        data.add(new City("PTDL Hermann Gmeiner", 1));
        data.add(new City("THPT TT Khai Trí", 1));
        data.add(new City("THPT Nguyễn Trãi", 1));
        data.add(new City("THPT Nguyễn Thượng Hiền", 1));
        data.add(new City("THPT Ông Ích Khiêm", 1));
        data.add(new City("THPT Phan Thành Tài", 1));
        data.add(new City("THPT Phạm Phú Thứ", 1));
        data.add(new City("THPT Hòa Vang", 1));
        data.add(new City("THPT Hiển Nhân", 1));
        data.add(new City("THPT Cẩm Lệ", 1));

        data.add(new City("THPT Lê Quý Đôn", 2));
        data.add(new City("THPT Trần Cao Vân", 2));
        data.add(new City("THPT Phan Bội Châu", 2));
        data.add(new City("THPT Duy Tân", 2));
        data.add(new City("THPT Tư thục Hà Huy Tập", 2));
        data.add(new City("THPT Chuyên Nguyễn Bỉnh Khiêm", 2));
        data.add(new City("THPT Trần Hưng Đạo", 2));
        data.add(new City("THPT Nguyễn Trãi", 2));
        data.add(new City("PTDT Nội trú tỉnh Quảng Nam", 2));
        data.add(new City("THPT Chuyên Lê Thánh Tông", 2));
        data.add(new City("THPT Trần Quý Cáp", 2));
        data.add(new City("THPT Sào Nam", 2));
        data.add(new City("THPT Lê Hồng Phong", 2));
        data.add(new City("THPT Nguyễn Hiền", 2));
        data.add(new City("PT nhiều cấp học Quảng Đông", 2));
        data.add(new City("PT nhiều cấp học Hoàng Sa", 2));
        data.add(new City("THPT Nguyễn Duy Hiệu", 2));
        data.add(new City("THPT Hoàng Diệu", 2));
        data.add(new City("THPT Phạm Phú Thứ", 2));
        data.add(new City("THPT Lương Thế Vinh", 2));
        data.add(new City("THPT Lương Thúc Kỳ", 2));
        data.add(new City("THPT Đỗ Đăng Tuyển", 2));
        data.add(new City("THPT Huỳnh Ngọc Huệ", 2));
        data.add(new City("THPT Chu Văn An", 2));
        data.add(new City("THPT Nguyễn Văn Cừ", 2));
        data.add(new City("THPT Trần Đại Nghĩa", 2));
        data.add(new City("THPT Quế Sơn", 2));
        data.add(new City("THPT TT Phạm Văn Đồng", 2));
        data.add(new City("THPT Hiệp Đức", 2));
        data.add(new City("THPT Trần Phú", 2));
        return data;
    }

    /**
     * Load các trường vào Spinner
     */
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
    private void Dangki() {
        final String email = textInputEmail.getEditText().getText().toString().trim();
        final String password = textInputPassword.getEditText().getText().toString().trim();
        final String city = SpinnerCity.getSelectedItem().toString().trim();
        final String school = myListView.getSelectedItem().toString().trim();
        String email1 = textInputEmail.getEditText().getText().toString().trim();
        String password1 = textInputPassword.getEditText().getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "ĐĂNG KÍ TÀI KHOẢN THÀNH CÔNG, VÀO EMAIL CỦA BẠN ĐỂ XÁC NHẬN ĐĂNG KÍ",
                                        Toast.LENGTH_LONG).show();
                                btnDangNhap.setText("Trờ về đăng nhập");
                                DangkiRealtime(email, city, password, school);
                            } else {
                                Toast.makeText(Register.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register.this, task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * thêm cái jj
     */
    @Override
    public void onClick(View v) {
        Validate validate = new Validate();

//        String username = textInputNAME.getEditText().getText().toString().trim();
        String email = textInputEmail.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();
        String cfpassword = textInputConfirmPassword.getEditText().getText().toString().trim();
        if (isOnline()) {
            if (v == btnDangKi) {
                if (validate.validateEmail(email, textInputEmail)
                        && validate.validatePassword(password, textInputPassword)
                        && validate.validateConfirmPassword(password, cfpassword, textInputConfirmPassword)) {
                    Dangki();
                    return;
                }
                if (validate.validatePassword(password, textInputPassword)) {
                    Dangki();
                    return;

                }
                if (validate.validateConfirmPassword(password, cfpassword, textInputConfirmPassword)) {
                    Dangki();
                    return;
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Vui lòng kết nối Internet", Toast.LENGTH_LONG).show();
        }

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

}
