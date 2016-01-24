package com.wb.util;

import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by YiSD on 2016/1/24.
 */
public class CusMath {
    public static int calculate(int value,int during,int s){
        double v = new BigDecimal(s).divide(new BigDecimal(during)).doubleValue();
        return (int) (((v-1)*(v-1))*value);
    }
}
