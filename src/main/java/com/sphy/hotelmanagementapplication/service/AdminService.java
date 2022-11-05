package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import org.springframework.stereotype.Service;

/***
 * created by gp
 */
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /***
     * get an admin by his id
     * @param id the id of the admin to be found
     * @return the admin with the current id
     */
    public Admin getAdminById(Long id){
        return adminRepository.findById(id).orElse(null);
    }
}
