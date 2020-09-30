package com.tua.wanchalerm.user.repository;

import com.tua.wanchalerm.user.model.db.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, String>, JpaSpecificationExecutor<DeviceEntity> {

}