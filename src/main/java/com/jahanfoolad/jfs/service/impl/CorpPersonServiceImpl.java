package com.jahanfoolad.jfs.service.impl;

import com.jahanfoolad.jfs.JfsApplication;
import com.jahanfoolad.jfs.domain.Contact;
import com.jahanfoolad.jfs.domain.CorpPerson;
import com.jahanfoolad.jfs.domain.ResponseModel;
import com.jahanfoolad.jfs.domain.dto.ContactDto;
import com.jahanfoolad.jfs.domain.dto.CorpPersonDto;
import com.jahanfoolad.jfs.jpaRepository.ContactRepository;
import com.jahanfoolad.jfs.jpaRepository.CorpPersonRepository;
import com.jahanfoolad.jfs.service.CorpPersonService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CorpPersonServiceImpl implements CorpPersonService {

    @Autowired
    CorpPersonRepository corpPersonRepository;

    @Autowired
    ContactRepository contactRepository;

    @Resource(name = "faMessageSource")
    private MessageSource faMessageSource;

    @Autowired
    ResponseModel responseModel;

    @Override
    public Page<CorpPerson> getCorpPeople(Integer pageNo, Integer perPage) {
        return corpPersonRepository.findAll(JfsApplication.createPagination(pageNo, perPage));
    }

    @Override
    public CorpPerson getCorpPersonById(Long id) throws Exception {
        return corpPersonRepository.findById(id).orElseThrow(() -> new Exception(faMessageSource.getMessage("CORP_PERSON_NOT_FOUND", null, Locale.ENGLISH)));
    }

    @Override
    public Page<CorpPerson> findByProvince(ContactDto contactDto, Integer pageNo, Integer perPage) {
        List<Contact> contacts = contactRepository.findAllByProvince(contactDto.getProvince());
        return corpPersonRepository.findAllByContactListIn(contacts, JfsApplication.createPagination(pageNo, perPage));
    }

    @Override
    public Page<CorpPerson> findByCity(ContactDto contactDto, Integer pageNo, Integer perPage) {
        List<Contact> contacts = contactRepository.findAllByCity(contactDto.getCity());
        return corpPersonRepository.findAllByContactListIn(contacts, JfsApplication.createPagination(pageNo, perPage));
    }


    @Override
    public CorpPerson createCorpPerson(CorpPersonDto corpPersonDto, HttpServletRequest httpServletRequest) {
        ModelMapper modelMapper = new ModelMapper();
        CorpPerson corpPerson = modelMapper.map(corpPersonDto, CorpPerson.class);
        return corpPersonRepository.save(corpPerson);
    }

    @Override
    public CorpPerson updateCorpPerson(CorpPersonDto corpPersonDto, HttpServletRequest httpServletRequest) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        CorpPerson oldCorpPerson = getCorpPersonById(corpPersonDto.getId());
        CorpPerson newCorpPerson = modelMapper.map(corpPersonDto, CorpPerson.class);

        responseModel.clear();
        CorpPerson updated = (CorpPerson) responseModel.merge(oldCorpPerson, newCorpPerson);

        if (newCorpPerson.getContactList() != null && !newCorpPerson.getContactList().isEmpty()) {
            updated.setContactList(newCorpPerson.getContactList());
        }

        return corpPersonRepository.save(updated);
    }

    @Override
    public Page<CorpPerson> findByContact(ContactDto contactDto, Integer pageNo, Integer perPage) {
        ModelMapper modelMapper = new ModelMapper();
        Contact contact = modelMapper.map(contactDto, Contact.class);
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        return corpPersonRepository.findAllByContactListIn(contactList, JfsApplication.createPagination(pageNo, perPage));
    }

    @Override
    public void deleteCorpPerson(Long id) {
        corpPersonRepository.deleteById(id);
    }

    @Override
    public CorpPerson login(CorpPerson corpPerson, HttpServletRequest httpServletRequest) throws Exception {
        CorpPerson userByPhoneNumber = corpPersonRepository.findByCellPhone(corpPerson.getCellPhone());
        if (userByPhoneNumber == null)
            throw new Exception(faMessageSource.getMessage("NOT_FOUND", null, Locale.ENGLISH));
        if (!userByPhoneNumber.getPassword().equals(corpPerson.getPassword())) {
            throw new Exception(faMessageSource.getMessage("INCORRECT_PASSWORD", null, Locale.ENGLISH));
        }
        return userByPhoneNumber;
    }


}
