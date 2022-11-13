package com.lovo.sgproj.bean;

import java.time.LocalDate;

//记录实体
public class RecordBean {
    private int recID;
    private String recDescription;//损坏描述
    private LocalDate recReportDate;//报损日期
    private boolean recSoluted;//是否解决(解决--true；未解决--false)

    public RecordBean() {
    }

    public RecordBean(int recID, String recDescription, LocalDate recReportDate, boolean recSoluted) {
        this.recID = recID;
        this.recDescription = recDescription;
        this.recReportDate = recReportDate;
        this.recSoluted = recSoluted;
    }

    public RecordBean(String recDescription, LocalDate recReportDate, boolean recSoluted) {
        this.recDescription = recDescription;
        this.recReportDate = recReportDate;
        this.recSoluted = recSoluted;
    }

    public int getRecID() {
        return recID;
    }

    public void setRecID(int recID) {
        this.recID = recID;
    }

    public String getRecDescription() {
        return recDescription;
    }

    public void setRecDescription(String recDescription) {
        this.recDescription = recDescription;
    }

    public LocalDate getRecReportDate() {
        return recReportDate;
    }

    public void setRecReportDate(LocalDate recReportDate) {
        this.recReportDate = recReportDate;
    }

    public boolean isRecSoluted() {
        return recSoluted;
    }

    public void setRecSoluted(boolean recSoluted) {
        this.recSoluted = recSoluted;
    }
}
