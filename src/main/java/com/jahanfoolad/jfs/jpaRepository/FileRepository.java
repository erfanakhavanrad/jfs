package com.jahanfoolad.jfs.jpaRepository;

import com.jahanfoolad.jfs.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Long>{
}
