package com.turkcell.crm.catalog_service.business.dtos.responses.property;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CreatedPropertyResponse {
    int id;
    String categoryName;
    String name;
    LocalDateTime createdDate;
}
