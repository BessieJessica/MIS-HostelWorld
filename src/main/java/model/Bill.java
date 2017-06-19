package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Entity
@Table(name = "bill")
public class Bill implements Serializable {
    public Bill() {
    }

    @Id
    private int id;
    private int userId;
    private String time;
    private double pay;
    private String hostel;
    private String year;
    private String month;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Bill(int userId, String time, double pay, String hostel, String year, String month) {

        this.userId = userId;
        this.time = time;
        this.pay = pay;
        this.hostel = hostel;
        this.year = year;
        this.month = month;
    }
}
