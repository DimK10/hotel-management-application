package com.sphy.hotelmanagementapplication.repositories;

import com.sphy.hotelmanagementapplication.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/***
 * created by gp
 */
@NoRepositoryBean
public interface UserRepository extends CrudRepository<User,Long> {
}
