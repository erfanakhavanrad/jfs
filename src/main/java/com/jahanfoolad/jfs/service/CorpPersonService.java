package com.jahanfoolad.jfs.service;

import com.jahanfoolad.jfs.domain.CorpPerson;
import com.jahanfoolad.jfs.domain.dto.ContactDto;
import com.jahanfoolad.jfs.domain.dto.CorpPersonDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

public interface CorpPersonService {
    Page<CorpPerson> getCorpPeople(Integer pageNo, Integer perPage) throws Exception;

    CorpPerson getCorpPersonById(Long id) throws Exception;

    Page<CorpPerson> findByProvince(ContactDto contactDto, Integer pageNo, Integer perPage) throws Exception;

    Page<CorpPerson> findByCity(ContactDto contactDto, Integer pageNo, Integer perPage);

    CorpPerson createCorpPerson(CorpPersonDto corpPersonDto, HttpServletRequest httpServletRequest) throws Exception;

    CorpPerson updateCorpPerson(CorpPersonDto corpPersonDto, HttpServletRequest httpServletRequest) throws Exception;

    Page<CorpPerson> findByContact(ContactDto contactDto, Integer pageNo, Integer perPage) throws Exception;

    void deleteCorpPerson(Long id);

    CorpPerson login(CorpPerson corpPerson, HttpServletRequest httpServletRequest) throws Exception;

}
