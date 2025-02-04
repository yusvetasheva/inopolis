package com.example.inopolis.service;

import com.example.inopolis.model.OrderDTO;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    private final Counter orderCount;
    private final AtomicReference<BigDecimal> totalSum = new AtomicReference<>(BigDecimal.ZERO);
    private final AtomicInteger orderCounter = new AtomicInteger(0);

    public OrderServiceImpl(MeterRegistry meterRegistry) {
        this.orderCount = meterRegistry.counter("orders.total");
        Gauge.builder("orders.averageSum", this, service ->
                orderCounter.get() == 0 ? 0 : totalSum.get().doubleValue() / orderCounter.get()
        ).register(meterRegistry);
    }

    @Override
    public ResponseEntity<String> createOrder(OrderDTO order) {
        if (order.getCount() < 0 || order.getSum().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Некорректный запрос");
        }

        orderCount.increment();
        orderCounter.incrementAndGet();
        totalSum.updateAndGet(sum -> sum.add(order.getSum()));

        return ResponseEntity.ok("Успех!");
    }
}