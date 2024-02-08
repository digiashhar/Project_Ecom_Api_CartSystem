package com.register.registration.Service.ServiceImpl;

import com.register.registration.entity.Bill;
import com.register.registration.entity.Order;
import com.register.registration.entity.PaymentHistory;
import com.register.registration.repository.BillRepository;
import com.register.registration.repository.OrderRepository;
import com.register.registration.repository.PaymentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// PaymentService
@Service
public class PaymentService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private OrderRepository orderRepository;


    public String processPayment(Long userId, Long billId) {

        // Check if the bill exists
        Optional<Bill> optionalBill = billRepository.findById(billId);
        if (optionalBill.isPresent()) {
            Bill bill = optionalBill.get();

            //successful payment logic here
            boolean paymentSuccess = performPaymentLogic(userId, bill);

            // Save payment history
            double totalPrice = bill.getTotalPrice();

            // Retrieve product IDs from the Bill entity
            List<Long> productIds = bill.getProductIds();

            // Save payment history
            savePaymentHistory(userId, billId, paymentSuccess, bill.getTotalPrice(), productIds);

            // Return result based on payment success
            if (paymentSuccess) {

                return "Payment successful! Total Price: " + bill.getTotalPrice();
            } else {
                return "Payment failed.";
            }
        } else {
            return "Bill not found.";
        }
    }

    private boolean performPaymentLogic(Long userId, Bill bill) {

        return true;
    }

    private void savePaymentHistory(Long userId, Long billId, boolean paymentSuccess, double totalPrice,List<Long> productIds) {
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setUserId(userId);
        paymentHistory.setBillId(billId);
        paymentHistory.setPaymentStatus(paymentSuccess ? "Success" : "Failed");
        paymentHistory.setTotalPrice(totalPrice);
        //paymentHistory.setTimestamp(LocalDateTime.now());
        // Save orders
        List<Order> orders = productIds.stream()
                .map(productId -> {
                    Order order = new Order();
                    order.setProductId(productId);
                    order.setPaymentHistory(paymentHistory);
                    return order;
                })
                .collect(Collectors.toList());

        paymentHistory.setOrders(orders);

        paymentHistoryRepository.save(paymentHistory);
    }
}

