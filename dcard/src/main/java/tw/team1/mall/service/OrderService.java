package tw.team1.mall.service;

import tw.team1.mall.dto.CreateOrderRequest;
import tw.team1.mall.dto.OrderQueryParams;
import tw.team1.mall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders (OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer creaOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
