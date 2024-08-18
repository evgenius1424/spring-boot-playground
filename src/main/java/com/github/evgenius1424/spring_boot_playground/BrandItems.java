package com.github.evgenius1424.spring_boot_playground;

import lombok.Value;

import java.util.List;

@Value
public class BrandItems {
    String brand;
    List<ItemDetails> items;
}
