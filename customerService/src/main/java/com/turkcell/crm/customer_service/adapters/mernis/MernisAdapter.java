package com.turkcell.crm.customer_service.adapters.mernis;

import com.turkcell.crm.customer_service.out_services.MernisClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MernisAdapter implements CheckNationalityService {
    private final MernisClient mernisClient;

    @Override
    public boolean validate(CheckNationalityDTO checkNationalityDTO) {
        return mernisClient.checkNationality(checkNationalityDTO.getNationalityId(), checkNationalityDTO.getFirstName(), checkNationalityDTO.getLastName(), checkNationalityDTO.getBirthYear());
    }
}
