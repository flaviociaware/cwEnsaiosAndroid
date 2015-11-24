package br.com.ciaware.android.ensaios.thiengocalopsita.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 4/5/15.
 */
public class Car implements Parcelable {
    private String model;
    private String brand;
    private String description;
    private int category;
    private String tel;
    private int photo;


    public Car(){}
    public Car(String m, String b, int p){
        model = m;
        brand = b;
        photo = p;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    // PARCELABLE
    public Car(Parcel parcel){
        setModel(parcel.readString());
        setBrand(parcel.readString());
        setDescription(parcel.readString());
        setCategory(parcel.readInt());
        setTel(parcel.readString());
        setPhoto(parcel.readInt());
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( getModel() );
        dest.writeString( getBrand() );
        dest.writeString( getDescription() );
        dest.writeInt( getCategory() );
        dest.writeString( getTel() );
        dest.writeInt( getPhoto() );
    }
    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>(){
        @Override
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }
        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };
}
