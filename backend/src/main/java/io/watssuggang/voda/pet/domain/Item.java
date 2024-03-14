package io.watssuggang.voda.pet.domain;

import io.watssuggang.voda.common.domain.BaseEntity;
import io.watssuggang.voda.common.enums.ItemCategory;
import io.watssuggang.voda.pet.dto.req.ItemPostRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.CHAR, name = "item_type")
@DiscriminatorValue("i")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    private String itemImageUrl;

    private Integer itemPrice;

    @Column(columnDefinition = "char(30)")
    private String itemName;

    public Item(String itemImageUrl, Integer itemPrice, String itemName) {
        this.itemImageUrl = itemImageUrl;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
    }

    public static Item toEntity(ItemPostRequest postRequest) {
        switch (ItemCategory.valueOf(postRequest.getCategory().toUpperCase())) {
            case EFFECT -> {
                return Effect.builder()
                        .itemName(postRequest.getName())
                        .itemPrice(postRequest.getPrice())
                        .itemImageUrl(postRequest.getImgUrl())
                        .build();
            }
            case FOOD -> {
                return Food.builder()
                        .itemName(postRequest.getName())
                        .itemPrice(postRequest.getPrice())
                        .itemImageUrl(postRequest.getImgUrl())
                        .build();
            }
        }
        throw new RuntimeException();
    }
}
