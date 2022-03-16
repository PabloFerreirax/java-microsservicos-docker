package com.java.back.end.casa.codigo.dto;

import com.java.back.end.casa.codigo.model.Item;
import com.java.back.end.casa.codigo.model.Shop;
import dto.ItemDTO;
import dto.ShopDTO;

import java.util.stream.Collectors;

public class DTOConverter {
    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(
                item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop.getItems().stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList()));
        return shopDTO;
    }
}
