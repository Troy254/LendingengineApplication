package com.peerlender.lendingengine.domain.repository;

import com.peerlender.lendingengine.domain.model.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUsers,String>{

}
