package project.workshop.requestPayLoad;

import java.time.LocalDateTime;

public record PaymentRequestPayLoad(Integer id, LocalDateTime moment, Integer orderId) {
}
