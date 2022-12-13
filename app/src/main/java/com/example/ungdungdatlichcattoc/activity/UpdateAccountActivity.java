package com.example.ungdungdatlichcattoc.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ungdungdatlichcattoc.API.ApiCustomer;
import com.example.ungdungdatlichcattoc.API.ApiService;
import com.example.ungdungdatlichcattoc.API.ApiUploadImage;
import com.example.ungdungdatlichcattoc.Prefab.RealPathUtil;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.CusstomerInfo;
import com.example.ungdungdatlichcattoc.model.ProfileCus;
import com.example.ungdungdatlichcattoc.model.RegisterResponse;
import com.example.ungdungdatlichcattoc.model.ResponeAvatandNameCustomer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateAccountActivity extends AppCompatActivity {

    private static final int MY_REQUSET_CODE = 10;
    EditText edt_update_account_username, edt_update_account_birthday, edt_update_account_Adress;
    MaterialButton btn_updateaccount_Update;
    TextView txtluuthongtin;
    private String nameUser, birThday, Phone, token, andress;
    SharedPreferences prefs;
    final Calendar calendar = Calendar.getInstance();
    ShapeableImageView img_update_avt;
    private Uri mUri;
    private ProgressDialog mprogressDialog;

    ImageView img_update_back;
    //opengallary
    ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e("TAG", "onActivityResult: ");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri =uri;

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            img_update_avt.setImageBitmap(bitmap);
                            Uploadavtar();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        edt_update_account_username = findViewById(R.id.edt_update_account_username);
        edt_update_account_birthday = findViewById(R.id.edt_update_account_birthday);
        edt_update_account_Adress = findViewById(R.id.edt_update_account_Adress);
        txtluuthongtin = findViewById(R.id.tvLuuthongtin);
        img_update_avt = findViewById(R.id.img_update_avt);
        mprogressDialog = new ProgressDialog(this);
        img_update_back= findViewById(R.id.img_update_back);
        mprogressDialog.setMessage("Vui Lòng Chờ");
        img_update_avt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermision();
            }
        });
        //btn_updateaccount_Update = findViewById(R.id.btn_updateaccount_Update);

        img_update_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateAccountActivity.this,AccoutActivity.class);
                startActivity(intent);
                finish();
            }
        });
        getUserinfo();
        Profile(token);
        edt_update_account_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog((EditText) edt_update_account_birthday);
            }
        });
        txtluuthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date1 = format.parse(edt_update_account_birthday.getText().toString());
                    date1 = calendar.getTime();
                    System.out.println(date1);
                    Update(token, edt_update_account_username.getText().toString(), date1, edt_update_account_Adress.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }


    void Uploadavtar(){
        if(mUri != null){
            callApiUpdateImage();
        }
    }
    void callApiUpdateImage(){
        mprogressDialog.show();

        String mtoken = token;

        RequestBody requestBodyToken = RequestBody.create(MediaType.parse("multipart/form-data"),mtoken);

        String strRealPath = RealPathUtil.getRealPath(this,mUri);
        Log.e("uploadimg", "callApiUpdateImage: "+strRealPath);

        File file = new File(strRealPath);
        RequestBody requestBodyAvatar = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part mulipartbodyavt = MultipartBody.Part.createFormData("image",file.getName(),requestBodyAvatar);


        ApiUploadImage.apiUploadImage.uploadavatar(requestBodyToken,mulipartbodyavt).enqueue(new Callback<CusstomerInfo>() {
            @Override
            public void onResponse(Call<CusstomerInfo> call, Response<CusstomerInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mprogressDialog.dismiss();
                    CusstomerInfo responeAvatandNameCustomer = response.body();
                    if(responeAvatandNameCustomer != null){
                        //   img_update_avt
                        mprogressDialog.dismiss();
                    //    Toast.makeText(getApplicationContext(),"Thành Công",Toast.LENGTH_SHORT).show();
                        Profile(token);
                     //   Log.e("TAGimg", "onResponse: "+responeAvatandNameCustomer.getImage() );
                      //  Log.e("TAGimg", "onResponse: "+  response.body().toString());
                  //      Glide.with(UpdateAccountActivity.this).load("http://io.supermeo.com:8000/"+responeAvatandNameCustomer.getImage()).into(img_update_avt);
                }

                }
            }

            @Override
            public void onFailure(Call<CusstomerInfo> call, Throwable t) {
               mprogressDialog.dismiss();
                Log.e("TAG", "onFailure: "+t.getMessage() );
            }
        });
    }
    void getUserinfo() {
        prefs = getSharedPreferences("HAIR", MODE_PRIVATE);
        token = prefs.getString("token", token);

    }

    private void Profile(String customerId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCustomer apiCustomer = retrofit.create(ApiCustomer.class);
        Call<ProfileCus> call = apiCustomer.Getprofile(customerId);
        call.enqueue(new Callback<ProfileCus>() {
            @Override
            public void onResponse(Call<ProfileCus> call, Response<ProfileCus> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProfileCus profileCus = response.body();
                    nameUser = profileCus.getNameUser();
                    birThday = profileCus.getBirthOfYear();
                    Phone = profileCus.getPhone();
                    andress = profileCus.getAddress();
                    Glide.with(UpdateAccountActivity.this).load("http://io.supermeo.com:8000/"+profileCus.getImage()).into(img_update_avt);

                    edt_update_account_username.setText(nameUser);
                    edt_update_account_Adress.setText(andress);
                    SharedPreferences.Editor editor = getSharedPreferences("HAIR", MODE_PRIVATE).edit();
                    editor.putString("nameUser",nameUser);
                    editor.putString("imageAvatar",profileCus.getImage());
                    editor.apply();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = format.parse(birThday);

                        edt_update_account_birthday.setText(format.format(date));
                    } catch (Exception e) {
                        //   Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Log.e("loi", "onResponse: looix");
                }
            }

            @Override
            public void onFailure(Call<ProfileCus> call, Throwable t) {
                Log.e("loi", "onResponse: looix " + t.getMessage());
            }
        });
    }

    ///chon anh
    void onClickRequestPermision() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            OpenGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            OpenGallery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUSET_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUSET_CODE){
            if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                OpenGallery();
            }
        }
    }

    void OpenGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Chọn Hình Ảnh"));
       // startActivityForResult();

    }

    //end chon anh
    private void showDateTimeDialog(final EditText date_time_in) {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new DatePickerDialog(UpdateAccountActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void Update(String token, String edt_update_account_username, Date edt_update_account_birthday, String edt_update_account_Adress) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/customer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCustomer api = retrofit.create(ApiCustomer.class);
        Call<CusstomerInfo> call = api.UpdateCustomer(token, edt_update_account_username, edt_update_account_Adress, edt_update_account_birthday);
        call.enqueue(new Callback<CusstomerInfo>() {
            @Override
            public void onResponse(Call<CusstomerInfo> call, Response<CusstomerInfo> response) {
                if (response.isSuccessful()) {
                    Profile(token);
                    Toast.makeText(getApplicationContext(), "Dã Thay đổi thành công", Toast.LENGTH_SHORT).show();
                    finishAndRemoveTask();
                    Intent intent = new Intent(UpdateAccountActivity.this, AccoutActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CusstomerInfo> call, Throwable t) {

            }
        });
    }

}