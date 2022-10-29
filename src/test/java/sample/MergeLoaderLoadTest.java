package sample;

import static sample.DVO.dvos;
import static sample.util.Logger.print;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sample.util.StopWatch;

class MergeLoaderLoadTest {
    int size = 100000;
    int[] leftNumbers;
    int[] rightNumbers;
    int[] resultNumbers;

    List<DVO> left = null;
    List<DVO> right = null;

    boolean printLog = false;
    
    @BeforeEach
    void setup() {
        print("\netup stars\n");
        StopWatch sw = StopWatch.start();
        leftNumbers = new int[size];
        for (int i = 0; i < size; i++) {
            leftNumbers[i] = i * 2;
        }
        rightNumbers = new int[size];
        for (int i = 0; i < size; i++) {
            rightNumbers[i] = i * 2 + 1;
        }
        resultNumbers = new int[size*2];
        for (int i = 0; i < size*2; i++) {
            resultNumbers[i] = i;
        }
        left = dvos(leftNumbers);
        right = dvos(rightNumbers);
        print("setup ends:%d\n", sw.elapsed());
    }
    
    @Test
    void 한번로딩() {
        APIComposer m = new APIComposer(new DataGateway(left), new DataGateway(right));
        
        StopWatch sw = StopWatch.start();
        print("한번로딩: starts\n");
        List<DVO> result = m.load(Integer.MAX_VALUE);
        if (printLog)
            print(result);
        print("한번로딩: ends (%d)\n", sw.elapsed());
    }

    @Test
    void 나눠서로딩() {
        doMergeLoad(10000);
        doMergeLoad(1000);
        doMergeLoad(100);
        doMergeLoad(33);
        doMergeLoad(17);
    }

    private StopWatch doMergeLoad(int chunkLength) {
        print("나눠서로딩_%d개 starts\n", chunkLength);
        APIComposer m = new APIComposer(new DataGateway(left), new DataGateway(right));
        StopWatch sw = StopWatch.start();
        for (int i = 0; i < 2*size/chunkLength + 1; i++) {
            List<DVO> result = m.load(chunkLength);
            if (printLog)
                print(result);
        }
        print("나눠서로딩_%d개 ends(%d)\n", chunkLength, sw.elapsed());
        return sw;
    }
    
}


