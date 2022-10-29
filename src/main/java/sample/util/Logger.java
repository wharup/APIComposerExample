package sample.util;

import java.util.List;

import sample.DVO;

public class Logger {
    
    public static void print(List<DVO> m2) {
        for(int i = 0; i < m2.size(); i++) {
            print("%d, ", m2.get(i).getCreated().toEpochMilli()/1000);
        }
        print("\n");
    }

    public static void print(String format, Object... obj) {
        System.out.print(String.format(format, obj));
    }
    
}