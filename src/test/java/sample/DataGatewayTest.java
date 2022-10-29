package sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sample.DVO.dvos;

import java.util.List;

import org.junit.jupiter.api.Test;

class DataGatewayTest {
    @Test
    void test() {
        List<DVO> left = dvos(10, 12, 14, 15, 20);

        List<DVO> chunk = null;
        DataGateway g = new DataGateway(left);
        
        chunk = g.load(3);
        assertEquals(3, chunk.size());
        
        chunk = g.load(3);
        assertEquals(2, chunk.size());
        
        chunk = g.load(3);
        assertEquals(0, chunk.size());
    }

}
