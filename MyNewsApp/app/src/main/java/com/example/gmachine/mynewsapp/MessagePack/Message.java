package com.example.gmachine.mynewsapp.MessagePack;

public class Message {
    private String headerMsg;
    private String descriptionMsg;
    private int resId;
    private int descId;


    public Message(String headerMsg, String descriptionMsg){
        this.headerMsg = headerMsg;
        this.descriptionMsg = descriptionMsg;

    }

    public String getHeaderMsg() {
        return headerMsg;
    }

    public void setHeaderMsg(String headerMsg) {
        this.headerMsg = headerMsg;
    }

    public String getDescriptionMsg() {
        return descriptionMsg;
    }

    public void setDescriptionMsg(String descriptionMsg) {
        this.descriptionMsg = descriptionMsg;
    }


}
