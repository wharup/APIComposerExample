package sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static sample.DVO.dvos;
import static sample.util.Logger.print;

import java.util.List;

import org.junit.jupiter.api.Test;

class APIComposerTest {

    @Test
    void 모두_머지() {
        List<DVO> left = dvos(10, 12, 14, 15, 20);
        List<DVO> right = dvos(10, 13);
        APIComposer m = new APIComposer(new DataGateway(left), new DataGateway(right));
        
        List<DVO> merge = m.load(100);
        assertListEquals(merge, dvos(10, 10, 12, 13, 14, 15, 20));
    }
    
    @Test
    void 머지_3개씩_끊어읽기() {
        List<DVO> left = dvos(10, 12, 14, 15, 20);
        List<DVO> right = dvos(10, 13);
        APIComposer m = new APIComposer(new DataGateway(left), new DataGateway(right));
        
        List<DVO> result = m.load(3);
        assertListEquals(result, dvos(10, 10, 12));
        
        result = m.load(3);
        assertListEquals(result, dvos(13, 14, 15));
        
        result = m.load(3);
        assertListEquals(result, dvos(20));
        
        result = m.load(3);
        assertListEquals(result, dvos());
    }

    @Test
    void 엣지_케이스1() {
        List<DVO> left = dvos(10, 12, 14, 15, 20);
        List<DVO> right = dvos();
        APIComposer m = new APIComposer(new DataGateway(left), new DataGateway(right));
        
        List<DVO> result = m.load(3);
        assertListEquals(result, dvos(10, 12, 14));
        result = m.load(3);
        assertListEquals(result, dvos(15, 20));
    }
    
    @Test
    void 엣지_케이스2() {
        List<DVO> left = dvos();
        List<DVO> right = dvos(10, 12, 14, 15, 20);
        APIComposer m = new APIComposer(new DataGateway(left), new DataGateway(right));
        
        List<DVO> result = m.load(3);
        assertListEquals(result, dvos(10, 12, 14));
        result = m.load(3);
        assertListEquals(result, dvos(15, 20));
    }
    
    @Test
    void 엣지_케이스3() {
        List<DVO> left = dvos();
        List<DVO> right = dvos();
        APIComposer m = new APIComposer(new DataGateway(left), new DataGateway(right));
        
        List<DVO> result = m.load(3);
        assertListEquals(result, dvos());
    }
    
    private void assertListEquals(List<DVO> left, List<DVO> right) {
        if (left == null)
            fail("");

        print(left);
        print(right);
        assertEquals(left.size(), right.size());

        for (int i = 0; i < left.size(); i++) {
            assertEquals(left, right);
        }
    }
}


