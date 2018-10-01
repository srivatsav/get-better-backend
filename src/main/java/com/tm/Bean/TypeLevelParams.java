package com.tm.Bean;

import java.util.List;

public class TypeLevelParams {
    private String paramType;
    private List<RatingParams> paramList;
    public String getParamType() {
        return paramType;
    }
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
    public List<RatingParams> getParamList() {
        return paramList;
    }
    public void setParamList(List<RatingParams> paramList) {
        this.paramList = paramList;
    }
    
    
}
