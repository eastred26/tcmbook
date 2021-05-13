package com.tcm.tcmbook.utils;

public class stringclear {
    //特殊符号处理，#表示要换行
    public static  String clear(String s){
        if(s==null||s.isEmpty())return "";
        char[] l=s.toCharArray();
        String r="";
        boolean flag=false;
        boolean flag2=false;
        boolean flag3=false;
        for(char c:l){
            if(c=='■'||c=='〓')continue;
            else if(c=='#'){
                if(!flag2){
                    if(!flag3){
                        r+="\n";
                        flag3=true;
                    }
                    else r+="\n\n";
                }
                flag2=true;
                continue;
            }
            flag2=false;
            if(c=='【'){
                flag=true;
                continue;
            }
            else if(c=='】'){
                flag=false;
                flag3=false;
                continue;
            }
            else if(flag)continue;
            r+=c;
        }
        return r;
    }
    //在clear基础上去除句末括号
    public static String clear2(String s){
        String res=clear(s);
        if(res=="")return res;
        int l=res.length();
        int i=0;
        for(i=l-1;i>=0;i--){
            if(res.charAt(i)!='（')i--;
            else break;
        }
        return res.substring(0,i);
    }

    //在clear基础上去除所有括号
    public static String clear3(String s){
        s=clear(s);
        if(s=="")return s;
        char[] l=s.toCharArray();
        String r="";
        boolean flag=false;
        for(char c:l){
            if(c=='（'){
                flag=true;
                continue;
            }
            else if(c=='）'){
                flag=false;
                continue;
            }
            else if(flag)continue;
            r+=c;
        }
        return r;
    }
}
