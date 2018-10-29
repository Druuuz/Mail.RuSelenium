package com.epam.ta.bo;

public class Message {
    private String target;
    private String subject;
    private String body;

    public Message(String target, String  subject, String body){
        this.target = target;
        this.subject =subject;
        this.body = body;
    }

    public String getTarget(){
        return this.target;
    }

    public String  getSubject(){
        return this.subject;
    }

    public  String getBody(){
        return  this.body;
    }

    public void setTarget(String target){
        this.target = target;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public void setBody(String body){
        this.body = body;
    }

}
