package com.mole.springbootexample.model;

import jakarta.persistence.criteria.CriteriaBuilder;

public class DataResponse {
    private Object payload;

    public DataResponse(Object payload, Integer resCode){
        this.payload = payload;
        this.resCode = resCode;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    private Integer resCode;
}
