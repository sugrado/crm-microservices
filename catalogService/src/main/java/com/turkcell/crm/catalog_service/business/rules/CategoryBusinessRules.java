package com.turkcell.crm.catalog_service.business.rules;

import com.turkcell.crm.catalog_service.business.constants.messages.Messages;
import com.turkcell.crm.catalog_service.core.business.abstracts.MessageService;
import com.turkcell.crm.catalog_service.data_access.abstracts.CategoryRepository;
import com.turkcell.crm.catalog_service.entities.concretes.Category;
import com.turkcell.crm.common.shared.exceptions.types.BusinessException;
import com.turkcell.crm.common.shared.exceptions.types.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryBusinessRules {

    private final CategoryRepository categoryRepository;
    private final MessageService messageService;

    public void categoryShouldBeExist(Optional<Category> optionalCategory) {
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException(messageService.getMessage(Messages.CategoryMessages.NOT_FOUND));
        }
    }

    public void categoryNameCannotBeDuplicatedWhenInserted(String name) {
        Optional<Category> optionalCategory = this.categoryRepository.findByName(name);
        if (optionalCategory.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.CategoryMessages.ALREADY_EXISTS));
        }
    }

    public void categoryNameCannotBeDuplicatedWhenUpdated(int id, String name) {
        Optional<Category> optionalCategory = this.categoryRepository.findByNameAndIdIsNot(name, id);
        if (optionalCategory.isPresent()) {
            throw new BusinessException(messageService.getMessage(Messages.CategoryMessages.ALREADY_EXISTS));
        }
    }

    public void categoryShouldNotBeDeleted(Optional<Category> category) {
        if (category.get().getDeletedDate() != null) {
            throw new BusinessException(messageService.getMessage(Messages.CategoryMessages.DELETED));
        }
    }
}
