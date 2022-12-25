package com.sphy.hotelmanagementapplication.repository;

import com.sphy.hotelmanagementapplication.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.NoRepositoryBean;


/***
 * created by gp
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
