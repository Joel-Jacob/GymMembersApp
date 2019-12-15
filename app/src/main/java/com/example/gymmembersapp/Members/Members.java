package com.example.gymmembersapp.Members;

import android.os.Parcel;
import android.os.Parcelable;

public class Members implements Parcelable {
    private String memberName;
    private String memberId;
    private String plan;

    public Members(String memberName, String memberId, String plan) {
        this.memberName = memberName;
        this.memberId = memberId;
        this.plan = plan;
    }

    public Members(Parcel in) {
        memberName = in.readString();
        memberId = in.readString();
        plan = in.readString();
    }

    public String getMemberName() {
        return memberName;
    }

    public static final Creator<Members> CREATOR = new Creator<Members>() {
        @Override
        public Members createFromParcel(Parcel in) {
            return new Members(in);
        }

        @Override
        public Members[] newArray(int size) {
            return new Members[size];
        }
    };

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(memberName);
        dest.writeString(plan);
        dest.writeString(memberId);
    }
}
