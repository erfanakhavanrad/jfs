package com.jahanfoolad.jfs.jpaRepository;

import com.jahanfoolad.jfs.domain.RealPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealPersonRepository extends JpaRepository<RealPerson, Long> {
}