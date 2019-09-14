package com.sbia.sbiademo.util;

public class StringUtils {
    /**
     * 把中文转成Unicode码
     * @param str
     * @return
     */
    public static String chinaToUnicode(String str){
        String result="";
        for (int i = 0; i < str.length(); i++){
            int chr1 = (char) str.charAt(i);
            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)
                result+="\\u" + Integer.toHexString(chr1);
            }else{
                result+=str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 字符串转long
     * @param s
     * @return
     */
    public static long stringToLong(String s){
        long a=0;
        try {
            a = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return a;
    }
    /**
     * 字符串转Integer
     * @param s
     * @return
     */
    public static Integer stringToInteger(String s){
        Integer a=0;
        try {
            a = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return a;
    }
    /*
     * 检查字符串是否为空
     */
    public static boolean isEmpty(String str){
        return str == null || str.equals("");
    }
}
