package sample;

import static sample.DVO.compare;

import java.util.ArrayList;
import java.util.List;

public class APIComposer {

    //정렬된 데이터1
    List<DVO> leftBuffer = new ArrayList<>();
    //정렬된 데이터2
    List<DVO> rightBuffer = new ArrayList<>();
    
    DataGateway leftLoader = null;
    DataGateway rightLoader = null;
    
    public APIComposer(DataGateway leftLoader, DataGateway rightLoader) {
        this.leftLoader = leftLoader;
        this.rightLoader = rightLoader;
    }

    List<DVO> load(int chunkLength) {
        
        //a. 2개 버퍼를 페이지 크기 만큼 채우기
        loadBuffer(chunkLength);

        //b. 머지
        List<DVO> result = new ArrayList<>();
        for (int i = 0; i < chunkLength; i++) {
            //b1. 데이터가 없는 경우 종료
            if (leftBuffer.isEmpty() && rightBuffer.isEmpty())
                break;

            //b2. left만 데이터가 남은 경우 right 추가
            if (leftBuffer.isEmpty() && !rightBuffer.isEmpty()) {
                result.add(rightBuffer.remove(0));
            } 
            //b3. right만 데이터가 남은 경우 left 추가
            else if (!leftBuffer.isEmpty() && rightBuffer.isEmpty()) {
                result.add(leftBuffer.remove(0));
            } 
            //b4. 양쪽에 데이터가 있는 경우
            else {
                if (compare(leftBuffer.get(0), rightBuffer.get(0)) <= 0) {
                    result.add(leftBuffer.remove(0));
                } else {
                    result.add(rightBuffer.remove(0));
                }
            }
        }        
        return result;
    }

    public void loadBuffer(int chunkLength) {
        if (leftBuffer == null)
            leftBuffer = new ArrayList<>();
        if (rightBuffer == null) 
            rightBuffer = new ArrayList<>();
        
        if (leftBuffer.size() < chunkLength)
            leftBuffer.addAll(leftLoader.load(chunkLength)); 
        if (rightBuffer.size() < chunkLength)
            rightBuffer.addAll(rightLoader.load(chunkLength));
    }
    
}