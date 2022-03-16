package com.java.back.end.casa.codigo.service;

import com.java.back.end.casa.codigo.dto.DTOConverter;
import com.java.back.end.casa.codigo.model.Shop;
import com.java.back.end.casa.codigo.repository.ShopRepository;
import dto.ItemDTO;
import dto.ProductDTO;
import dto.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository
                .findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository
                .findAllByDateGreaterThanEqual(shopDTO.getDate());
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        return shop.map(DTOConverter::convert).orElse(null);
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {

        if (userService.getUserByCpf(shopDTO.getUserIdentifier(), key) == null)
            return null;
        if (!validateProducts(shopDTO.getItems()))
            return null;

        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);

        return DTOConverter.convert(shop);
        //        shopDTO.setTotal(shopDTO.getItems().stream()
//                .map(ItemDTO::getPrice)
//                .reduce((float) 0, Float::sum));
//        Shop shop = Shop.convert(shopDTO);
//        shop.setDate(new Date());
//        shop = shopRepository.save(shop);
//        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());

            if (productDTO == null)
                return false;

            item.setPrice(productDTO.getPreco());
        }
        return true;
    }


}
