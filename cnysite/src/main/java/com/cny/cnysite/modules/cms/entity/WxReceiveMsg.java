package com.cny.cnysite.modules.cms.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WxReceiveMsg {
    @XmlElement(name="ToUserName")
    private String toUserName;//开发者微信号
    @XmlElement(name="FromUserName")
    private String fromUserName;//发送方帐号（一个OpenID）
    @XmlElement(name="CreateTime")
    private Long createTime;//消息创建时间 （整型）
    @XmlElement(name="MsgType")
    private String msgType;//text
    @XmlElement(name="Content")
    private String content;//文本消息内容
    @XmlElement(name="MsgId")
    private Long msgId;//消息id，64位整型

    //图片消息
    @XmlElement(name="PicUrl")
    private String picUrl;
    @XmlElement(name="MediaId")
    private String mediaId;

    //事件消息
    @XmlElement(name="Event")
    private String event;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "WxReceiveMsg{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                ", msgId=" + msgId +
                ", picUrl='" + picUrl + '\'' +
                ", mediaId='" + mediaId + '\'' +
                '}';
    }
}
