package common;

/**
 * Created by L.H.S on 2017/3/10.
 */
public class RespInfo {

    private boolean status;
    private String info;
    private Object object;

    public RespInfo(boolean status, String info, Object object) {
        this.status = status;
        this.info = info;
        this.object = object;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
