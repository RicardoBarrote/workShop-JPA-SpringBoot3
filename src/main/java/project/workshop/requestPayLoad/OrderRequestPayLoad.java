package project.workshop.requestPayLoad;

import project.workshop.entities.User;
import project.workshop.enums.OrderStatus;

public record OrderRequestPayLoad(Integer id, OrderStatus orderStatus, User user) {
}
