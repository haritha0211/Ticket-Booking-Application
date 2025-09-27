package com.bookingapp.service;

import com.bookingapp.dto.AdminDTO;
import com.bookingapp.entity.Admin;
import com.bookingapp.exception.ResourceNotFoundException;
import com.bookingapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AdminDTO getAdminById(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + adminId));
        return convertToDTO(admin);
    }

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        if (adminRepository.existsByEmail(adminDTO.getEmail())) {
            throw new RuntimeException("Admin with email already exists");
        }

        Admin admin = convertToEntity(adminDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return convertToDTO(savedAdmin);
    }

    public AdminDTO updateAdmin(Long adminId, AdminDTO adminDTO) {
        Admin existingAdmin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + adminId));

        existingAdmin.setAdminName(adminDTO.getAdminName());
        existingAdmin.setAdminContact(adminDTO.getAdminContact());
        existingAdmin.setEmail(adminDTO.getEmail());
        if (adminDTO.getPassword() != null && !adminDTO.getPassword().isEmpty()) {
            existingAdmin.setPassword(adminDTO.getPassword());
        }

        Admin updatedAdmin = adminRepository.save(existingAdmin);
        return convertToDTO(updatedAdmin);
    }

    public void deleteAdmin(Long adminId) {
        if (!adminRepository.existsById(adminId)) {
            throw new ResourceNotFoundException("Admin not found with id: " + adminId);
        }
        adminRepository.deleteById(adminId);
    }

    public AdminDTO signIn(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(email, password);
        if (admin.isPresent()) {
            return convertToDTO(admin.get());
        }
        throw new RuntimeException("Invalid email or password");
    }

    private AdminDTO convertToDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setAdminId(admin.getAdminId());
        dto.setAdminName(admin.getAdminName());
        dto.setAdminContact(admin.getAdminContact());
        dto.setEmail(admin.getEmail());
        return dto;
    }

    private Admin convertToEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setAdminName(dto.getAdminName());
        admin.setAdminContact(dto.getAdminContact());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        return admin;
    }
}