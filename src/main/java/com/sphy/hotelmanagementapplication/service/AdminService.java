package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.domain.Admin;
import com.sphy.hotelmanagementapplication.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id).orElse(null);
    }
}
