package com.aecpple.aecpple;

public class FitnessInfodata {
    private String tv_gymname;
    private String tv_gymcall;
    private String tv_gymadress;
    private String tv_actname;
    private int iv_icon;

    public FitnessInfodata(int iv_icon, String tv_gymname, String tv_gymcall, String tv_gymadress, String tv_actname) {
        this.tv_gymname = tv_gymname;
        this.tv_gymcall = tv_gymcall;
        this.tv_gymadress = tv_gymadress;
        this.tv_actname = tv_actname;
        this.iv_icon = iv_icon;
    }


    public int getIv_icon() {
        return iv_icon;
    }

    public void setIv_icon(int iv_icon) {
        this.iv_icon = iv_icon;
    }

    public String getTv_gymname() {
        return tv_gymname;
    }
    public void setTv_gymname(String tv_gymname) {
        this.tv_gymname = tv_gymname;
    }

    public String getTv_gymcall() {
        return tv_gymcall;
    }
    public void setTv_gymcall(String tv_gymcall) {
        this.tv_gymcall = tv_gymcall;
    }

    public String getTv_gymadress() {
        return tv_gymadress;
    }
    public void setTv_gymadress(String tv_gymadress) {
        this.tv_gymadress = tv_gymadress;
    }

    public String getTv_actname() {
        return tv_actname;
    }
    public void setTv_actname(String tv_actname) {
        this.tv_actname = tv_actname;
    }

}



