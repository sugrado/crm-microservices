package com.turkcell.crm.catalog_service.business.dtos.responses.property;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record CreatedPropertyResponse(
        int id,
        int categoryId,
        String name,
        LocalDateTime createdDate
) {


}
