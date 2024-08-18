package com.github.evgenius1424.spring_boot_playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.evgenius1424.spring_boot_playground.entity.Item;
import com.github.evgenius1424.spring_boot_playground.mapper.BrandItemsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringBootPlaygroundApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BrandItemsMapper brandItemsMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

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

        List<BrandItemsProjection> projections = itemRepository.getBrandItems();
        List<BrandItems> items = projections.stream().map(brandItemsMapper::toBrandItems).toList();
        System.out.println();
    }

    @Test
    void createAndRetrieveItemTest() throws Exception {
        CreateItemRequest newItem = new CreateItemRequest();
        newItem.setName("Laptop");
        newItem.setBrand("Dell");

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newItem)))
                .andExpect(status().isOk());

        String responseContent = mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<BrandItems> items = objectMapper.readValue(responseContent,
                objectMapper.getTypeFactory().constructCollectionType(List.class, BrandItems.class));

        assertThat(items).isNotEmpty();
    }
}