package springbootlearning.service;

import springbootlearning.dto.PaymentProcessDto;

public interface PaymentInfoService {

    Boolean process(PaymentProcessDto paymentProcessDto);
}
