package model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAgent extends User{
    private List<OrderInfo> orderInfoList;
}
