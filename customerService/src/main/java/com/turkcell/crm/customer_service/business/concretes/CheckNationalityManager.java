package com.turkcell.crm.customer_service.business.concretes;

import com.turkcell.crm.customer_service.adapters.mernis.CheckNationalityDTO;
import com.turkcell.crm.customer_service.adapters.mernis.CheckNationalityService;
import org.springframework.stereotype.Service;

@Service
public class CheckNationalityManager implements CheckNationalityService {
    @Override
    public boolean validate(CheckNationalityDTO checkNationalityDTO) {
        return true;
    }
}
