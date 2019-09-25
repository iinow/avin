package com.avin.common.util;

import java.util.regex.Pattern;

public final class PatternUtil {
    private static PatternUtil instance;
    private final Pattern avcode = Pattern.compile("^[a-zA-Z]-[0-9]*$");
    private final Pattern avcode1 = Pattern.compile("^[a-zA-Z] [0-9]*$");
    private final Pattern avcode2 = Pattern.compile("^[a-zA-Z][0-9]*$");

    private PatternUtil(){}

    public synchronized static PatternUtil getInstance(){
        if(instance == null){
           instance = new PatternUtil();
        }
        return instance;
    }

    public String getAvcode(String name){
        if(avcode.matcher(name).find()){

        }
        return null;
    }
}
