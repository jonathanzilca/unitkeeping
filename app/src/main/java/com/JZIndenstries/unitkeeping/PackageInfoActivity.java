package com.JZIndenstries.unitkeeping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
//import android.widget.Toast;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PackageInfoActivity extends AppCompatActivity {
    EditText inspectorExpiration, inspectorName, packageWeight, segmanExpiration,
            packageParachuteSerialNumber, packageParachuteOpenerSerialNumber,
            packageReleaserInspector, packageInspector, releaserExpirationDate,
            parachuteExpiration;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;
    private DatePickerDialog.OnDateSetListener mDateSetListener3;
    private DatePickerDialog.OnDateSetListener mDateSetListener4;
    Spinner spinner1,spinner, spinner2;
    Switch switch1;
    int[] packageParachuteSerialNumberHeavy;
    EditText[] serialNum;
    EditText[] expirationDate;
    private  PackageModel packageModel;
    private Button approve, delete;
    private String PackageID = "";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_info);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000"))); // removing the support bar
        getWindow().setStatusBarColor(Color.parseColor("#000000"));
        Configuration configuration = getResources().getConfiguration();
        configuration.setLayoutDirection(new Locale("en"));
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        packageModel = getIntent().getParcelableExtra("PackageInfo");
        PackageID = getIntent().getStringExtra("PackageId");
        packageModel.setPackageExpirationDate(getIntent().getStringExtra("PackageExpirationDate"));
        packageModel.setPackageReleaserExpirationDate(getIntent().getStringExtra("PackageReleaserExpirationDate"));
        packageModel.setPackageParachuteOpenerExpirationDate(getIntent().getStringExtra("PackageParachuteOpenerExpirationDate"));
        packageModel.setPackageParachuteExpirationDate(getIntent().getStringExtra("PackageParachuteExpirationDate"));



        Log.d("packageModel", packageModel.toString());


        inspectorName = findViewById(R.id.inspectorName);
        inspectorExpiration = findViewById(R.id.inspectorExpiration);
        packageParachuteOpenerSerialNumber = findViewById(R.id.segmanSerialNumber);
        packageParachuteSerialNumber = findViewById(R.id.parachuteSerialNumber);
        packageWeight = findViewById(R.id.weight);
        packageReleaserInspector = findViewById(R.id.releaserInspector);
        packageInspector = findViewById(R.id.inspectorName);
        releaserExpirationDate = findViewById(R.id.releaserExpirationDate);
        parachuteExpiration = findViewById(R.id.parachuteExpiration);
        segmanExpiration = findViewById(R.id.segmanExpiration);
        inspectorExpiration = findViewById(R.id.inspectorExpiration);

        approve = findViewById(R.id.approve);
        delete = findViewById(R.id.delete);


        inspectorName.setVisibility(View.GONE);
        inspectorExpiration.setVisibility(View.GONE);

        packageParachuteOpenerSerialNumber.setText(packageModel.packageParachuteOpenerSerialNumber + "");
        packageParachuteSerialNumber.setText("" + packageModel.getPackageParachuteSerialNumber());
        packageWeight.setText("" + packageModel.getPackageWeight());
        packageReleaserInspector.setText("" + packageModel.getPackageReleaserInspector());
        packageInspector.setText("" + packageModel.getPackageInspector());
        inspectorExpiration.setText("" + packageModel.getPackageExpirationDate());
        segmanExpiration.setText("" + packageModel.getPackageParachuteOpenerExpirationDate());
        parachuteExpiration.setText("" + packageModel.getPackageParachuteExpirationDate());
        releaserExpirationDate.setText("" + packageModel.getPackageReleaserExpirationDate());



        packageWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double parachuteAmountMid = 0;
                int weight = Integer.parseInt("0" + editable);
                parachuteAmountMid =  ( (weight + 341.2)/716.03);
                int parachuteAmount = (int) parachuteAmountMid;
                Log.d("parachuteAmount", "parachuteAmount: " + parachuteAmount + "  --- " + "intermediate: " + parachuteAmountMid);
                if (weight > 1090) {
                    packageParachuteOpenerSerialNumber.setVisibility(View.GONE);
                    segmanExpiration.setVisibility(View.GONE);
                    parachuteAmountFun(parachuteAmount);

                }

            }
        });

        //releaserExpirationDate.setText(dateToStringFormat(packageModel.getPackageReleaserExpirationDate()));
        String[] packageType = {"א22", "טריוול", "כלוב", "פלציף","רובד 8 רגל",
                "רובד 12 רגל", "רובד 16 רגל", "דו - שלבי (דוש)", "מנוהגת"};
        String[] releaserType = {"FXC", "ללא מנתק", "5000", "M1",
                "M2"};
        String[] openingStrap = {"3ft", "8ft", "20ft",
                "40ft", "60ft"};

        spinner = (Spinner) findViewById(R.id.packageType);
        ArrayAdapter<String> spinnerPackageType = new ArrayAdapter<String>(this, R.layout.my_selected_is_showed, packageType);
        spinnerPackageType.setDropDownViewResource(R.layout.my_selected_item); // The drop down view
        spinner.setAdapter(spinnerPackageType);
        String t = packageModel.getPackageType();
        int packageTypePosition = 0;
        switch (t) {
            case "א22":
                packageTypePosition = 0;
                break;
            case "טריוול":
                packageTypePosition = 1;
                break;
            case "כלוב":
                packageTypePosition = 2;
                break;
            case "פלציף":
                packageTypePosition = 3;
                break;
            case "רובד 8 רגל":
                packageTypePosition = 4;
                break;
            case "רובד 12 רגל":
                packageTypePosition = 5;
                break;
            case "רובד 16 רגל":
                packageTypePosition = 6;
                break;
            case "דו - שלבי (דוש)":
                packageTypePosition = 7;
                break;
            case "מנוהגת":
                packageTypePosition = 8;
                break;
        }
        spinner.setSelection(packageTypePosition);

        spinner1 = (Spinner) findViewById(R.id.releaserType);
        ArrayAdapter<String> spinnerReleaserType = new ArrayAdapter<String>(this, R.layout.my_selected_is_showed, releaserType);
        spinnerReleaserType.setDropDownViewResource(R.layout.my_selected_item); // The drop down view
        spinner1.setAdapter(spinnerReleaserType);
        String s = packageModel.getPackageReleaser();
        int packageReleaserPosition = 0;
        switch (s) {
            case "FXC":
                packageReleaserPosition = 0;
                break;
            case "ללא מנתק":
                packageReleaserPosition = 1;
                break;
            case "5000":
                packageReleaserPosition = 2;
                break;
            case "M1":
                packageReleaserPosition = 3;
                break;
            case "M2":
                packageReleaserPosition = 4;
                break;
        }
        spinner1.setSelection(packageReleaserPosition);


        spinner2 = (Spinner) findViewById(R.id.openingStrap);
        ArrayAdapter<String> spinnerOpeningStrap = new ArrayAdapter<String>(this, R.layout.my_selected_is_showed, openingStrap);
        spinnerOpeningStrap.setDropDownViewResource(R.layout.my_selected_item); // The drop down view
        spinner2.setAdapter(spinnerOpeningStrap);
        int j = packageModel.getPackageOpeningStrap();
        int packageOpeningStrapPosition = 0;
        switch (j) {
            case 3:
                packageOpeningStrapPosition = 0;
                break;
            case 8:
                packageOpeningStrapPosition = 1;
                break;
            case 20:
                packageOpeningStrapPosition = 2;
                break;
            case 40:
                packageOpeningStrapPosition = 3;
                break;
            case 60:
                packageOpeningStrapPosition = 4;
                break;
        }
        spinner2.setSelection(packageOpeningStrapPosition);

        switch1 = findViewById(R.id.switch1);
        switch1.setChecked(packageModel.isPackageApproved());
        boolean b = packageModel.isPackageApproved();
        inspectorName.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
        inspectorExpiration.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                inspectorName.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
                inspectorExpiration.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
                packageModel.setPackageApproved(b);
            }
        });

        releaserExpirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PackageInfoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        parachuteExpiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PackageInfoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        segmanExpiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PackageInfoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        inspectorExpiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PackageInfoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener3,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("mDateSetListener", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                releaserExpirationDate.setText(date);
            }
        };

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("mDateSetListener", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                parachuteExpiration.setText(date);
            }
        };

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("mDateSetListener", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                segmanExpiration.setText(date);
//                inspectorExpiration.setText(date);
            }
        };

        mDateSetListener3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("mDateSetListener", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                inspectorExpiration.setText(date);
            }
        };

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setters();
                if (!isPackageWeightApproved(packageModel)){
                    Toast.makeText(PackageInfoActivity.this, "משקל שגוי", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!isPackageReleaserApproved(packageModel)){
                    Toast.makeText(PackageInfoActivity.this, "מנתק אינו מתאים לסוג מארז זה", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (!isPackageOpeningStrapApproved(packageModel)){
                    Toast.makeText(PackageInfoActivity.this, "אורך רצועת פתיחה שגוי", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PackageInfoActivity.this);
                    builder.setMessage("האם ברצונך לעדכן את המידע על המארז?").setPositiveButton("עדכן", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            DataBaseRef dataBaseRef = new DataBaseRef(PackageInfoActivity.this);
                            String pathId;
                            if (PackageID.contains("R")){
                                pathId = "PackagesRightSide";
                            }else{
                                pathId = "PackagesLeftSide";
                            }
                            dataBaseRef.update(packageModel,PackageID,pathId);


                            Intent intent = new Intent(PackageInfoActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(PackageInfoActivity.this, "המידע עודכן בהצלחה", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("בטל", null);
                    AlertDialog alert = builder.create();
                    alert.show();

                }catch (Exception e){
                    Toast.makeText(PackageInfoActivity.this, "נא למלא את פרטי המצנח", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PackageInfoActivity.this);
                builder.setMessage("האם ברצונך למחוק את המידע על המארז?").setPositiveButton("כן", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        packageModel.setPackageWeight(0);
                        packageModel.setPackageParachuteSerialNumber(0);
                        packageModel.setPackageParachuteOpenerSerialNumber(0);
                        packageModel.setPackageApproved(false);
                        packageModel.setPackageInspector("");
                        packageModel.setPackageExpirationDate("");
                        packageModel.setPackageReleaser("FXC");
                        packageModel.setPackageReleaserInspector("");
                        packageModel.setPackageReleaserExpirationDate("");
                        packageModel.setPackageParachuteOpenerExpirationDate("");
                        packageModel.setPackageParachuteExpirationDate("");
                        packageModel.setPackageType("א22");
                        packageModel.setPackageOpeningStrap(8);

                        DataBaseRef dataBaseRef = new DataBaseRef(PackageInfoActivity.this);
                        String pathId;
                        if (PackageID.contains("R")){
                            pathId = "PackagesRightSide";
                        }else{
                            pathId = "PackagesLeftSide";
                        }
                        dataBaseRef.update(packageModel,PackageID,pathId);

                        Intent intent = new Intent(PackageInfoActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(PackageInfoActivity.this, "המארז נמחק", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("בטל", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private void setters(){
        String packageType = spinner.getSelectedItem().toString();
        packageModel.setPackageType(packageType);
        String releaserType = spinner1.getSelectedItem().toString();
        packageModel.setPackageReleaser(releaserType);
        String openingStrap = spinner2.getSelectedItem().toString().replace("ft" , "");
        packageModel.setPackageOpeningStrap(Integer.parseInt(openingStrap));
        //integers
        packageModel.setPackageWeight(Integer.parseInt(packageWeight.getText().toString()));
        packageModel.setPackageParachuteSerialNumber(Integer.parseInt(packageParachuteSerialNumber.getText().toString()));
        packageModel.setPackageParachuteOpenerSerialNumber(Integer.parseInt(packageParachuteOpenerSerialNumber.getText().toString()));
        // Strings
        packageModel.setPackageReleaserInspector(packageReleaserInspector.getText().toString());
        packageModel.setPackageInspector(packageInspector.getText().toString());
        packageModel.setPackageExpirationDate(inspectorExpiration.getText().toString());
        packageModel.setPackageParachuteExpirationDate(parachuteExpiration.getText().toString());
        packageModel.setPackageReleaserExpirationDate(releaserExpirationDate.getText().toString());
        packageModel.setPackageParachuteOpenerExpirationDate(segmanExpiration.getText().toString());
        if(packageParachuteSerialNumberHeavy != null){
            String Print = "";
            ArrayList<String> expirationDateString = new ArrayList<>();
            ArrayList<Integer> serialNumString = new ArrayList<>();
            String BoardInspector = new String();
            String BoardExperation = new String();

            for (int i = 0 ; i < serialNum.length-1; i++ ){
                expirationDateString.add(expirationDate[i].getText().toString().trim());
                try{
                    serialNumString.add(Integer.parseInt(serialNum[i].getText().toString().trim()));
                    Print = Print + expirationDateString.get(i) + " " + serialNumString.get(i) + " -";
                }
                catch (Exception e){
                    Toast.makeText(this, "נא למלא את פרטי המנצחים", Toast.LENGTH_SHORT).show();
                    serialNum[i].setBackground(ContextCompat.getDrawable(this, R.drawable.missing));
                    expirationDate[i].setBackground(ContextCompat.getDrawable(this, R.drawable.missing));
                }


            }
            packageModel.setPackageParachuteSerialNumberHeavy(serialNumString);
            packageModel.setPackageParachuteExpirationDateHeavy(expirationDateString);
        }
    }

    public boolean isPackageWeightApproved(PackageModel packageModel){
        switch (packageModel.getPackageType()) {
            case "כלוב":
                if (packageModel.getPackageWeight() > 339
                        && packageModel.getPackageWeight() < 1041) {
                    return true;
                }else{ return false;}
            case "א22":
            case "טריוול":
            case "מנוהגות" :
                if (packageModel.getPackageWeight() > 280
                        && packageModel.getPackageWeight() < 1041){
                    return true;
                }else{ return false;}
            case "רובד 8 רגל" :
            case "רובד 12 רגל" :
            case "רובד 16 רגל" :
                if( packageModel.getPackageWeight() > 1040){
                    return true;
                }else{ return false;}
        }
        return false;
    }


    public boolean isPackageOpeningStrapApproved(PackageModel packageModel){
        switch (packageModel.getPackageOpeningStrap()) {
            case 3 :
            case 8 :
                if (packageModel.getPackageType().contains("כלוב") || packageModel.getPackageType().contains("טריוול")
                        || packageModel.getPackageType().contains("א22")){
                    return true;
                }else{ return false;}
            case 20 :
            case 40 :
            case 60 :
                if (packageModel.getPackageType().contains("רובד") || packageModel.getPackageType().contains("דו")){
                    return true;
                }else{ return false;}
        }
        return false;
    }

    public boolean isPackageReleaserApproved(PackageModel packageModel){
        switch (packageModel.getPackageReleaser()) {
            case "FXC":
            case "ללא מנתק":
                if (packageModel.getPackageType().contains("כלוב") && packageModel.getPackageWeight() > 280
                    && packageModel.getPackageWeight() < 1041|| packageModel.getPackageType().contains("פל") && packageModel.getPackageWeight() > 280
                        && packageModel.getPackageWeight() < 1041||
                        packageModel.getPackageType().contains("22") && packageModel.getPackageWeight() > 280
                                && packageModel.getPackageWeight() < 1041||
                        packageModel.getPackageType().contains("טריוול")&& packageModel.getPackageWeight() > 280
                                && packageModel.getPackageWeight() < 1041){
                    return true;
                }else{ return false;}
            case "5000":
                if (packageModel.getPackageType().contains("כלוב") && packageModel.getPackageWeight() > 650
                    && packageModel.getPackageWeight() < 1041|| packageModel.getPackageType().contains("פל") && packageModel.getPackageWeight() > 650
                        && packageModel.getPackageWeight() < 1041||
                        packageModel.getPackageType().contains("22") && packageModel.getPackageWeight() > 650
                                && packageModel.getPackageWeight() < 1041 ||
                        packageModel.getPackageType().contains("טריוול") && packageModel.getPackageWeight() > 650
                                && packageModel.getPackageWeight() < 1041){
                    return true;
                }else{ return false;}
            case "M1":
                if (packageModel.getPackageType().contains("רובד") && packageModel.getPackageWeight() > 91
                        && packageModel.getPackageWeight() < 6818){
                    return true;
                }else{ return false;}
            case "M2":
                if (packageModel.getPackageType().contains("רובד") && packageModel.getPackageWeight() > 2727
                        && packageModel.getPackageWeight() < 19090){
                    return true;
                }else{ return false;}
        }
        return false;
    }

    private boolean openingStrapCheck(int openingStrap){
        if(openingStrap == 20 || openingStrap == 40 || openingStrap == 60 ){
            return true;
        }
        return false;
    }
    private boolean openingStrapCheckLight(int openingStrap){
        if(openingStrap == 8 || openingStrap == 3){
            return true;
        }
        return false;
    }

    private void parachuteAmountFun(int loop){



        Log.d("parachuteAmountFun", "parachuteAmountFun: " + loop);
        DatePickerDialog.OnDateSetListener[] mDateSetListener = new DatePickerDialog.OnDateSetListener[loop];
        packageParachuteSerialNumberHeavy = new int[loop];
        serialNum = new EditText[loop];
        expirationDate = new EditText[loop];

        Drawable originalDrawable = releaserExpirationDate.getBackground();

        LinearLayout layoutParachute = (LinearLayout) findViewById(R.id.layoutParachute);
        LinearLayout layoutDate = (LinearLayout) findViewById(R.id.layoutParachuteDate);

        LinearLayout.LayoutParams mRparams = new LinearLayout.LayoutParams(448, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams mRparams1 = new LinearLayout.LayoutParams(448, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < loop-1 ;i++){

            serialNum[i] = new EditText(getApplicationContext());
                mRparams.setMargins(10,20,10,0);
                serialNum[i].setPadding(0,25,30,25);
                serialNum[i].setLayoutParams(mRparams);
                serialNum[i].setTextColor(Color.WHITE);
                serialNum[i].setInputType(InputType.TYPE_CLASS_NUMBER);
                serialNum[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_numbers, 0);


                expirationDate[i] = new EditText(getApplicationContext());
                mRparams1.setMargins(10,20,14,0);
                expirationDate[i].setPadding(0,25,27,25);
                expirationDate[i].setLayoutParams(mRparams1);
                expirationDate[i].setTextColor(Color.WHITE);
                expirationDate[i].setInputType(InputType.TYPE_CLASS_NUMBER);
                expirationDate[i].setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_btter_calander, 0);
                layoutParachute.addView(serialNum[i]);
                layoutDate.addView(expirationDate[i]);

            expirationDate[i].setHint("תאריך אריזה");

            expirationDate[i].setBackgroundDrawable(originalDrawable);
            expirationDate[i].setHintTextColor(Color.WHITE);
            expirationDate[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            expirationDate[i].setFocusable(false);
            int finalI = i;
            expirationDate[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(
                                PackageInfoActivity.this,
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                mDateSetListener[finalI],
                                year, month, day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });

                mDateSetListener[i] = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;

                        String Date = day + "/" + month + "/" + year;
                        expirationDate[finalI].setText(Date);
                    }
                };
                int parachuteNumber = i +2;
                serialNum[i].setHint("מס' מצנח - " + parachuteNumber);
                serialNum[i].setTag("serialNum" + parachuteNumber);
                serialNum[i].setBackgroundDrawable(originalDrawable);
                serialNum[i].setHintTextColor(Color.WHITE);
                serialNum[i].setInputType(InputType.TYPE_CLASS_NUMBER);

                 if (packageModel.getPackageParachuteExpirationDateHeavy() != null){
                     String m = packageModel.getPackageParachuteExpirationDateHeavy().get(i);
                     Log.d("programTAG", "onCreate: " + m);
                     ArrayList<Integer> p =  getIntent().getIntegerArrayListExtra("PackageParachuteSerialNumHeavy");
                     Log.d("parachuteAmountFun: ", "parachuteAmountFun: " + p);

                     serialNum[i].setText(String.valueOf(p.get(i)));
                     expirationDate[i].setText(String.valueOf(m));
                }
            }

        EditText boardInspector = new EditText(getApplicationContext());
        boardInspector.setPadding(0,25,30,25);
        boardInspector.setLayoutParams(mRparams);
        boardInspector.setTextColor(Color.WHITE);
        boardInspector.setHintTextColor(Color.WHITE);
        boardInspector.setBackgroundDrawable(originalDrawable);
        boardInspector.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_inspector, 0);
        boardInspector.setHint("מבקר רובד");
        layoutParachute.addView(boardInspector);

        EditText expirationInspection = new EditText(getApplicationContext());
        expirationInspection.setPadding(0,25,30,25);
        expirationInspection.setLayoutParams(mRparams1);
        expirationInspection.setTextColor(Color.WHITE);
        expirationInspection.setHintTextColor(Color.WHITE);
        expirationInspection.setBackgroundDrawable(originalDrawable);
        expirationInspection.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_inspector, 0);
        expirationInspection.setHint("תאריך ביקורת");
        layoutDate.addView(expirationInspection);

        expirationInspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PackageInfoActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener4,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener4 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("mDateSetListener", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                expirationInspection.setText(date);
            }
        };



    }

}
