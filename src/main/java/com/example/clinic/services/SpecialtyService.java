package com.example.clinic.services;

import com.example.clinic.models.Specialty;
import com.example.clinic.repositories.SpecialtyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<Specialty> listSpecialties() {
        return specialtyRepository.findAll();
    }

    public Specialty findSpecialtyById(Integer id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Specialty not found with id " + id));
    }

    public Specialty createSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    public Specialty updateSpecialty(Integer id, Specialty updatedSpecialty) {
        Specialty existing = findSpecialtyById(id);
        existing.setName(updatedSpecialty.getName());
        existing.setDescription(updatedSpecialty.getDescription());
        return specialtyRepository.save(existing);
    }

    public void deleteSpecialty(Integer id) {
        Specialty existing = findSpecialtyById(id);
        specialtyRepository.delete(existing);
    }

}
