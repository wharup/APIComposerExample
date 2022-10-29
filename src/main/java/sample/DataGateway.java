package sample;

import java.util.ArrayList;
import java.util.List;

public class DataGateway {

    List<DVO> data = new ArrayList<>();
    
    public List<DVO> load(int chunkLength) {
        List<DVO> result = new ArrayList<>();
        int length = data.size() > chunkLength ? chunkLength : data.size();
        for (int i = 0; i < length; i++) {
            result.add(data.remove(0));
        }
        return result;
    }

    public DataGateway (List<DVO> data) {
        for (DVO d : data) {
            this.data.add(DVO.copy(d));
        }
        
    }

}
