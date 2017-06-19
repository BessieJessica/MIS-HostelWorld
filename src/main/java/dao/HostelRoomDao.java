package dao;

import model.HostelRoom;

/**
 * Created by L.H.S on 2017/3/9.
 */
public interface HostelRoomDao {

    HostelRoom getRoom(int hosId, int roomId);

    void updateState(int hosId, int roomId, String state);

    void insertRoom(int hosId, int roomId);
}
