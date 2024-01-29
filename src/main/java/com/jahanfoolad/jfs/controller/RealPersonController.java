package com.jahanfoolad.jfs.controller;

import com.jahanfoolad.jfs.domain.RealPerson;
import com.jahanfoolad.jfs.domain.ResponseModel;
import com.jahanfoolad.jfs.domain.dto.RealPersonDto;
import com.jahanfoolad.jfs.service.RealPersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/realperson")
@RestController
public class RealPersonController {

    @Autowired
    RealPersonService realPersonService;

    @Autowired
    ResponseModel responseModel;

    @GetMapping(path = "/test")
    public String testMethod() {
        return "Hello.";
    }

    @GetMapping("/getall")
    public ResponseModel getRealPersons(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        responseModel.clear();
        try {
            log.info("GET ALL USERS");
            List<RealPerson> users = realPersonService.getRealPersons();
            responseModel.setContents(users);
            responseModel.setResult(1);
            responseModel.setRecordCount(users.size());
            responseModel.setStatus(httpServletResponse.getStatus());
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            responseModel.setError(dataIntegrityViolationException.getMessage());
        } catch (Exception e) {
            responseModel.setError(e.getMessage());
        } finally {
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(0);
        }
        return responseModel;
    }

    @GetMapping(path = "/getbyid")
//    public String getUserById(@PathVariable Long userid) {
    public ResponseModel getRealPersonById(@RequestParam Long id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        , HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse
        try {

            log.info("-------------");

            responseModel.clear();
            responseModel.setContent(realPersonService.getRealPersonByUserId(id));
            responseModel.setResult(1);
//            responseModel.set
//        return userService.getUserByUserId(userid);
//        } catch (AccessDeniedException accessDeniedException) {
//            responseModel.setResult(0);
//            responseModel.setSystemError(accessDeniedException.getMessage());
//            responseModel.setError("properties");

//            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            responseModel.setError(dataIntegrityViolationException.getMessage());
        } catch (Exception e) {
            responseModel.setError(e.getMessage());
        } finally {
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(0);
        }
        return responseModel;
    }

    @PostMapping("/save")
    public ResponseModel createRealPerson(@RequestBody RealPersonDto realPersonDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        try {
            responseModel.clear();
            responseModel.setContent(realPersonService.createRealPerson(realPersonDto));
            responseModel.setResult(1);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            responseModel.setError(dataIntegrityViolationException.getMessage());
        } catch (Exception e) {
            responseModel.setError(e.getMessage());
        } finally {
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(0);
        }
        return responseModel;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseModel deleteRealPerson(@PathVariable("id") Long id, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        responseModel.clear();
        try {
            log.info("DELETE USER");
            realPersonService.deleteRealPerson(id);
            responseModel.clear();
            responseModel.setResult(1);
        } catch (Exception e) {
            responseModel.setError(e.getMessage());
        } finally {
            responseModel.setStatus(httpServletResponse.getStatus());
            responseModel.setResult(0);
        }
        return responseModel;
    }


}
