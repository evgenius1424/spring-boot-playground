package com.github.evgenius1424.spring_boot_playground;

import com.github.evgenius1424.spring_boot_playground.entity.Item;
import com.github.evgenius1424.spring_boot_playground.mapper.BrandItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BrandItemsMapper brandItemsMapper;

    @GetMapping
    public List<BrandItems> getAllItems() {
        // Retrieve items from the repository
        List<BrandItemsProjection> projections = itemRepository.getBrandItems();

        // Map items to BrandItems DTO
        List<BrandItems> items = projections.stream()
                .map(brandItemsMapper::toBrandItems)
                .toList();

        // Return the mapped items
        return items;
    }

    @PostMapping
    public void createItem(@RequestBody CreateItemRequest createItemRequest) {
        // Convert the request DTO to an entity (assuming you have an Item entity)
        Item item = new Item();
        item.setName(createItemRequest.getName());
        item.setBrand(createItemRequest.getBrand());

        // Save the item using the repository
        item = itemRepository.save(item);
    }
}