package com.practice.library.services;

import com.practice.library.entities.Description;
import com.practice.library.repository.DescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DescriptionService {

    @Autowired
    private DescriptionRepo descriptionRepository;

    @Transactional
    public Description createDescription(String about) {

        Description description = new Description();
        description.setDescription(about);
        descriptionRepository.save(description);

        return description;
    }

    @Transactional
    public void deactivateDescription(Integer idDescription) {
        descriptionRepository.deactivateDescription(idDescription, LocalDate.now());
    }

    @Transactional
    public void editDescription(Integer idDescription, String about) {
        descriptionRepository.editDescription(idDescription, about);
    }
}
