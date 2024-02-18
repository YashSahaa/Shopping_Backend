package com.shoppingdbapi.database.api.Repository;

import com.shoppingdbapi.database.api.Model.Acl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AclRepo extends JpaRepository<Acl, UUID> {

    @Query(value = "select * from acl where requestor=:requestor and operation=:operation",nativeQuery = true)
    public Acl getConfiguration(String requestor,String operation);
}
