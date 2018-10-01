package com.tm.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tm.Bean.Designations;
import com.tm.Bean.ParamType;
import com.tm.Bean.ParamWeightage;
import com.tm.Bean.RatingParams;
import com.tm.Bean.ResponseBean;
import com.tm.Bean.Teams;
import com.tm.Bean.Users;
import com.tm.Service.ToolService;

@RestController
@RequestMapping("/controler")
public class ToolController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolController.class);
    
    @Autowired
    ToolService toolService;
    
    @RequestMapping(value = { "/saveTeams" }, method = { RequestMethod.POST })
    public @ResponseBody ResponseBean<Long> saveTeams(@RequestBody Teams team) {
        LOGGER.info("ToolController.saveTeams :: "+team);
        ResponseBean<Long> response = toolService.saveTeams(team);
        return response;
    }
    
    @RequestMapping(value = { "/getTeams" }, method = { RequestMethod.GET })
    public @ResponseBody ResponseBean<List<Teams>> getTeams(@RequestParam(value = "teamId", required = false) Long teamId,
            @RequestParam(value = "teamName", required = false) String teamName) {
        LOGGER.info("ToolController.getTeams :: "+teamId +" "+teamName);
        ResponseBean<List<Teams>> response = toolService.getTeams(teamId, teamName);
        return response;
    }
    
    @RequestMapping(value = { "/saveDesignations" }, method = { RequestMethod.POST })
    public @ResponseBody ResponseBean<Long> saveDesignations(@RequestBody Designations designation) {
        LOGGER.info("ToolController.saveDesignations :: "+designation);
        return toolService.saveDesignations(designation);
    }
    
    @RequestMapping(value = { "/getDesignations" }, method = { RequestMethod.GET })
    public @ResponseBody ResponseBean<List<Designations>> getDesignations(
            @RequestParam(value = "designationId",required = false)Long designationId,
            @RequestParam(value = "designation",required = false)String designation) {
        LOGGER.info("ToolController.getDesignations :: designationId "+designationId+" designation"+designation);
        return toolService.getDesignations(designationId, designation);
        
    }
    
    public @ResponseBody ResponseBean<Long> saveUsers(@RequestBody Users user) {
        LOGGER.info("ToolController.saveUsers :: "+user);
        return toolService.saveUsers(user);
    }
    
    public @ResponseBody ResponseBean<List<Users>> getUsers(
            @RequestParam(value = "id",required = false)Long id,
            @RequestParam(value = "email",required = false)String email,
            @RequestParam(value = "mobile",required = false)String mobile,
            @RequestParam(value = "teamId",required = false)Long teamId,
            @RequestParam(value = "designationId",required = false)Long designationId) {
        
        LOGGER.info("ToolController.getUsers :: id "+id+" email "+email+" mobile "+mobile+" teamId "+teamId+" designationId "+designationId);
        return toolService.getUsers(id, email, mobile, teamId, designationId);
        
    }
    
    public @ResponseBody ResponseBean<Long> saveParamTypes(@RequestBody ParamType paramType) {
        LOGGER.info("ToolController.saveParamTypes :: "+paramType);
        return toolService.saveParamTypes(paramType);        
    }
    
    public @ResponseBody ResponseBean<List<ParamType>> getParamTypes(
            @RequestParam(value = "paramTypeId",required = false) Long paramTypeId,
            @RequestParam(value = "type",required = false)String type) {
        LOGGER.info("ToolController.getParamTypes :: paramTypeId "+paramTypeId +" type "+type);
        return toolService.getParamTypes(paramTypeId, type);
    }
    
    public @ResponseBody ResponseBean<Long> saveRatingParams(@RequestBody RatingParams ratingParams) {
        LOGGER.info("ToolController.saveRatingParams :: "+ratingParams);
        return toolService.saveRatingParams(ratingParams);
    }
    
    public @ResponseBody ResponseBean<List<RatingParams>> getRatingParams(
            @RequestParam(value = "paramId",required = false)Long paramId,
            @RequestParam(value = "typeId",required = false)Long typeId,
            @RequestParam(value = "param",required = false)String param) {
        LOGGER.info("ToolController.getRatingParams :: paramId "+paramId+" typeId "+typeId+" param "+param);
        return toolService.getRatingParams(paramId, typeId, param);
    }
    
    public @ResponseBody ResponseBean<Long> saveParamWeightage(@RequestBody ParamWeightage paramWeightage) {
        LOGGER.info("ToolController.saveParamWeightage :: "+paramWeightage);
        return toolService.saveParamWeightage(paramWeightage);
    }
    public @ResponseBody ResponseBean<List<ParamWeightage>> getParamWeightage(
            @RequestParam(value = "paramId",required = false)Long paramId,
            @RequestParam(value = "teamId",required = false)Long teamId,
            @RequestParam(value = "designationId",required = false)Long designationId) {
        LOGGER.info("ToolController.getParamWeightage :: paramId "+paramId+" teamId "+teamId+" designationId "+designationId);
        return toolService.getParamWeightage(paramId, teamId, designationId);
    }
    
}
