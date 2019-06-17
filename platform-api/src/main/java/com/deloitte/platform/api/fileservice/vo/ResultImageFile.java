package com.deloitte.platform.api.fileservice.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lixin on 11/04/2019.
 */
@Data
public class ResultImageFile implements Serializable {
    /**
     * {
     *     "state": "SUCCESS",
     *     "original": "p1.png",
     *     "size": "53294",
     *     "title": "1555073575774016274.png",
     *     "type": ".png",
     *     "url": "/ueditor/jsp/upload/image/20190412/1555073575774016274.png"
     * }
     */
    private String state;
    private String url;
    private String title;
    private String original;
    private long size;
    private String type;

}
