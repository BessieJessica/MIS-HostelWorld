package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by L.H.S on 2017/3/9.
 */
@Entity
@Table(name = "manager")
public class Manager implements Serializable {

    private int id;
    private String name;
    private String password;
    private String imgurl;

    public Manager() {}

    public Manager(int id, String name, String password, String imgurl) {
        this.id = id;

        this.name = name;
        this.password = password;
        this.imgurl = imgurl;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
