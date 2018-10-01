package com.tm.Service;

import java.util.List;

import com.tm.Bean.Designations;
import com.tm.Bean.ParamType;
import com.tm.Bean.ParamWeightage;
import com.tm.Bean.RatingParams;
import com.tm.Bean.ResponseBean;
import com.tm.Bean.Teams;
import com.tm.Bean.Users;

public interface ToolService {
    
    public ResponseBean<Long> saveTeams(Teams team);
    public ResponseBean<List<Teams>> getTeams(Long teamId,String teamName);
    
    public ResponseBean<Long> saveDesignations(Designations designation);
    public ResponseBean<List<Designations>> getDesignations(Long designationId,String designation);
    
    public ResponseBean<Long> saveUsers(Users user);
    public ResponseBean<List<Users>> getUsers(Long id,String email, String mobile, Long teamId, Long designationId);
    
    public ResponseBean<Long> saveParamTypes(ParamType paramType);
    public ResponseBean<List<ParamType>> getParamTypes(Long paramTypeId,String type);
    
    public ResponseBean<Long> saveRatingParams(RatingParams ratingParams);
    public ResponseBean<List<RatingParams>> getRatingParams(Long paramId, Long typeId,String param);
    
    public ResponseBean<Long> saveParamWeightage(ParamWeightage paramWeightage);
    public ResponseBean<List<ParamWeightage>> getParamWeightage(Long paramId, Long teamId,Long designationId);

}
