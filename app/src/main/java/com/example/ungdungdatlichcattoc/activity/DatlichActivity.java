package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiHairStylish;
import com.example.ungdungdatlichcattoc.API.ApiOrder;
import com.example.ungdungdatlichcattoc.Adapter.HairStylishSpinerAdapter;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;
import com.example.ungdungdatlichcattoc.model.OrderResponse;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

public class DatlichActivity extends AppCompatActivity {
    ImageView btnHomeBack;
    private List<HairStylish> hairStylishList;
    LinearLayout layouthairstylish;
    SharedPreferences prefs;
    String token;
    Spinner spinnerStylish, spnfake;
    HairStylishSpinerAdapter hairStylishSpinerAdapter;
    public TextView tvNameStylish;
    CardView crv_chonService, crv_selectstylish;
    TextView tvservice;
    String[] listidservice;
    int sumprice;
    String idStylish;
    ImageView calenda;
    TextView tv_datlich_time;
    Date dateOrder;
    EditText edtycthem;
    Button btn_order_hoantat;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datlich);
        btnHomeBack = findViewById(R.id.btnhomeDatLich);
        hairStylishList = new ArrayList<>();
        spinnerStylish = findViewById(R.id.spinerstylish);
        crv_chonService = findViewById(R.id.datlich_crv_chondichvu);
        crv_selectstylish = findViewById(R.id.crv_selectstylish);
        tvservice = findViewById(R.id.tv_datlich_service);
        calenda = findViewById(R.id.btn_datlich_calendar);
        tv_datlich_time = findViewById(R.id.tv_datlich_time);
        edtycthem = findViewById(R.id.datlich_edt_yeucauthem);
        btn_order_hoantat = findViewById(R.id.btn_order_hoantat);
        getdataService();
        intentControl();
        getHairStylishAPI();
        getAdapterHairStylish();
        token();
        Log.e("Token", "mytoken: " + token());
        spinnerStylish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HairStylish hairStylish = hairStylishList.get(i);
                idStylish = hairStylish.get_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        calenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(tv_datlich_time);
            }
        });
        btn_order_hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tokencus = token();
                String note = edtycthem.getText().toString() + " ! ";
                String date = tv_datlich_time.getText().toString();
                dateOrder = calendar.getTime();
                JSONArray jsonArray = new JSONArray(Arrays.asList(listidservice));
                Order(tokencus, jsonArray, idStylish, dateOrder, note, sumprice);

            }
        });


    }

    private void showDateTimeDialog(final TextView date_time_in) {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(DatlichActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };

        new DatePickerDialog(DatlichActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    //fixbug

    private String token() {
        prefs = getSharedPreferences("HAIR", MODE_PRIVATE);
        token = prefs.getString("token", toString());
        return token;
    }

    void getAdapterHairStylish() {


    }

    void getHairStylishAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/hairstylist/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiHairStylish apiHairStylish = retrofit.create(ApiHairStylish.class);
        Call<List<HairStylish>> callhairstylish = apiHairStylish.getHairStylish();
        callhairstylish.enqueue(new Callback<List<HairStylish>>() {
            @Override
            public void onResponse(Call<List<HairStylish>> call, Response<List<HairStylish>> response) {
                hairStylishList.addAll(response.body());
                hairStylishSpinerAdapter = new HairStylishSpinerAdapter(getApplicationContext(), R.layout.layout_item_stylish_spiner_selected, hairStylishList);
                spinnerStylish.setAdapter(hairStylishSpinerAdapter);
            }

            @Override
            public void onFailure(Call<List<HairStylish>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "lỗi rồi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void getdataService() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            listidservice = bundle.getStringArray("listidservice");
            sumprice = bundle.getInt("sumprice");
            tvservice.setText("Bạn đã chọn: " + listidservice.length + "dịch Vụ " + "tổng tiền " + String.valueOf(bundle.getInt("sumprice")));
        }
    }


    void intentControl() {
        crv_chonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChonDichVuAcitivty.class);
                startActivity(intent);
            }
        });
        btnHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);
            }
        });

    }

    private void Order(String customerId, JSONArray serviceIds, String stylistId,  Date time, String note, int sumPrice) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/order/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiOrder apiOrder = retrofit.create(ApiOrder.class);
        Call<OrderResponse> call = apiOrder.order(customerId, serviceIds, stylistId,  time, note, sumPrice);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DatlichActivity.this, "Đặt Lịch Thành Công", Toast.LENGTH_SHORT).show();
                    Log.e("TAGmess", "onResponse: "+response.message());
                } else {
                    Toast.makeText(DatlichActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.d("go", "onFailure: " + t.toString());
            }
        });
    }


}