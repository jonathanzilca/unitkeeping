package com.JZIndenstries.unitkeeping;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DialogSumInfo {
    Button exitButton;
    TextView ReleaserTitleButton;
     public void showDialog(Activity activity , ArrayList<PackageModel> information){
      final Dialog dialog = new Dialog(activity);
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialog.setCancelable(true);
      dialog.setContentView(R.layout.dialog_sum_info);

      TextView packageAmount,Inspected,releaserAmount,packageTypes, releaserTypes,
              openingStrapAmount, openingStrapTypes;
      ReleaserTitleButton = dialog.findViewById(R.id.ReleaserTitleButton);
      ReleaserTitleButton.setPaintFlags(ReleaserTitleButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
      packageAmount = dialog.findViewById(R.id.textView4);
      Inspected = dialog.findViewById(R.id.textView5);
      releaserAmount = dialog.findViewById(R.id.textView10);
      releaserTypes = dialog.findViewById(R.id.textView9);
      openingStrapAmount = dialog.findViewById(R.id.textView11);
      packageTypes = dialog.findViewById(R.id.packageTypeAmount);
      openingStrapTypes = dialog.findViewById(R.id.textView12);
      int counterPackage = 0;
      int counterInspected = 0;
      int[] counterType= {0,0,0,0,0,0,0,0};
      int[] releaserType= {0,0,0,0,0};
      int[] openingStrapType= {0,0,0,0,0};

      for(PackageModel packageModel : information){
          if (packageModel.getPackageWeight() > 0) {
              counterPackage++;
          }
          // inspected packages
          if (packageModel.isPackageApproved() && packageModel.getPackageWeight() != 0){
              counterInspected++;
          }
          // package type
          if(packageModel.getPackageType().contains("א22") && packageModel.getPackageWeight() != 0){
            counterType[0]++;
          }
          if(packageModel.getPackageType().contains("טריוול") && packageModel.getPackageWeight() != 0){
              counterType[1]++;
          }
          if(packageModel.getPackageType().contains("כלוב") && packageModel.getPackageWeight() != 0){
              counterType[2]++;
          }
          if(packageModel.getPackageType().contains("8") && packageModel.getPackageWeight() != 0){
              counterType[3]++;
          }
          if(packageModel.getPackageType().contains("12") && packageModel.getPackageWeight() != 0){
              counterType[4]++;
          }
          if(packageModel.getPackageType().contains("16") && packageModel.getPackageWeight() != 0){
              counterType[5]++;
          }
          if(packageModel.getPackageType().contains("דו") && packageModel.getPackageWeight() != 0){
              counterType[6]++;
          }
          if(packageModel.getPackageType().contains("מנוהגת") && packageModel.getPackageWeight() != 0){
              counterType[7]++;
          }
          // releaser
          if(packageModel.getPackageReleaser().contains("FXC") && packageModel.getPackageWeight() != 0){
              releaserType[0]++;
          }
          if(packageModel.getPackageReleaser().contains("5000") && packageModel.getPackageWeight() != 0){
              releaserType[1]++;
          }
          if(packageModel.getPackageReleaser().contains("M1") && packageModel.getPackageWeight() != 0){
              releaserType[2]++;
          }
          if(packageModel.getPackageReleaser().contains("M2") && packageModel.getPackageWeight() != 0){
              releaserType[3]++;
          }
          if(packageModel.getPackageReleaser().contains("נתי") && packageModel.getPackageWeight() != 0){
              releaserType[4]++;
          }

          // opening strap type
          if(packageModel.getPackageOpeningStrap() == 3 && packageModel.getPackageWeight() != 0){
              openingStrapType[0]++;
          }
          if(packageModel.getPackageOpeningStrap() == 8 && packageModel.getPackageWeight() != 0){
              openingStrapType[1]++;
          }
          if(packageModel.getPackageOpeningStrap()== 20 && packageModel.getPackageWeight() != 0){
              openingStrapType[2]++;
          }
          if(packageModel.getPackageOpeningStrap()== 40 && packageModel.getPackageWeight() != 0){
              openingStrapType[3]++;
          }
          if(packageModel.getPackageOpeningStrap()== 60 && packageModel.getPackageWeight() != 0){
              openingStrapType[4]++;
          }
      }

         packageAmount.setText(String.valueOf(counterPackage) +  " מארזים  " );
         releaserAmount.setText(String.valueOf(counterPackage) +  " מנתקים  " );
         openingStrapAmount.setText(String.valueOf(counterPackage) +  " רצועות פתיחה  " );
         Inspected.setText(String.valueOf(counterInspected) + " מבוקרים  ");
         String types = " ";
         String releaser_types = " ";
         String opening_strap_types = " ";

         if (counterType[0] != 0) {
             types = types + "א22 - " + counterType[0];
         }
         if (counterType[1] != 0) {
             types = types + ", טריוול - " + counterType[1];
         }
         if (counterType[2] != 0) {
             types = types + ", כלוב - " + counterType[2] ;
         }
         if (counterType[3] != 0) {
             types = types + ", רובד 8 - " + counterType[3];
         }
         if (counterType[4] != 0) {
             types = types + ", רובד 12 - " + counterType[4];
         }
         if (counterType[5] != 0) {
             types = types + ", רובד 16 - " + counterType[5];
         }
         if (counterType[6] != 0) {
             types = types + ", דוש - " + counterType[6] ;
         }
         if (counterType[7] != 0) {
             types = types + ", מנוהגת - " + counterType[7];
         }
         // releaser
         if (releaserType[4] != 0) {
             releaser_types = releaser_types + releaserType[4] + " - Nati ,";
         }
         if (releaserType[3] != 0) {
             releaser_types = releaser_types + releaserType[3] + " - M2 ," ;
         }
         if (releaserType[2] != 0) {
             releaser_types = releaser_types + releaserType[2] + " - M1 ,"  ;
         }
         if (releaserType[1] != 0) {
             releaser_types = releaser_types + releaserType[1] + " - 5000 ,";
         }
         if (releaserType[0] != 0) {
             releaser_types = releaser_types + releaserType[0] + " - FXC" ;
         }
         //opening strap
         if (openingStrapType[4] != 0) {
             opening_strap_types = opening_strap_types + openingStrapType[4] + " - 60 ft ," ;
         }
         if (openingStrapType[3] != 0) {
             opening_strap_types = opening_strap_types + openingStrapType[3] + " - 40 ft ,";
         }
         if (openingStrapType[2] != 0) {
             opening_strap_types = opening_strap_types + openingStrapType[2] + " - 20 ft ,"  ;
         }
         if (openingStrapType[1] != 0) {
             opening_strap_types = opening_strap_types + openingStrapType[1] + " - 8 ft ," ;
         }
         if (openingStrapType[0] != 0) {
             opening_strap_types = opening_strap_types + openingStrapType[0] + " - 3 ft ,";
         }





         String typess =  "(" + types + ")";
         String releaser_typess =  "(" + releaser_types + ")" ;
         String opening_strap_typess = "(" + opening_strap_types + ")";





         packageTypes.setText(typess);
         releaserTypes.setText(releaser_typess);
         openingStrapTypes.setText(opening_strap_typess);

         exitButton = (Button) dialog.findViewById(R.id.exitButton);
         exitButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 dialog.dismiss();
             }
         });


         dialog.show();



     }
}
