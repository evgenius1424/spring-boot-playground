package com.github.evgenius1424.spring_boot_playground;

import com.github.evgenius1424.spring_boot_playground.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}