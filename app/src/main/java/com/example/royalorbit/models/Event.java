package com.example.royalorbit.models;

import java.util.Date;

public class Event {
    private String id;
    private String userId;
    private String eventType;
    private boolean isFlower;
    private boolean isFabric;
    private boolean isStageDesign;
    private String contactNum;
    private Date startTime;
    private Date endTime;
    private long totalAmount;

    public Event() {
    }

    public Event(String id, String userId, String eventType, boolean isFlower, boolean isFabric, boolean isStageDesign, String contactNum, Date startTime, Date endTime, long totalAmount) {
        this.id = id;
        this.userId = userId;
        this.eventType = eventType;
        this.isFlower = isFlower;
        this.isFabric = isFabric;
        this.isStageDesign = isStageDesign;
        this.contactNum = contactNum;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public boolean isFlower() {
        return isFlower;
    }

    public void setFlower(boolean flower) {
        isFlower = flower;
    }

    public boolean isFabric() {
        return isFabric;
    }

    public void setFabric(boolean fabric) {
        isFabric = fabric;
    }

    public boolean isStageDesign() {
        return isStageDesign;
    }

    public void setStageDesign(boolean stageDesign) {
        isStageDesign = stageDesign;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
