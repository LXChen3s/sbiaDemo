package com.sbia.sbiademo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    public static class TempC{
        int i;
        public void setI(int i){
            this.i=i;
        }
    }

    @Test
    public void testList(){
        List<TempC> l=new ArrayList<>();
        for(int i=0;i<5;i++){
            TempC t=new TempC();
            t.setI(i);
            l.add(t);
        }
        for (TempC tc:l){
            System.out.println(tc.i);
        }
    }
}
