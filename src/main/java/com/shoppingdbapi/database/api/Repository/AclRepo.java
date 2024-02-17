package com.shoppingdbapi.database.api.Repository;

import com.shoppingdbapi.database.api.Model.Acl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AclRepo extends JpaRepository<Acl, UUID> {
}
