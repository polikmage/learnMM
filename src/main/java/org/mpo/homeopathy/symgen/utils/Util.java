package org.mpo.homeopathy.symgen.utils;

public class Util {

    public static String getRemedyName(String sym) {
        String s = sym;
        s = s.substring(s.indexOf("@") + 1);
        s = s.substring(0, s.indexOf("@"));
        return s;
    }

/*    public static String getOnlySymptom(String symptom){

    }*/
}
