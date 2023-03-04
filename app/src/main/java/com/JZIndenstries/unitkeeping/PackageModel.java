package com.JZIndenstries.unitkeeping;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PackageModel implements Parcelable {
    int id;
    String packageType;
    int packageWeight;

    String packageReleaser;
    String packageReleaserExpirationDate;
    String packageReleaserInspector;

    int packageOpeningStrap;

    int packageParachuteSerialNumber;
    String packageParachuteExpirationDate;
    int packageParachuteOpenerSerialNumber;
    String packageParachuteOpenerExpirationDate;

    String packageExpirationDate;
    String packageInspector;
    ArrayList<Integer> packageParachuteSerialNumberHeavy;
    ArrayList<String> packageParachuteExpirationDateHeavy;
    String boardExpirationDate;
    String boardInspector;

    boolean packageApproved;
    public PackageModel(){

    }


    public PackageModel(int id, String packageType, int packageWeight, String packageReleaser, String packageReleaserExpirationDate, String packageReleaserInspector, int packageOpeningStrap, int packageParachuteSerialNumber, String packageParachuteExpirationDate, int packageParachuteOpenerSerialNumber, String packageParachuteOpenerExpirationDate, String packageExpirationDate, String packageInspector, boolean packageApproved) {
        this.packageType = packageType;
        this.packageWeight = packageWeight;
        this.packageReleaser = packageReleaser;
        this.packageReleaserExpirationDate = packageReleaserExpirationDate;
        this.packageReleaserInspector = packageReleaserInspector;
        this.packageOpeningStrap = packageOpeningStrap;
        this.packageParachuteSerialNumber = packageParachuteSerialNumber;
        this.packageParachuteExpirationDate = packageParachuteExpirationDate;
        this.packageParachuteOpenerSerialNumber = packageParachuteOpenerSerialNumber;
        this.packageParachuteOpenerExpirationDate = packageParachuteOpenerExpirationDate;
        this.packageExpirationDate = packageExpirationDate;
        this.packageInspector = packageInspector;
        this.packageApproved = packageApproved;
    }

    public PackageModel(int id, String packageType, int packageWeight, String packageReleaser, String packageReleaserExpirationDate, String packageReleaserInspector, int packageOpeningStrap, int packageParachuteSerialNumber, String packageParachuteExpirationDate, int packageParachuteOpenerSerialNumber, String packageParachuteOpenerExpirationDate, String packageExpirationDate, String packageInspector, ArrayList<Integer> packageParachuteSerialNumberHeavy, ArrayList<String> packageParachuteExpirationDateHeavy,String boardExpirationDate ,String boardInspector, boolean packageApproved) {
        this.id = id;
        this.packageType = packageType;
        this.packageWeight = packageWeight;
        this.packageReleaser = packageReleaser;
        this.packageReleaserExpirationDate = packageReleaserExpirationDate;
        this.packageReleaserInspector = packageReleaserInspector;
        this.packageOpeningStrap = packageOpeningStrap;
        this.packageParachuteSerialNumber = packageParachuteSerialNumber;
        this.packageParachuteExpirationDate = packageParachuteExpirationDate;
        this.packageParachuteOpenerSerialNumber = packageParachuteOpenerSerialNumber;
        this.packageParachuteOpenerExpirationDate = packageParachuteOpenerExpirationDate;
        this.packageExpirationDate = packageExpirationDate;
        this.packageInspector = packageInspector;
        this.boardExpirationDate = boardExpirationDate;
        this.boardInspector = boardInspector;
        this.packageParachuteSerialNumberHeavy = packageParachuteSerialNumberHeavy;
        this.packageParachuteExpirationDateHeavy = packageParachuteExpirationDateHeavy;
        this.packageApproved = packageApproved;
    }

    public String getBoardExpirationDate() {
        return boardExpirationDate;
    }

    public void setBoardExpirationDate(String boardExpirationDate) {
        this.boardExpirationDate = boardExpirationDate;
    }

    public String getBoardInspector() {
        return boardInspector;
    }

    public void setBoardInspector(String boardInspector) {
        this.boardInspector = boardInspector;
    }

    protected PackageModel(Parcel in) {
        id = in.readInt();
        packageType = in.readString();
        packageWeight = in.readInt();
        packageReleaser = in.readString();
        packageReleaserExpirationDate = in.readString();
        packageReleaserInspector = in.readString();
        packageOpeningStrap = in.readInt();
        packageParachuteSerialNumber = in.readInt();
        packageParachuteExpirationDate = in.readString();
        packageParachuteOpenerSerialNumber = in.readInt();
        packageParachuteOpenerExpirationDate = in.readString();
        packageExpirationDate = in.readString();
        packageInspector = in.readString();
        boardExpirationDate = in.readString();
        boardInspector = in.readString();
        packageParachuteExpirationDateHeavy = in.createStringArrayList();
        packageApproved = in.readByte() != 0;
    }

    public static final Creator<PackageModel> CREATOR = new Creator<PackageModel>() {
        @Override
        public PackageModel createFromParcel(Parcel in) {
            return new PackageModel(in);
        }

        @Override
        public PackageModel[] newArray(int size) {
            return new PackageModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(int packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getPackageReleaser() {
        return packageReleaser;
    }

    public void setPackageReleaser(String packageReleaser) {
        this.packageReleaser = packageReleaser;
    }

    public String getPackageReleaserExpirationDate() {
        return packageReleaserExpirationDate;
    }

    public void setPackageReleaserExpirationDate(String packageReleaserExpirationDate) {
        this.packageReleaserExpirationDate = packageReleaserExpirationDate;
    }

    public String getPackageReleaserInspector() {
        return packageReleaserInspector;
    }

    public void setPackageReleaserInspector(String packageReleaserInspector) {
        this.packageReleaserInspector = packageReleaserInspector;
    }

    public int getPackageOpeningStrap() {
        return packageOpeningStrap;
    }

    public void setPackageOpeningStrap(int packageOpeningStrap) {
        this.packageOpeningStrap = packageOpeningStrap;
    }

    public int getPackageParachuteSerialNumber() {
        return packageParachuteSerialNumber;
    }

    public void setPackageParachuteSerialNumber(int packageParachuteSerialNumber) {
        this.packageParachuteSerialNumber = packageParachuteSerialNumber;
    }

    public String getPackageParachuteExpirationDate() {
        return packageParachuteExpirationDate;
    }

    public void setPackageParachuteExpirationDate(String packageParachuteExpirationDate) {
        this.packageParachuteExpirationDate = packageParachuteExpirationDate;
    }

    public int getPackageParachuteOpenerSerialNumber() {
        return packageParachuteOpenerSerialNumber;
    }

    public void setPackageParachuteOpenerSerialNumber(int packageParachuteOpenerSerialNumber) {
        this.packageParachuteOpenerSerialNumber = packageParachuteOpenerSerialNumber;
    }

    public String getPackageParachuteOpenerExpirationDate() {
        return packageParachuteOpenerExpirationDate;
    }

    public void setPackageParachuteOpenerExpirationDate(String packageParachuteOpenerExpirationDate) {
        this.packageParachuteOpenerExpirationDate = packageParachuteOpenerExpirationDate;
    }

    public String getPackageExpirationDate() {
        return packageExpirationDate;
    }

    public void setPackageExpirationDate(String packageExpirationDate) {
        this.packageExpirationDate = packageExpirationDate;
    }

    public String getPackageInspector() {
        return packageInspector;
    }

    public void setPackageInspector(String packageInspector) {
        this.packageInspector = packageInspector;
    }

    public ArrayList<Integer> getPackageParachuteSerialNumberHeavy() {
        return packageParachuteSerialNumberHeavy;
    }

    public void setPackageParachuteSerialNumberHeavy(ArrayList<Integer> packageParachuteSerialNumberHeavy) {
        this.packageParachuteSerialNumberHeavy = packageParachuteSerialNumberHeavy;
    }

    public ArrayList<String> getPackageParachuteExpirationDateHeavy() {
        return packageParachuteExpirationDateHeavy;
    }

    public void setPackageParachuteExpirationDateHeavy(ArrayList<String> packageParachuteExpirationDateHeavy) {
        this.packageParachuteExpirationDateHeavy = packageParachuteExpirationDateHeavy;
    }

    public boolean isPackageApproved() {
        return packageApproved;
    }

    public void setPackageApproved(boolean packageApproved) {
        this.packageApproved = packageApproved;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "PackageModel{" +
                "id=" + id +
                ", packageType='" + packageType + '\'' +
                ", packageWeight=" + packageWeight +
                ", packageReleaser='" + packageReleaser + '\'' +
                ", packageReleaserExpirationDate='" + packageReleaserExpirationDate + '\'' +
                ", packageReleaserInspector='" + packageReleaserInspector + '\'' +
                ", packageOpeningStrap=" + packageOpeningStrap +
                ", packageParachuteSerialNumber=" + packageParachuteSerialNumber +
                ", packageParachuteExpirationDate='" + packageParachuteExpirationDate + '\'' +
                ", packageParachuteOpenerSerialNumber=" + packageParachuteOpenerSerialNumber +
                ", packageParachuteOpenerExpirationDate='" + packageParachuteOpenerExpirationDate + '\'' +
                ", packageExpirationDate='" + packageExpirationDate + '\'' +
                ", packageInspector='" + packageInspector + '\'' +
                ", boardExpirationDate='" + boardExpirationDate + '\'' +
                ", boardInspector='" + boardInspector + '\'' +
                ", packageParachuteSerialNumberHeavy=" + packageParachuteSerialNumberHeavy +
                ", packageParachuteExpirationDateHeavy=" + packageParachuteExpirationDateHeavy +
                ", packageApproved=" + packageApproved +
                '}';
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(packageType);
        parcel.writeInt(packageWeight);
        parcel.writeString(packageReleaser);
        parcel.writeString(packageReleaserExpirationDate);
        parcel.writeString(packageReleaserInspector);
        parcel.writeInt(packageOpeningStrap);
        parcel.writeInt(packageParachuteSerialNumber);
        parcel.writeString(packageParachuteExpirationDate);
        parcel.writeInt(packageParachuteOpenerSerialNumber);
        parcel.writeString(packageParachuteOpenerExpirationDate);
        parcel.writeString(packageExpirationDate);
        parcel.writeString(packageInspector);
        parcel.writeString(boardExpirationDate);
        parcel.writeString(boardInspector);
        parcel.writeStringList(packageParachuteExpirationDateHeavy);
        parcel.writeByte((byte) (packageApproved ? 1 : 0));
    }
}
