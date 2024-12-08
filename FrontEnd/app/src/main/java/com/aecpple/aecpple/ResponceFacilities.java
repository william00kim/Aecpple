package com.aecpple.aecpple;

import java.util.ArrayList;
import java.util.List;

public class ResponceFacilities {

//    private ArrayList<DatacoverFacilities> Facilites;
//
//    public ArrayList<DatacoverFacilities> getFacilites() {
//        return Facilites;
//    }

    private String status;
    private List<DataFacilites> data;

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataFacilites> getData() {
        return data;
    }

    public void setData(List<DataFacilites> data) {
        this.data = data;
    }
}
