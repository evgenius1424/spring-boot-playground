package com.github.evgenius1424.spring_boot_playground;

import com.github.evgenius1424.spring_boot_playground.entity.Item;
import com.github.evgenius1424.spring_boot_playground.mapper.BrandItemsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringBootPlaygroundApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCustomRepository itemCustomRepository;

    @Autowired
    private BrandItemsMapper brandItemsMapper;

    @Test
    void contextLoads() {
        Item firstItem = new Item();
        firstItem.setName("iPhone");
        firstItem.setBrand("Apple");

        Item secondItem = new Item();
        secondItem.setName("Car");
        secondItem.setBrand("BMW");

        itemRepository.save(firstItem);
        itemRepository.save(secondItem);

        List<BrandItemsProjection> projections = itemCustomRepository.getBrandItems();
        List<BrandItems> items = projections.stream().map(brandItemsMapper::toBrandItems).toList();
        System.out.println();
    }
}