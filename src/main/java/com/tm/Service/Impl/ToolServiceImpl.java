package com.tm.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tm.Bean.Designations;
import com.tm.Bean.ParamType;
import com.tm.Bean.ParamWeightage;
import com.tm.Bean.RatingParams;
import com.tm.Bean.ResponseBean;
import com.tm.Bean.Teams;
import com.tm.Bean.Users;
import com.tm.Dao.ToolDao;
import com.tm.Service.ToolService;

@Component
public class ToolServiceImpl implements ToolService{
    
    @Autowired
    ToolDao toolDao;
    
    public ResponseBean<Long> saveTeams(Teams team) {
        return toolDao.saveTeams(team);
    }
    
    public ResponseBean<List<Teams>> getTeams(Long teamId,String teamName) {
        return toolDao.getTeams(teamId, teamName);
    }
    
    public ResponseBean<Long> saveDesignations(Designations designation) {
        return toolDao.saveDesignations(designation);
    }
    
    public ResponseBean<List<Designations>> getDesignations(Long designationId,String designation) {
        return toolDao.getDesignations(designationId, designation);
    }
    
    public ResponseBean<Long> saveUsers(Users user) {
        return toolDao.saveUsers(user);
    }
    
    public ResponseBean<List<Users>> getUsers(Long id,String email, String mobile, Long teamId, Long designationId) {
        return toolDao.getUsers(id, email, mobile, teamId, designationId);
    }
    
    public ResponseBean<Long> saveParamTypes(ParamType paramType) {
        return toolDao.saveParamTypes(paramType);
    }
    
    public ResponseBean<List<ParamType>> getParamTypes(Long paramTypeId,String type) {
        return toolDao.getParamTypes(paramTypeId, type);
    }
    
    public ResponseBean<Long> saveRatingParams(RatingParams ratingParams) {
        return toolDao.saveRatingParams(ratingParams);
    }
    
    public ResponseBean<List<RatingParams>> getRatingParams(Long paramId, Long typeId,String param) {
        return toolDao.getRatingParams(paramId, typeId, param);
    }
    
    public ResponseBean<Long> saveParamWeightage(ParamWeightage paramWeightage) {
        return toolDao.saveParamWeightage(paramWeightage);
    }
    
    public ResponseBean<List<ParamWeightage>> getParamWeightage(Long paramId, Long teamId,Long designationId) {
        return toolDao.getParamWeightage(paramId, teamId, designationId);
    }

}
