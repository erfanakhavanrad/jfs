package com.jahanfoolad.jfs.jpaRepository;

import com.jahanfoolad.jfs.domain.Contact;
import com.jahanfoolad.jfs.domain.RealPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealPersonRepository extends JpaRepository<RealPerson, Long> {
    //    @Query(value = "SELECT * FROM real_person", nativeQuery = true)
//    @Query("SELECT rp FROM RealPerson AS rp WHERE rp.cellPhone = :cellPhone")
//    RealPerson findUserByPhoneNumber(@Param("cellPhone") String cellPhone);
    RealPerson findByCellPhone(@Param("cellPhone") String cellPhone);

    RealPerson findByUserName(@Param("userName") String userName);
//    @Query("SELECT rp FROM RealPerson AS rp WHERE rp.password = :password")
//    RealPerson validateUserPassword(@Param("password") String password);

    Page<RealPerson> findAllByContactListIn(List<Contact> contactList, Pageable pageable);

}
