package com.example.mypetyourpet.service;

import com.example.mypetyourpet.model.Pet;
import com.example.mypetyourpet.model.PetProfileStatus;
import com.example.mypetyourpet.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

//    public PetService(PetRepository petRepository) {
//        this.petRepository = petRepository;
//    }

    public Pet createPet(Pet pet){
        if(petRepository.findPetByPetNameAndCustomerId(pet.getPetName(), pet.getCustomerId()).isPresent()){
            throw new DataIntegrityViolationException("This Pet is already listed");
        }
        return petRepository.save(pet);
    }

    public List<Pet> getListOfPets(PetProfileStatus petProfileStatus) {
        return petRepository.findPetListByPetProfileStatus(petProfileStatus);
    }

    public List<Pet> petList(Long customerId, String petName, PetProfileStatus petProfileStatus) {
        if(customerId == null){
            return petRepository.findPetListByPetProfileStatus(petProfileStatus);
        }
        else{
            return petRepository.findPetListByPetNameAndCustomerId(petName, customerId);
        }
    }

//    public Pet updatePet(Long petId, String petBehavior, boolean dewormingUpToDate,
//                                 boolean vaccinationUpToDate, double petFee, PetProfileStatus petProfileStatus){
//        if(!petRepository.findPetByCustomerId(petId).isPresent()){
//            throw new EntityNotFoundException("Error finding your pet");
//        }
//        Pet existingPet = petRepository.findPetByCustomerId(petId).get();
//        existingPet.setPetBehavior(petBehavior);
//        existingPet.setDewormingUpToDate(dewormingUpToDate);
//        existingPet.setVaccinationUpToDate(vaccinationUpToDate);
//        existingPet.setPetFee(petFee);
//        existingPet.setPetProfileStatus(petProfileStatus);
//        return petRepository.save(existingPet);
//    }

    public Pet updatePet(Long petId, Pet updatedPet){
        if(!petRepository.findPetByCustomerId(petId).isPresent()){
            throw new EntityNotFoundException("Error finding your pet");
        }
        Pet existingPet = petRepository.findPetByCustomerId(petId).get();
        existingPet.setPetBehavior(updatedPet.getPetBehavior());
        existingPet.setDewormingUpToDate(updatedPet.isDewormingUpToDate());
        existingPet.setVaccinationUpToDate(updatedPet.isVaccinationUpToDate());
        existingPet.setPetFee(updatedPet.getPetFee());
        existingPet.setPetProfileStatus(updatedPet.getPetProfileStatus());
        return petRepository.save(existingPet);
    }

    public Pet updatePetPictureUrl(Long petId, String imageUrl){
        if(!petRepository.findPetByPetId(petId).isPresent()){
            throw new EntityNotFoundException("Error finding your pet");
        }
        Pet existingPet = petRepository.findPetByCustomerId(petId).get();
        existingPet.setProfilePicture(imageUrl);
        return petRepository.save(existingPet);
    }

    public void deletePetByPetId(Long petId){
        if(!petRepository.existsById(petId)){
            throw new EntityNotFoundException("Pet with id " + petId + " does not exist");
        }
        petRepository.deleteById(petId);
    }

    public Pet getPetById(Long petId){
        if(!petRepository.findPetByPetId(petId).isPresent()){
            throw new EntityNotFoundException("Pet with id " + petId + " does not exist");
        }
        return petRepository.findPetByPetId(petId).get();
    }

//    public Pet updatePet(Long petId, String petName, String petBehavior, boolean dewormingUpToDate,
//                         boolean vaccinationUpToDate, double petFee, PetProfileStatus petProfileStatus,
//                         Long customerId){
//        if(!petRepository.findOpPetByPetNameAndCustomerId(petName, customerId).isPresent()){
//            throw new EntityNotFoundException("Error finding your pet");
//        }
//        Pet existingPet = petRepository.findPetByPetNameAndCustomerId(petName, customerId);
//        existingPet.setPetBehavior(petBehavior);
//        existingPet.setDewormingUpToDate(dewormingUpToDate);
//        existingPet.setVaccinationUpToDate(vaccinationUpToDate);
//        existingPet.setPetFee(petFee);
//        existingPet.setPetProfileStatus(petProfileStatus);
//        return petRepository.save(existingPet);
//    }
//
//    public Pet updatePetPicture(Long petId, String petName, String profilePicture, Long customerId){
//        if(!petRepository.findOpPetByPetNameAndCustomerId(petName, customerId).isPresent()){
//            throw new EntityNotFoundException("Error finding your pet");
//        }
//        Pet existingPet = petRepository.findPetByPetNameAndCustomerId(petName, customerId);
//        existingPet.setProfilePicture(profilePicture);
//        return petRepository.save(existingPet);
//    }
}
