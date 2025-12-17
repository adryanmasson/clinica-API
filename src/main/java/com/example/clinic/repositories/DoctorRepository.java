package com.example.clinic.repositories;

import com.example.clinic.models.Doctor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    boolean existsByCrm(String medicalLicense);

    List<Doctor> findBySpecialtyId(Integer specialtyId);
}
