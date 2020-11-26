package org.mpo.homeopathy.symgen.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Remedy {
    Map<String, List<String>> symptomMap;
    String name;

    public Remedy(Map<String, List<String>> symptomMap, String name) {
        this.symptomMap = symptomMap;
        this.name = name;
    }

    public Map<String, List<String>> getSymptomMap() {
        return symptomMap;
    }

    public void setSymptomMap(Map<String, List<String>> symptomMap) {
        this.symptomMap = symptomMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Remedy{" +
                "symptomMap=" + symptomMap +
                ", name='" + name + '\'' +
                '}';
    }
}
