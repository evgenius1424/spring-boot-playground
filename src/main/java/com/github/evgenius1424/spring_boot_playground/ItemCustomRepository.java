package com.github.evgenius1424.spring_boot_playground;

import java.util.List;

public interface ItemCustomRepository {
    List<BrandItemsProjection> getBrandItems();
}
