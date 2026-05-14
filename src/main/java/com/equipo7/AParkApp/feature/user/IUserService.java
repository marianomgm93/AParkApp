package com.equipo7.AParkApp.feature.user;

import java.util.UUID;

public interface IUserService {
    List<ProductResponse> getAllUsers();

    ProductResponse getProductById(UUID userId);

    ProductResponse save(ProductRequest productRequest);

    void delete(UUID productId);
}
