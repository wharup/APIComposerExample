package sample;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DVO {
    Instant created;
    String data;
    
    public Instant getCreated() {
        return created;
    }
    public void setCreated(Instant created) {
        this.created = created;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DVO other = (DVO) obj;
        return Objects.equals(created, other.created) && Objects.equals(data, other.data);
    }

    public static DVO copy(DVO d) {
        DVO copied = new DVO();
        copied.setCreated(d.getCreated());
        copied.setData(d.getData());
        return copied;
    }
    
    public static DVO dvo(int epochSecond) {
        DVO d = new DVO();
        d.setCreated(Instant.ofEpochSecond(epochSecond));
        d.setData(d.getCreated().toString());
        return d;
    }
    
    public static List<DVO> dvos(int... times) {
        List<DVO> result = new ArrayList<>();
        for (int time : times) {
            result.add(dvo(time));
        }
        return result;
    }
    public static int compare(DVO left, DVO right) {
        return left.getCreated().compareTo(right.getCreated());
    }
    
}