package com.github.evgenius1424.spring_boot_playground.mapper;

import com.github.evgenius1424.spring_boot_playground.BrandItems;
import com.github.evgenius1424.spring_boot_playground.BrandItemsProjection;
import com.github.evgenius1424.spring_boot_playground.ItemDetails;
import com.github.evgenius1424.spring_boot_playground.JsonMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandItemsMapper {


    @Mapping(target = "items", source = "itemsJson", qualifiedByName = "mapJsonToItemDetailsList")
    BrandItems toBrandItems(BrandItemsProjection projection);

    @Named("mapJsonToItemDetailsList")
    default List<ItemDetails> mapJsonToItemDetailsList(String itemsJson) {
        return JsonMapperUtil.mapJsonToList(itemsJson, ItemDetails.class);
    }
}