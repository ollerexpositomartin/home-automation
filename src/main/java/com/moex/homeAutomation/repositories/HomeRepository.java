package com.moex.homeAutomation.repositories;

import com.moex.homeAutomation.domain.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HomeRepository extends JpaRepository<Device,String> {
}
