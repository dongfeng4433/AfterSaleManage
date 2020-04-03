package com.joindoo.jdwechat.model;

public class DowloadFileModel {
    private int start;
    private String attachment_id;
    private String fj_mc;
    private String fj_path;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(String attachment_id) {
        this.attachment_id = attachment_id;
    }

    public String getFj_mc() {
        return fj_mc;
    }

    public void setFj_mc(String fj_mc) {
        this.fj_mc = fj_mc;
    }

    public String getFj_path() {
        return fj_path;
    }

    public void setFj_path(String fj_path) {
        this.fj_path = fj_path;
    }
}
