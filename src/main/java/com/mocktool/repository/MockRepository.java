package com.mocktool.repository;

import com.mocktool.model.Mock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MockRepository extends JpaRepository<Mock, Long> {
    Collection<Mock> findAllByServiceAndEnvironmentAndIsActive(String service, String environment, Boolean isActive);}
