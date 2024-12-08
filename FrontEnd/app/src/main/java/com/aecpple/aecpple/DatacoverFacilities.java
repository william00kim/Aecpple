package com.aecpple.aecpple;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatacoverFacilities {

    private String status;
    private ArrayList<DataFacilites> data;

    DatacoverFacilities(String status, ArrayList<DataFacilites> data) {
        this.status = status;
        this.data = data;
    }
}
