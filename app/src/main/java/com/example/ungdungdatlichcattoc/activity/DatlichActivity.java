package com.example.ungdungdatlichcattoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ungdungdatlichcattoc.API.ApiHairStylish;
import com.example.ungdungdatlichcattoc.API.ApiOrder;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_rec_btnTime;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_ryc_Stylist;
import com.example.ungdungdatlichcattoc.Adapter.Adapter_text;
import com.example.ungdungdatlichcattoc.Adapter.HairStylishSpinerAdapter;
import com.example.ungdungdatlichcattoc.Interface.ItemClickListener;
import com.example.ungdungdatlichcattoc.Interface.ItemClickListenerRcv;
import com.example.ungdungdatlichcattoc.MainActivity;
import com.example.ungdungdatlichcattoc.R;
import com.example.ungdungdatlichcattoc.model.HairStylish;
import com.example.ungdungdatlichcattoc.model.LoginResponse;
import com.example.ungdungdatlichcattoc.model.OrderResponse;
import com.example.ungdungdatlichcattoc.model.ServiceIDs;
import com.example.ungdungdatlichcattoc.model.TimePickerStylelish;
import com.example.ungdungdatlichcattoc.model.text;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
    TextView tvservice;
    LinearLayout crv_chonService, crv_selectstylish;
    RecyclerView recyclerView_btnTime;
    List<TimePickerStylelish> listTime = new ArrayList<>();
    Adapter_rec_btnTime adapter_rec_btnTime;
    String Dateandhour;
    public String hour;
    RecyclerView rcy_Stylist;
    List<HairStylish> stylishList = new ArrayList<>();
    Adapter_ryc_Stylist adapter_ryc_stylist;
    TextView tv_namestylist;
    String[] listidservice;
    ArrayList<String> listnamesv ;
    int sumprice;
    String idStylish = null;
    LinearLayout calenda;
    TextView tv_datlich_time;
    Date dateOrder;
    EditText edtycthem;
    Button btn_order_hoantat;
    int sttTime;
    final Calendar calendar = Calendar.getInstance();
    ListView lv_nameSV;
    //validate time
    int steporder;
    private String s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30, s31, s32, s33, s34, s35, s36, s37, s38, s39, s40, s41, s42;
    private Date t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42;
    Adapter_text adapter_text;
    List<String> listText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datlich);
        btnHomeBack = findViewById(R.id.btnhomeDatLich);
        hairStylishList = new ArrayList<>();
        crv_chonService = findViewById(R.id.linear_chondichvu);
        tv_namestylist = findViewById(R.id.tv_namestylist);
        crv_selectstylish = findViewById(R.id.crv_selectstylish);
        tvservice = findViewById(R.id.tv_datlich_service);
        calenda = findViewById(R.id.btn_datlich_calendar);
        lv_nameSV = findViewById(R.id.lv_nameSV);
        tv_datlich_time = findViewById(R.id.tv_datlich_time);
        btn_order_hoantat = findViewById(R.id.btn_order_hoantat);
        listnamesv = new ArrayList<>();
        hour = "";
        steporder=0;
        getTimenow();
        listText = new ArrayList<>();
        tv_namestylist.setText("");
        recyclerView_btnTime = findViewById(R.id.rec_btnTime);
//        add_timeBtn();
//        adapter_rec_btnTime = new Adapter_rec_btnTime(listTime, new ItemClickListener() {
//            @Override
//            public void onClickItemTime(String time) {
//                hour = time;
//                //Toast.makeText(getApplicationContext(),hour,Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(DatlichActivity.this, 4);
//        recyclerView_btnTime.setLayoutManager(mLayoutManager);
//        recyclerView_btnTime.setAdapter(adapter_rec_btnTime);


        rcy_Stylist = findViewById(R.id.rec_stylist);

        getdataService();
        intentControl();
        getHairStylishAPI();
        getAdapterHairStylish();
        token();

        if(steporder<2){
            btn_order_hoantat.setEnabled(false);
        }
        adapter_ryc_stylist = new Adapter_ryc_Stylist(stylishList, new ItemClickListenerRcv() {
            @Override
            public void onClickItemTime(String time, String name) {
                idStylish = time;
                tv_namestylist.setText(name);
                if (!tv_datlich_time.getText().toString().isEmpty()) {
                    getFreeTimeHairStylishAPI(idStylish, tv_datlich_time.getText().toString());
                }
            }
        });
        GridLayoutManager mLayoutStylist = new GridLayoutManager(DatlichActivity.this, 1);
        mLayoutStylist.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcy_Stylist.setLayoutManager(mLayoutStylist);
        rcy_Stylist.setAdapter(adapter_ryc_stylist);
        sttTime = 0;
        getTimenow();


        Log.e("Token", "mytoken: " + token());
