package com.turkcell.crm.customer_service.adapters.mernis;

import com.turkcell.crm.customer_service.out_services.MernisClient;
import lombok.AllArgsConstructor;

//@Service
@AllArgsConstructor
public class MernisAdapter implements CheckNationalityService {
    private final MernisClient mernisClient;

    @Override
    public boolean validate(CheckNationalityDTO checkNationalityDTO) {
        return mernisClient.TCKimlikNoDogrula(checkNationalityDTO.getNationalityId(),
                checkNationalityDTO.getFirstName().toUpperCase(),
                checkNationalityDTO.getLastName().toUpperCase(),
                checkNationalityDTO.getBirthYear());
    }
}
