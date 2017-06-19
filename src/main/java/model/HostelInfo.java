package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by L.H.S on 2017/3/9.
 */

@Entity
@Table(name = "hostelinfo")
public class HostelInfo implements Serializable {

    @Id
    private int id;

    private String password;
    private String name;
    private String location;
    private String description;
    private String ownername;
    private String phone;
    private String email;
    private String notice;
    private int roomcount;

    public HostelInfo() {}

    public HostelInfo(int id, String password, String name, String location, String description, String ownername, String phone, String email, String notice, int roomcount) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.location = location;
        this.description = description;
        this.ownername = ownername;
        this.phone = phone;
        this.email = email;
        this.notice = notice;
        this.roomcount = roomcount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(int roomcount) {
        this.roomcount = roomcount;
    }
}
