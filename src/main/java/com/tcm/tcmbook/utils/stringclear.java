package com.tcm.tcmbook.utils;

public class stringclear {
    public static  String clear(String s){
        if(s==null||s.isEmpty())return "";
        char[] l=s.toCharArray();
        String r="";
        boolean flag=false;
        for(char c:l){
            if(c=='#'||c=='■')continue;
            if(c=='【'){
                flag=true;
                continue;
            }
            if(c=='】'){
                flag=false;
                continue;
            }
            if(flag)continue;
            r+=c;
        }
        return r;
    }
}
