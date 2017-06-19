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
@Table(name = "hostelroom")
public class HostelRoom implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private int roomId;
    private int hostelId;
    private String isempty;

    public HostelRoom() {}

    public HostelRoom(int roomId, int hostelId, String isempty) {
        this.roomId = roomId;
        this.hostelId = hostelId;
        this.isempty = isempty;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public String getIsempty() {
        return isempty;
    }

    public void setIsempty(String isempty) {
        this.isempty = isempty;
    }
}
