package com.example.user.nottspark.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private int userID;
    private String userUsername;
    private String userName;
    private String userContactNum;
    private String userEmail;
    private String carMake;
    private String carModel;
    private String carPlate;
    private String registerDate;
    private String userAccountType;
    private String userPassword;

    public User() {

    }

    public User(String userUsername, String userName, String userContactNum, String userEmail, String carMake, String carModel, String carPlate, String userAccountType, String userPassword) {
        this.userUsername = userUsername;
        this.userName = userName;
        this.userContactNum = userContactNum;
        this.userEmail = userEmail;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carPlate = carPlate;
        this.registerDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.userAccountType = userAccountType;
        this.userPassword = userPassword;
    }

    public User(int userID, String userUsername, String userName, String userContactNum, String userEmail, String carMake, String carModel, String carPlate, String registerDate, String userAccountType, String userPassword) {
        this.userID = userID;
        this.userUsername = userUsername;
        this.userName = userName;
        this.userContactNum = userContactNum;
        this.userEmail = userEmail;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carPlate = carPlate;
        this.registerDate = registerDate;
        this.userAccountType = userAccountType;
        this.userPassword = userPassword;
    }

    protected User(Parcel in) {
        userID = in.readInt();
        userUsername = in.readString();
        userName = in.readString();
        userContactNum = in.readString();
        userEmail = in.readString();
        carMake = in.readString();
        carModel = in.readString();
        carPlate = in.readString();
        registerDate = in.readString();
        userAccountType = in.readString();
        userPassword = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userID);
        dest.writeString(userUsername);
        dest.writeString(userName);
        dest.writeString(userContactNum);
        dest.writeString(userEmail);
        dest.writeString(carMake);
        dest.writeString(carModel);
        dest.writeString(carPlate);
        dest.writeString(registerDate);
        dest.writeString(userAccountType);
        dest.writeString(userPassword);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContactNum() {
        return userContactNum;
    }

    public void setUserContactNum(String userContactNum) {
        this.userContactNum = userContactNum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUserAccountType() {
        return userAccountType;
    }

    public void setUserAccountType(String userAccountType) {
        this.userAccountType = userAccountType;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userUsername='" + userUsername + '\'' +
                ", userName='" + userName + '\'' +
                ", userContactNum='" + userContactNum + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", carMake='" + carMake + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carPlate='" + carPlate + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", userAccountType='" + userAccountType + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
