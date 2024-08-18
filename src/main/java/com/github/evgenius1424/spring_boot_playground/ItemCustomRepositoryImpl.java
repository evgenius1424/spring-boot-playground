package com.github.evgenius1424.spring_boot_playground;

import com.github.evgenius1424.spring_boot_playground.entity.Item;
import com.github.evgenius1424.spring_boot_playground.entity.Item_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Expression;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemCustomRepositoryImpl implements ItemCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BrandItemsProjection> getBrandItems() {
        var cb = entityManager.getCriteriaBuilder();
        var query = cb.createQuery(BrandItemsProjection.class);
        var root = query.from(Item.class);

        Expression<String> jsonExpression = cb.function("json_build_object",
                String.class,
                cb.literal("id"), root.get(Item_.ID),
                cb.literal("name"), root.get(Item_.NAME));

        Expression<String> jsonArrayAggExpression = cb.function("json_agg", String.class, jsonExpression);

        query.multiselect(
                root.get(Item_.BRAND),
                jsonArrayAggExpression
        ).groupBy(root.get(Item_.BRAND));

        return entityManager.createQuery(query).getResultList();
    }
}