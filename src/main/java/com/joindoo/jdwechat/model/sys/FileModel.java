package com.joindoo.jdwechat.model.sys;

/**
 * Created by zhuqiang on 2018/3/8.
 */
public class FileModel {
    private String filePath;
    private String type;//1 为视频
    private String file_id;
    private byte[] data;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
