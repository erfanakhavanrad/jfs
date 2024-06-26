package com.jahanfoolad.jfs.service;

import com.jahanfoolad.jfs.domain.RealPerson;
import com.jahanfoolad.jfs.domain.ResponseModel;
import com.jahanfoolad.jfs.domain.dto.ContactDto;
import com.jahanfoolad.jfs.domain.dto.RealPersonDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

public interface RealPersonService {
    Page<RealPerson> getRealPeople(Integer pageNo, Integer perPage) throws Exception;

    RealPerson getRealPersonById(Long id) throws Exception;

    RealPerson getRealPersonByUsername(String userName) throws Exception;

    Page<RealPerson> findByProvince(ContactDto contactDto, Integer pageNo, Integer perPage) throws Exception;

    Page<RealPerson> findByCity(ContactDto contactDto, Integer pageNo, Integer perPage);

    RealPerson createRealPerson(RealPersonDto realPersonDto, HttpServletRequest request) throws Exception;

    RealPerson updateRealPerson(RealPersonDto realPersonDto, HttpServletRequest httpServletRequest) throws Exception;

    void deleteRealPerson(Long id) throws Exception;

    RealPerson findByMobile(RealPerson realPerson) throws Exception;

    void resetPass(String userName);

    void resetPassConfirm(String userName, String newPassword) throws Exception;

    ResponseModel login(RealPerson realPerson, HttpServletRequest request) throws Exception;

    void resetPass(RealPerson byMobile, String newPassword) throws Exception;

    void forgetPassword(String userName) throws Exception;

}
