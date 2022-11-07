package com.sphy.hotelmanagementapplication.repositories;

import com.sphy.hotelmanagementapplication.domain.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/***
 * created by gp
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {
}
