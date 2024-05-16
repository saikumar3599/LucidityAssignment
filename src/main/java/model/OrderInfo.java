package model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OrderInfo implements Serializable {
    private String restaurantId;
    private String customerId;
}
