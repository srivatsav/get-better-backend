package com.tm.Dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.tm.Bean.Designations;
import com.tm.Bean.ParamType;
import com.tm.Bean.ParamWeightage;
import com.tm.Bean.RatingParams;
import com.tm.Bean.ResponseBean;
import com.tm.Bean.Teams;
import com.tm.Bean.TypeLevelParams;
import com.tm.Bean.Users;
import com.tm.Dao.ToolDao;

@Component
public class ToolDaoImpl implements ToolDao{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolDaoImpl.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public ResponseBean<Long> saveTeams(Teams team) {
        ResponseBean<Long> response = new ResponseBean<Long>();
        try {
            String procName = "p_save_teams_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", team.getId());
            inParams.addValue("in_team_name", team.getTeamName());
            inParams.addValue("in_status", team.getStatus());
            inParams.addValue("in_do_commit", "Y");
            
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.saveTeams() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    Long id =(Long) outMap.get("out_f_id");
                    response.setData(id);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }

        }catch (Exception e) {
            LOGGER.info("ToolDaoImpl.saveTeams Exception Occured :: ",e);
        }
        return response;
    }
    
    public ResponseBean<List<Teams>> getTeams(Long teamId,String teamName) {
        ResponseBean<List<Teams>> response = new ResponseBean<List<Teams>>();        
        try {
            LOGGER.info("ToolDaoImpl.getTeams :: "+ teamId + " "+teamName);
            String procName = "p_get_teams_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", teamId);
            inParams.addValue("in_team_name", teamName);
            
            proc.returningResultSet("teams", new RowMapper<Teams>() {
                @Override                
                public Teams mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Teams team = new Teams();
                    team.setId(rs.getLong("f_id"));
                    team.setTeamName(rs.getString("f_team_name"));
                    team.setStatus(rs.getString("f_status"));
                    return team;
                }
            });
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.getTeams() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    List<Teams> teamsList = (List<Teams>) outMap.get("teams");
                    response.setData(teamsList);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }
            
        } catch(Exception e) {
            LOGGER.info("ToolDaoImpl.getTeams :: Exception occured",e);
            
        }
        return response;        
    }
    
    public ResponseBean<Long> saveDesignations(Designations designation) {
        ResponseBean<Long> response = new ResponseBean<Long>();
        try {
            LOGGER.info("ToolDaoImpl.saveDesignations :: "+designation);
            String procName = "p_save_designations_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", designation.getId());
            inParams.addValue("in_designation", designation.getDesignation());
            inParams.addValue("in_status", designation.getStatus());
            inParams.addValue("in_do_commit", "Y");
            
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.saveDesignations() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    Long id =(Long) outMap.get("out_f_id");
                    response.setData(id);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }

        }catch (Exception e) {
            LOGGER.info("ToolDaoImpl.saveDesignations Exception Occured :: ",e);
        }
        return response;
    }
    
    public ResponseBean<List<Designations>> getDesignations(Long designationId,String designation) {
        ResponseBean<List<Designations>> response = new ResponseBean<List<Designations>>();        
        try {
            LOGGER.info("ToolDaoImpl.getDesignations :: "+ designationId + " "+designation);
            String procName = "p_get_designations_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", designationId);
            inParams.addValue("in_designation", designation);
            
            proc.returningResultSet("designations", new RowMapper<Designations>() {
                @Override                
                public Designations mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Designations designation = new Designations();
                    designation.setId(rs.getLong("f_id"));
                    designation.setDesignation(rs.getString("f_designation"));
                    designation.setStatus(rs.getString("f_status"));
                    return designation;
                }
            });
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.getDesignations() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    List<Designations> designationList = (List<Designations>) outMap.get("designations");
                    response.setData(designationList);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }
            
        } catch(Exception e) {
            LOGGER.info("ToolDaoImpl.getDesignations :: Exception occured",e);
            
        }
        return response;        
    }
    
    public ResponseBean<Long> saveUsers(Users user) {
        ResponseBean<Long> response = new ResponseBean<Long>();
        try {
            LOGGER.info("ToolDaoImpl.saveUsers :: "+user);
            String procName = "p_save_user_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", user.getId());
            inParams.addValue("in_fname", user.getfName());
            inParams.addValue("in_mname", user.getmName());
            inParams.addValue("in_lname", user.getlName());
            inParams.addValue("in_email", user.getEmail());
            inParams.addValue("in_mobile", user.getMobile());
            inParams.addValue("in_team_id", user.getTeamId());
            inParams.addValue("in_designation_id", user.getDesignationId());
            inParams.addValue("in_status", user.getStatus());
            inParams.addValue("in_do_commit", "Y");
            
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.saveUsers() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    Long id =(Long) outMap.get("out_f_id");
                    response.setData(id);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }

        }catch (Exception e) {
            LOGGER.info("ToolDaoImpl.saveUsers Exception Occured :: ",e);
        }
        return response;
    }
    
    public ResponseBean<List<Users>> getUsers(Long id,String email, String mobile, Long teamId, Long designationId) {
        ResponseBean<List<Users>> response = new ResponseBean<List<Users>>();        
        try {
            LOGGER.info("ToolDaoImpl.getUsers :: designationId"+ 
                    designationId + " teamId "+teamId+" mobile "+mobile+" email "+email+" id"+id);
            
            String procName = "p_get_users_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();            
            inParams.addValue("in_designation_id", designationId);
            inParams.addValue("in_id", id);
            inParams.addValue("in_email", email);
            inParams.addValue("in_mobile", mobile);
            inParams.addValue("in_team_id", teamId);
            
            proc.returningResultSet("users", new RowMapper<Users>() {
                @Override                
                public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Users user = new Users();
                    user.setId(rs.getLong("f_id"));
                    user.setDesignationId(rs.getLong("f_designation_id"));
                    user.setfName(rs.getString("f_fname"));
                    user.setmName(rs.getString("f_mname"));
                    user.setlName(rs.getString("f_lname"));
                    user.setEmail(rs.getString("f_email"));
                    user.setMobile(rs.getString("f_mobile"));
                    user.setTeamId(rs.getLong("f_team_id"));
                    user.setStatus(rs.getString("f_status"));
                    return user;
                }
            });
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.getUsers() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    List<Users> usersList = (List<Users>) outMap.get("users");
                    response.setData(usersList);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }
            
        } catch(Exception e) {
            LOGGER.info("ToolDaoImpl.getDesignations :: Exception occured",e);
            
        }
        return response;        
    }
    
    public ResponseBean<Long> saveParamTypes(ParamType paramType) {
        ResponseBean<Long> response = new ResponseBean<Long>();
        try {
            LOGGER.info("ToolDaoImpl.saveParamTypes :: "+paramType);
            String procName = "p_save_param_types_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", paramType.getId());
            inParams.addValue("in_type", paramType.getParamType());
            inParams.addValue("in_weightage", paramType.getWeightage());
            inParams.addValue("in_status", paramType.getStatus());
            inParams.addValue("in_do_commit", "Y");
            
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.saveParamTypes() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    Long id =(Long) outMap.get("out_f_id");
                    response.setData(id);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }

        }catch (Exception e) {
            LOGGER.info("ToolDaoImpl.saveParamTypes Exception Occured :: ",e);
        }
        return response;
    }
    
    public ResponseBean<List<ParamType>> getParamTypes(Long paramTypeId,String type) {
        ResponseBean<List<ParamType>> response = new ResponseBean<List<ParamType>>();        
        try {
            LOGGER.info("ToolDaoImpl.getParamTypes :: "+ paramTypeId + " type "+type);
            String procName = "p_get_param_types_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", paramTypeId);
            inParams.addValue("in_type", type);
            
            proc.returningResultSet("paramTypes", new RowMapper<ParamType>() {
                @Override                
                public ParamType mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ParamType paramType = new ParamType();
                    paramType.setId(rs.getLong("f_id"));
                    paramType.setParamType(rs.getString("f_type"));
                    paramType.setWeightage(rs.getInt("f_weightage"));
                    paramType.setStatus(rs.getString("f_status"));
                    return paramType;
                }
            });
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.getParamTypes() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    List<ParamType> paramTypeList = (List<ParamType>) outMap.get("paramTypes");
                    response.setData(paramTypeList);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }
            
        } catch(Exception e) {
            LOGGER.info("ToolDaoImpl.getDesignations :: Exception occured",e);
            
        }
        return response;        
    }
    
    public ResponseBean<Long> saveRatingParams(RatingParams ratingParams) {
        ResponseBean<Long> response = new ResponseBean<Long>();
        try {
            LOGGER.info("ToolDaoImpl.saveRatingParams :: "+ratingParams);
            String procName = "p_save_rating_params_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", ratingParams.getId());
            inParams.addValue("in_type_id", ratingParams.getTypeId());
            inParams.addValue("in_rating_param", ratingParams.getParam());
            inParams.addValue("in_status", ratingParams.getStatus());
            inParams.addValue("in_do_commit", "Y");
            
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.saveRatingParams() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    Long id =(Long) outMap.get("out_f_id");
                    response.setData(id);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }

        }catch (Exception e) {
            LOGGER.info("ToolDaoImpl.saveRatingParams Exception Occured :: ",e);
        }
        return response;
    }
    
    public ResponseBean<List<TypeLevelParams>> getRatingParams(Long paramId, Long typeId,String param) {
        ResponseBean<List<TypeLevelParams>> response = new ResponseBean<List<TypeLevelParams>>();        
        try {
            LOGGER.info("ToolDaoImpl.getRatingParams :: typeId "+ typeId + " param "+param+ " paramId"+paramId);
            String procName = "p_get_rating_params_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", paramId);
            inParams.addValue("in_type_id", typeId);
            inParams.addValue("in_param", param);
            
            proc.returningResultSet("ratingParams", new RowMapper<RatingParams>() {
                @Override                
                public RatingParams mapRow(ResultSet rs, int rowNum) throws SQLException {
                    RatingParams ratingParams = new RatingParams();
                    ratingParams.setId(rs.getLong("f_id"));
                    ratingParams.setParam(rs.getString("f_rating_param"));
                    ratingParams.setTypeId(rs.getLong("f_type_id"));
                    ratingParams.setStatus(rs.getString("f_status"));
                    ratingParams.setParamType(rs.getString("f_type"));
                    return ratingParams;
                }
            });
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.getRatingParams() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    List<RatingParams> ratingParamsList = (List<RatingParams>) outMap.get("ratingParams");
                    List<TypeLevelParams> typeParams = mapRatingParams(ratingParamsList);
                    response.setData(typeParams);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }
            
        } catch(Exception e) {
            LOGGER.info("ToolDaoImpl.getRatingParams :: Exception occured",e);
            
        }
        return response;        
    }
    
    private List<TypeLevelParams> mapRatingParams(List<RatingParams> paramsList) {
        Map<String,List<RatingParams>> map = new HashMap<String,List<RatingParams>>();
        List<TypeLevelParams> typeParamsList = new ArrayList<TypeLevelParams>();
        if(!CollectionUtils.isEmpty(paramsList)) {            
            for(RatingParams param : paramsList) {
                if(map.get(param.getParamType())!=null) {
                    TypeLevelParams typeLevelParam = new TypeLevelParams(); 
                    typeLevelParam.setParamType(param.getParamType());
                    List<RatingParams> ratingParams = 
                            paramsList.stream().filter(p -> p.getTypeId().equals(param.getTypeId())).collect(Collectors.toList());
                    typeLevelParam.setParamList(ratingParams);
                    typeParamsList.add(typeLevelParam);
                    map.put(param.getParamType(), ratingParams);
                            
                }                
                
            }
        }
        return typeParamsList;
    }
    
    
    public ResponseBean<Long> saveParamWeightage(ParamWeightage paramWeightage) {
        ResponseBean<Long> response = new ResponseBean<Long>();
        try {
            LOGGER.info("ToolDaoImpl.saveParamWeightage :: "+paramWeightage);
            String procName = "p_save_param_weightage_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_id", paramWeightage.getId());
            inParams.addValue("in_param_id", paramWeightage.getParamId());
            inParams.addValue("in_team_id", paramWeightage.getTeamId());
            inParams.addValue("in_designation_id", paramWeightage.getDesignationId());
            inParams.addValue("in_weightage", paramWeightage.getScore());
            inParams.addValue("in_do_commit", "Y");
            
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.saveParamWeightage() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    Long id =(Long) outMap.get("out_f_id");
                    response.setData(id);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }

        }catch (Exception e) {
            LOGGER.info("ToolDaoImpl.saveParamWeightage Exception Occured :: ",e);
        }
        return response;
    }
    
    public ResponseBean<List<ParamWeightage>> getParamWeightage(Long paramId, Long teamId,Long designationId) {
        ResponseBean<List<ParamWeightage>> response = new ResponseBean<List<ParamWeightage>>();        
        try {
            LOGGER.info("ToolDaoImpl.getParamWeightage :: teamId "+ teamId + " designationId "+designationId+ " paramId"+paramId);
            String procName = "p_get_param_weightage_v1dot0";
            LOGGER.info("Executing proc :: " + procName);
            SimpleJdbcCall proc = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procName);
            MapSqlParameterSource inParams = new MapSqlParameterSource();
            inParams.addValue("in_param_id", paramId);
            inParams.addValue("in_team_id", teamId);
            inParams.addValue("in_designation_id", designationId);
            
            proc.returningResultSet("paramWeightages", new RowMapper<ParamWeightage>() {
                @Override                
                public ParamWeightage mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ParamWeightage paramWeightage = new ParamWeightage();
                    paramWeightage.setId(rs.getLong("f_id"));
                    paramWeightage.setParamId(rs.getLong("f_param_id"));
                    paramWeightage.setTeamId(rs.getLong("f_team_id"));
                    paramWeightage.setDesignationId(rs.getLong("f_designation_id"));
                    paramWeightage.setScore(rs.getInt("f_weightage"));
                    return paramWeightage;
                }
            });
            Map<String, Object> outMap = proc.execute(inParams);
            
            LOGGER.info("ToolDaoImpl.getParamWeightage() - outMap : " + outMap);
            if(outMap!=null) {
                String status = String.valueOf(outMap.get("out_status"));    
                if (status.equals("Y")) {
                    List<ParamWeightage> paramsList = (List<ParamWeightage>) outMap.get("paramWeightages");
                    response.setData(paramsList);
                    response.setStatus("success");
                } else {
                    response.setErrorCode((String) outMap.get("out_error_code")); 
                    response.setErrorMessage((String) outMap.get("out_error_msg"));
                }
            }
            
        } catch(Exception e) {
            LOGGER.info("ToolDaoImpl.getParamWeightage :: Exception occured",e);
            
        }
        return response;        
    }
    

}