//        spinnerStylish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                HairStylish hairStylish = hairStylishList.get(i);
//                idStylish = hairStylish.get_id();
//                if (!tv_datlich_time.getText().toString().isEmpty()) {
//                    getFreeTimeHairStylishAPI(idStylish, tv_datlich_time.getText().toString());
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        calenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(tv_datlich_time);
                sttTime += 1;
            }
        });


        btn_order_hoantat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tokencus = token();
                String note = " ! ";
                String date = tv_datlich_time.getText().toString();

                //     Toast.makeText(getApplicationContext(),hour,Toast.LENGTH_SHORT).show();
                Dateandhour = date + " " + hour;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                dateOrder = calendar.getTime();
                if (check(listidservice, sttTime) == true) {


                    try {
                        Date date2 = format.parse(Dateandhour);
                        JSONArray jsonArray = new JSONArray(Arrays.asList(listidservice));
                        Log.d("array", "onClick: " + jsonArray);
                        List<ServiceIDs> stringList = new ArrayList<>();
                        for (int i = 0; i < listidservice.length; i++) {
                            stringList.add(new ServiceIDs(listidservice[i]));
                        }
                        Order(tokencus, jsonArray, idStylish, date2, note, sumprice);
                        sttTime = 0;

                    } catch (Exception e) {
                        //    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }


    private boolean check(String[] listidservice, int date) {
        boolean x = true;
        if (listidservice == null) {
            Toast.makeText(getApplicationContext(), "Anh hãy chọn dịch vụ", Toast.LENGTH_SHORT).show();
            x = false;
        }
        if (date == 0) {
            Toast.makeText(getApplicationContext(), "Anh hãy chọn thời gian", Toast.LENGTH_SHORT).show();
            x = false;
        }
        return x;


    }

    private void showDateTimeDialog(final TextView date_time_in) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                //  datePicker.setMinDate(System.currentTimeMillis()-1000);
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                getFreeTimeHairStylishAPI(idStylish, simpleDateFormat.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        );
        final Calendar cMin = Calendar.getInstance();
        final Calendar cMax = Calendar.getInstance();

        cMin.set(cMin.get(Calendar.YEAR), cMin.get(Calendar.MONTH), cMin.get(Calendar.DAY_OF_MONTH)); // for date 16/Aug/2022
        cMax.set(cMax.get(Calendar.YEAR), cMax.get(Calendar.MONTH), cMax.get(Calendar.DAY_OF_MONTH) + 2); // for date 16/Aug/2022

        datePickerDialog.getDatePicker().setMinDate(cMin.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(cMax.getTimeInMillis());
        datePickerDialog.show();


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
                stylishList.addAll(response.body());

                hairStylishSpinerAdapter = new HairStylishSpinerAdapter(getApplicationContext(), R.layout.layout_item_stylish_spiner_selected, hairStylishList);
                // spinnerStylish.setAdapter(hairStylishSpinerAdapter);
                //rcv
                adapter_ryc_stylist = new Adapter_ryc_Stylist(stylishList, new ItemClickListenerRcv() {
                    @Override
                    public void onClickItemTime(String time, String name) {
                        idStylish = time;
                        tv_namestylist.setText(name);
                        if (!tv_datlich_time.getText().toString().isEmpty()) {
                            getFreeTimeHairStylishAPI(idStylish, tv_datlich_time.getText().toString());
                        }
                    }
                });
                GridLayoutManager mLayoutStylist = new GridLayoutManager(DatlichActivity.this, 1);
                mLayoutStylist.setOrientation(LinearLayoutManager.HORIZONTAL);
                rcy_Stylist.setLayoutManager(mLayoutStylist);
                rcy_Stylist.setAdapter(adapter_ryc_stylist);


            }

            @Override
            public void onFailure(Call<List<HairStylish>> call, Throwable t) {
                //    Toast.makeText(getApplicationContext(), "lỗi rồi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFreeTimeHairStylishAPI(String idSL, String TimeSL) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/hairstylist/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiHairStylish apiHairStylish = retrofit.create(ApiHairStylish.class);
        Call<List<Integer>> callhairstylish = apiHairStylish.getFreeTime(idSL, TimeSL);
        callhairstylish.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {

                add_timeBtn();
                List<Integer> listTimeButton = response.body();
                List<TimePickerStylelish> lstpicktime = new ArrayList<>();
                Log.d("TAG", "onResponse: " + response.body());
                for (int i = 0; i < listTime.size(); i++) {
                    Boolean a = true;
                    for (Integer ina : listTimeButton) {
                        Log.d("TAG", "onResponse: " + listTime.get(i).getId() + "timebuttton" + ina);
                        if (listTime.get(i).getId() == ina) {
                            Log.d("TAG", "onResponse: " + "false");
                            a = false;
                        }
                    }
                    lstpicktime.add(new TimePickerStylelish(listTime.get(i).getId(), listTime.get(i).getHours(), a, listTime.get(i).getTime()));
                }
                adapter_rec_btnTime = new Adapter_rec_btnTime(lstpicktime, new ItemClickListener() {
                    @Override
                    public void onClickItemTime(String time) {
                        hour = time;
                        if(steporder==1){
                            steporder =2;
                            if(steporder==2){
                                btn_order_hoantat.setBackgroundColor(Color.parseColor("#FFCA39"));
                                btn_order_hoantat.setEnabled(true);
                            }
                        }

                            // Toast.makeText(getApplicationContext(),hour,Toast.LENGTH_SHORT).show();
                    }
                });
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(DatlichActivity.this, 4);
                recyclerView_btnTime.setLayoutManager(mLayoutManager);
                recyclerView_btnTime.setAdapter(adapter_rec_btnTime);
                adapter_rec_btnTime.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                //    Toast.makeText(getApplicationContext(), "lỗi rồi", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void getdataService() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            listidservice = bundle.getStringArray("listidservice");
            sumprice = bundle.getInt("sumprice");
            listnamesv = bundle.getStringArrayList("listnameservice");



            if(sumprice>=1){

                steporder=1;
                tvservice.setText("Bạn đã chọn: " + listidservice.length + " dịch Vụ " + "tổng tiền " + String.valueOf(bundle.getInt("sumprice")));
            }


            listText =listnamesv;
            Adapter_text adapter_text = new Adapter_text(this, listText);
            lv_nameSV.setAdapter(adapter_text);

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

    private void Order(String customerId, JSONArray serviceIds, String stylistId, Date time, String note, int sumPrice) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://io.supermeo.com:8000/order/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiOrder apiOrder = retrofit.create(ApiOrder.class);
        Call<OrderResponse> call = apiOrder.order(customerId, serviceIds, stylistId, time, note, sumPrice);
        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DatlichActivity.this, "Đặt Lịch Thành Công!", Toast.LENGTH_SHORT).show();
                    Log.e("TAGmess", "onResponse: " + response.message());
                    finish();
                    Intent intent = new Intent(DatlichActivity.this, DatlichActivity.class);
                    startActivity(intent);
                } else {
                    //   Toast.makeText(DatlichActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.d("go", "onFailure: " + t.toString());
            }
        });
    }

    void add_timeBtn() {
        listTime.clear();
        time();
        listTime.add(new TimePickerStylelish(18, "09:00", false, t18));
        listTime.add(new TimePickerStylelish(19, "09:30", false, t19));
        listTime.add(new TimePickerStylelish(20, "10:00", false, t20));
        listTime.add(new TimePickerStylelish(21, "10:30", false, t21));
        listTime.add(new TimePickerStylelish(22, "11:00", false, t22));
        listTime.add(new TimePickerStylelish(23, "11:30", false, t23));
        listTime.add(new TimePickerStylelish(24, "12:00", false, t24));
        listTime.add(new TimePickerStylelish(25, "12:30", false, t25));
        listTime.add(new TimePickerStylelish(26, "13:00", false, t25));
        listTime.add(new TimePickerStylelish(27, "13:30", false, t27));
        listTime.add(new TimePickerStylelish(28, "14:00", false, t28));
        listTime.add(new TimePickerStylelish(29, "14:30", false, t29));
        listTime.add(new TimePickerStylelish(30, "15:00", false, t30));
        listTime.add(new TimePickerStylelish(31, "15:30", false, t31));
        listTime.add(new TimePickerStylelish(32, "16:00", false, t32));
        listTime.add(new TimePickerStylelish(33, "16:30", false, t33));
        listTime.add(new TimePickerStylelish(34, "17:00", false, t34));
        listTime.add(new TimePickerStylelish(35, "17:30", false, t35));
        listTime.add(new TimePickerStylelish(36, "18:00", false, t36));
        listTime.add(new TimePickerStylelish(37, "18:30", false, t37));
        listTime.add(new TimePickerStylelish(38, "19:00", false, t38));
        listTime.add(new TimePickerStylelish(39, "19:30", false, t39));
        listTime.add(new TimePickerStylelish(40, "20:00", false, t40));
        listTime.add(new TimePickerStylelish(41, "20:30", false, t41));

    }

    void time() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String thisDate = tv_datlich_time.getText().toString();
        s18 = "09:00";
        try {
            String d18 = thisDate + " " + s18;
            t18 = formatter.parse(d18);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s19 = "09:30";
        try {
            String d19 = thisDate + " " + s19;
            t19 = formatter.parse(d19);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s20 = "10:00";
        try {
            String d20 = thisDate + " " + s20;
            t20 = formatter.parse(d20);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s21 = "10:30";
        try {
            String d21 = thisDate + " " + s21;
            t21 = formatter.parse(d21);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s22 = "11:00";
        try {
            String d22 = thisDate + " " + s22;
            t22 = formatter.parse(d22);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s23 = "11:30";
        try {
            String d23 = thisDate + " " + s23;
            t23 = formatter.parse(d23);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s24 = "12:00";
        try {
            String d24 = thisDate + " " + s24;
            t24 = formatter.parse(d24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s25 = "12:30";
        try {
            String d25 = thisDate + " " + s25;
            t25 = formatter.parse(d25);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s26 = "13:00";
        try {
            String d26 = thisDate + " " + s26;
            t26 = formatter.parse(d26);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s27 = "13:30";
        try {
            String d27 = thisDate + " " + s27;
            t27 = formatter.parse(d27);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s28 = "14:00";
        try {
            String d28 = thisDate + " " + s28;
            t28 = formatter.parse(d28);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s29 = "14:30";
        try {
            String d29 = thisDate + " " + s29;
            t29 = formatter.parse(d29);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s30 = "15:00";
        try {
            String d30 = thisDate + " " + s30;
            t30 = formatter.parse(d30);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s31 = "15:30";
        try {
            String d31 = thisDate + " " + s31;
            t31 = formatter.parse(d31);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s32 = "16:00";
        try {
            String d32 = thisDate + " " + s32;
            t32 = formatter.parse(d32);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s33 = "16:30";
        try {
            String d33 = thisDate + " " + s33;
            t33 = formatter.parse(d33);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s34 = "17:00";
        try {
            String d34 = thisDate + " " + s34;
            t34 = formatter.parse(d34);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s35 = "17:30";
        try {
            String d35 = thisDate + " " + s35;
            t35 = formatter.parse(d35);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s36 = "18:00";
        try {
            String d36 = thisDate + " " + s36;
            t36 = formatter.parse(d36);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s37 = "18:30";
        try {
            String d37 = thisDate + " " + s37;
            t37 = formatter.parse(d37);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s38 = "19:00";
        try {
            String d38 = thisDate + " " + s38;
            t38 = formatter.parse(d38);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s39 = "19:30";
        try {
            String d39 = thisDate + " " + s39;
            t39 = formatter.parse(d39);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s40 = "20:00";
        try {
            String d40 = thisDate + " " + s40;
            t40 = formatter.parse(d40);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        s41 = "20:30";
        try {
            String d41 = thisDate + " " + s41;
            t41 = formatter.parse(d41);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void getTimenow() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateformat.format(c.getTime());
        tv_datlich_time.setText(strDate);
        getFreeTimeHairStylishAPI(idStylish, strDate);
        sttTime += 1;
    }

}