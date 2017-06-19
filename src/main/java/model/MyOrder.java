package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Entity
@Table(name = "myorder")
public class MyOrder implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "userId")
    private int userId;
    private int hostelId;
    private String hostelname;
    private String location;
    private String room;
    private String ordertime;
    private String checkintime;
    private String leavetime;
    private double pay;
    private String phone;
    private String username;
    private String orderstate;   // 历史预定：history；future
    private String checkstate;   // 是否已入住： checked；unchecked

    public MyOrder() {
    }

    public MyOrder(int userId, int hostelId, String hostelname, String location, String room, String ordertime, String checkintime, String leavetime, double pay, String phone, String username, String orderstate, String checkstate) {
        this.userId = userId;
        this.hostelId = hostelId;
        this.hostelname = hostelname;
        this.location = location;
        this.room = room;
        this.ordertime = ordertime;
        this.checkintime = checkintime;
        this.leavetime = leavetime;
        this.pay = pay;
        this.phone = phone;
        this.username = username;
        this.orderstate = orderstate;
        this.checkstate = checkstate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHostelname() {
        return hostelname;
    }

    public void setHostelname(String hostelname) {
        this.hostelname = hostelname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(String checkintime) {
        this.checkintime = checkintime;
    }

    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
    }

    public String getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(String checkstate) {
        this.checkstate = checkstate;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }
}
