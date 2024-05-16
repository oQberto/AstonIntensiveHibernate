package com.aston.intensive.hibernate.astonintensivehibernate.controller;

import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.order.OrderDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.impl.OrderServiceImpl;
import com.aston.intensive.hibernate.astonintensivehibernate.validation.CreateAction;
import com.aston.intensive.hibernate.astonintensivehibernate.validation.UpdateAction;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping("/api/v1/orders")
public class OrderController {
    OrderServiceImpl orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(
                orderService.findById(orderId)
                        .orElseThrow(() -> new NoSuchElementException("errors.order.id.not_fount"))
        );
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(int page, int size) {
        return ResponseEntity.ok(
                orderService.findAll(page, size)
        );
    }

    @PostMapping
    public ResponseEntity<?> saveOrder(@Validated(CreateAction.class) @RequestBody OrderDto dto,
                                              BindingResult bindingResult,
                                              UriComponentsBuilder uriComponentsBuilder) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            var savedOrder = orderService.save(dto);

            return ResponseEntity
                    .created(uriComponentsBuilder
                            .pathSegment("/{orderId}")
                            .build(Map.of("orderId", savedOrder.getId())))
                    .body(savedOrder);
        }
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<?> updateItem(@PathVariable Long orderId,
                                        @Validated(UpdateAction.class) @RequestBody OrderDto dto,
                                        BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            orderService.update(dto, orderId);

            return ResponseEntity
                    .noContent()
                    .build();
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Long> deleteItem(@PathVariable Long orderId) {
        return ResponseEntity
                .ok()
                .body(orderService.delete(orderId));
    }
}
