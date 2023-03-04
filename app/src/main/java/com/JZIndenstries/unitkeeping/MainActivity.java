package com.JZIndenstries.unitkeeping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener , View.OnClickListener {
//    a22 = 0;
//    treevol = 1;
//    rovad8 = 2;
//    rovad12 = 3;
//    firefly = 4;
    int counterBtn = 0 ;
    Button[] buttonsLeft = new Button[51];
    Button[] buttonsRight = new Button[51];
    TextView headLine;
    private final String PackageIdRC1 = "PackagesRightSide";
    private final String PackageIdLC1 = "PackagesLeftSide";
    ArrayList<PackageModel> packageModelsL = new ArrayList<>();
    ArrayList<PackageModel> packageModelsR = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(Color.parseColor("#000000"));
        Configuration configuration = getResources().getConfiguration();
        configuration.setLayoutDirection(new Locale("en"));
        int orientation = getResources().getConfiguration().orientation;
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) { // naming buttons correspondely to database name
            for (int i =1 ; i< 51 ; i++){
                String buttonIDLeft = "buttonL" + i;
                String buttonIDRight = "buttonR" + i;
                int resIdLeft = getResources().getIdentifier(buttonIDLeft,"id",getPackageName());
                int resIdRight = getResources().getIdentifier(buttonIDRight,"id",getPackageName());
                buttonsLeft[i] = findViewById(resIdLeft);
                buttonsRight[i] = findViewById(resIdRight);
                buttonsLeft[i].setTag("PackageL" + i);
                buttonsRight[i].setTag("PackageR" + i);
                buttonsLeft[i].setOnClickListener(this);
                buttonsRight[i].setOnClickListener(this);
                buttonsLeft[i].setOnLongClickListener(this);
                buttonsRight[i].setOnLongClickListener(this);
                buttonsLeft[i].setTextSize(8);
                buttonsRight[i].setTextSize(8);
                buttonsLeft[i].setText("----");
                buttonsRight[i].setText("----");
                buttonsLeft[i].setTextColor(Color.WHITE);
                buttonsRight[i].setTextColor(Color.WHITE);

            }
            displayInfo();
            headLine = findViewById(R.id.info1);
            headLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<PackageModel> packageModelsT = new ArrayList<>();
                    packageModelsT = packageModelsL;
                    if(packageModelsT.size() < 52){
                        packageModelsT.addAll(packageModelsR);
                    }

                    DialogSumInfo dialogSumInfo = new DialogSumInfo();
                    dialogSumInfo.showDialog(MainActivity.this,packageModelsT);
                }
            });

        } else {

            // In portrait
        }




    }

    @Override // showing data from firebase according to buttons
    public void onClick(View view) {
        PackageModel packageModel = new PackageModel();
        if (view.getTag().toString().contains("R")){
            int pos = Integer.parseInt(view.getTag().toString().replace("PackageR", "")) -1;
            Log.d("firebase", "position = " + pos);
            packageModel = packageModelsR.get(pos);
        }
        else {
            int pos = Integer.parseInt(view.getTag().toString().replace("PackageL", ""))-1;
            packageModel = packageModelsL.get(pos);
        }
        Log.d("packageModel", packageModel.toString());

        Intent intent = new Intent(this, PackageInfoActivity.class);
        intent.putExtra("PackageInfo", packageModel);
        intent.putExtra("PackageParachuteSerialNumHeavy",packageModel.getPackageParachuteSerialNumberHeavy());
        intent.putExtra("PackageParachuteExpirationDate", packageModel.getPackageParachuteExpirationDate());
        intent.putExtra("PackageReleaserExpirationDate", packageModel.getPackageReleaserExpirationDate());
        intent.putExtra("PackageExpirationDate", packageModel.getPackageExpirationDate());
        intent.putExtra("PackageParachuteOpenerExpirationDate", packageModel.getPackageParachuteOpenerExpirationDate());

        intent.putExtra("PackageId", view.getTag().toString());

        startActivity(intent);
    }


    public void displayInfo(){ //showing packaeges weights on the buttons
        DatabaseReference databaseReference;
        String path = "https://unitkeeping-b1db9-default-rtdb.europe-west1.firebasedatabase.app";
        FirebaseDatabase database = FirebaseDatabase.getInstance(path);
        databaseReference = database.getReference();

        for (int i =1 ; i< 51 ; i++){
            String buttonIDLeft = "PackageL" + i;
            String buttonIDRight = "PackageR" + i;
            int t = i;
            databaseReference.child(PackageIdRC1).child(buttonIDRight).get().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Loading();
                }
                else {
                    PackageModel packageModel = task.getResult().getValue(PackageModel.class);
                    String textR = (packageModel.getPackageWeight() == 0)
                            ?  "X" : String.valueOf(packageModel.getPackageWeight());
                    buttonsRight[t].setText(textR); // text position
                    packageModelsR.add(packageModel);
                }
            });
            databaseReference.child(PackageIdLC1).child(buttonIDLeft).get().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Loading();

                }
                else {
                    PackageModel packageModel = task.getResult().getValue(PackageModel.class);
                    String textL = (packageModel.getPackageWeight() == 0)
                            ?  "X" : String.valueOf(packageModel.getPackageWeight());
                    buttonsLeft[t].setText(textL); // text position
                    packageModelsL.add(packageModel);
                }
            });

        }



    }


    @Override
    public boolean onLongClick(View view) {
        PackageModel packageModel = new PackageModel();
        int pos =0;
        counterBtn = 4;
        if (view.getTag().toString().contains("R")){
            pos = Integer.parseInt(view.getTag().toString().replace("PackageR", ""));
//            buttonsRight[pos].setCompoundDrawablesWithIntrinsicBounds(R.drawable.checked,0,0,0);
//            buttonsRight[pos].setPadding(40,0,0,0);
//            buttonsRight[pos].setText("");
            buttonsRight[pos].setScaleX(2);
            buttonsRight[pos].setScaleY(10);
            TableRow.LayoutParams params = (TableRow.LayoutParams) buttonsLeft[pos].getLayoutParams();
            params.setMargins(2000,2000,2000,2000);
            buttonsRight[pos].setPadding(0,2000,0,2000);


        }
        else {
            pos = Integer.parseInt(view.getTag().toString().replace("PackageL", ""));
//            buttonsLeft[pos].setCompoundDrawablesWithIntrinsicBounds(R.drawable.checked,0,0,0);
//            buttonsRight[pos].setPadding(0,0,0,0);
//            buttonsRight[pos].setText("");
//            TableRow.LayoutParams params = (TableRow.LayoutParams) buttonsLeft[pos].getLayoutParams();
//            if(pos%5==0){
//                params.span = pos--;
//            } else{
//                params.span = pos++;
//            }
//            buttonsLeft[pos].setLayoutParams(params);
            buttonsRight[pos].setScaleX(2);
            buttonsRight[pos].setScaleY(10);

        }
        int row ,column;
        column = (pos % 5)  == 0 ? 5 : (pos % 5);
        row = (pos % 5)  != 0 ? 1 + (pos/5) : (pos/5);


        return true;
    }

    void Loading(){
        LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
        loadingDialog.showLoadingDialog(MainActivity.this);
        loadingDialog.dismissLoadingDialog(MainActivity.this);

    }
}