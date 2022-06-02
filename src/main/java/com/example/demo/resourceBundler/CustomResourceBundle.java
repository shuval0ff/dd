package com.example.demo.resourceBundler;

import javafx.util.Pair;

import java.util.*;

public class CustomResourceBundle extends ListResourceBundle {

    private Map<String, String> values;

    public CustomResourceBundle(Map<String, String> values) {
        this.values = values;
    }

    public CustomResourceBundle(Pair<String, String> value) {
        this.values = new HashMap<>();
        this.values.put(value.getKey(), value.getValue());
    }

    public CustomResourceBundle(Pair<String, String>[] values) {
        this.values = new HashMap<>();
        for(Pair<String, String> value : values) {
            this.values.put(value.getKey(), String.valueOf(value.getValue()));
        }
    }

    @Override
    protected Object[][] getContents() {
        Object[][] contents = new Object[values.size()][2];
        Object[] keys = values.keySet().toArray();
        Object[] values = this.values.values().toArray();
        for(int i = 0; i < keys.length; i++) {
            contents[i][0] = keys[i];
            contents[i][1] = values[i];
        }
        return contents;
    }
}

