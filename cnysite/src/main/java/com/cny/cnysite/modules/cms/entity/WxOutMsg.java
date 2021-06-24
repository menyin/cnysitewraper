package com.cny.cnysite.modules.cms.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "xml")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WxOutMsg {
    //文本消息
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

    //图片消息
    @XmlElementWrapper(name="Image")//图片返回消息格式要求包装Image
    @XmlElement(name="MediaId")
    private String[] mediaId;


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

    public String[] getMediaId() {
        return mediaId;
    }

    public void setMediaId(String[] mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "WxOutMsg{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                ", mediaId='" + mediaId + '\'' +
                '}';
    }
}
