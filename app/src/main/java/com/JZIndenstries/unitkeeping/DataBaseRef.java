package com.JZIndenstries.unitkeeping;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataBaseRef {
    private DatabaseReference databaseReference;
    Activity activity;
    public DataBaseRef(Activity activity){
        this.activity =activity;
    }
    //מעדכן את הקובץ
    public Task<Void> update(PackageModel packageModel,String pathID ,String RightOrLeftPath){
        String path = "https://unitkeeping-b1db9-default-rtdb.europe-west1.firebasedatabase.app";
        FirebaseDatabase database = FirebaseDatabase.getInstance(path);
        databaseReference = database.getReference().child(RightOrLeftPath).child(pathID);
        return databaseReference.setValue(packageModel);
    }
    public void getData(String child1,String child2){
        String path = "https://unitkeeping-b1db9-default-rtdb.europe-west1.firebasedatabase.app";
        FirebaseDatabase database = FirebaseDatabase.getInstance(path);
        databaseReference = database.getReference();
        final PackageModel[] packageModel = new PackageModel[1];
            databaseReference.child(child1).child(child2).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        packageModel[0] = task.getResult().getValue(PackageModel.class);
                    }
                }
            });
            Log.d("firebase", String.valueOf(packageModel[0]));
    }

}
