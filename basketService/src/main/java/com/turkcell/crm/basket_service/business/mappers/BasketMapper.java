package com.turkcell.crm.basket_service.business.mappers;

import com.turkcell.crm.basket_service.entities.concretes.BasketItem;
import com.turkcell.crm.common.shared.dtos.baskets.GetProductsFromBasketDto;
import com.turkcell.crm.common.shared.mapping.MapstructService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructService.class)
public interface BasketMapper {
    List<GetProductsFromBasketDto> toGetProductsFromBasketDto(List<BasketItem> items);
}
