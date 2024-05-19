package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.data_access.abstracts.CategoryRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.common.exceptions.types.BusinessException;
import com.turkcell.crm.common.exceptions.types.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryBusinessRules {
    private final CategoryRepository categoryRepository;

    public void categoryShouldBeExist(Optional<Category> optionalCategory) {
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException(Messages.CategoryMessages.NOT_FOUND);
        }
    }

    public void categoryNameCannotBeDuplicatedWhenInserted(String name) {
        Optional<Category> optionalCategory = this.categoryRepository.findByName(name);
        if (optionalCategory.isPresent()) {
            throw new BusinessException(Messages.CategoryMessages.ALREADY_EXISTS);
        }
    }

    public void categoryNameCannotBeDuplicatedWhenUpdated(int id, String name) {
        Optional<Category> optionalCategory = this.categoryRepository.findByNameAndIdIsNot(name, id);
        if (optionalCategory.isPresent()) {
            throw new BusinessException(Messages.CategoryMessages.ALREADY_EXISTS);
        }
    }
}
