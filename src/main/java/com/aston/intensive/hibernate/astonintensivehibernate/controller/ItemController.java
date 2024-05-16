package com.aston.intensive.hibernate.astonintensivehibernate.controller;

import com.aston.intensive.hibernate.astonintensivehibernate.service.ItemService;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemCreateEditDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemRepresentationDto;
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

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
//в целом норм но у тебя в самих контроллерах присутствует логика обработки исключений, что наверное не совсем ок 
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@RequestMapping("/api/v1/items")
public class ItemController {
    ItemService itemService;

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemRepresentationDto> getItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(
                itemService.findById(itemId)
                        .orElseThrow(() -> new NoSuchElementException("errors.item.id.not_found" + format("Id %d", itemId)))
        );
    }
//page и size должны быть аннотированы как @RequestParam, чтобы Spring знал, что они являются параметрами запроса.
    @GetMapping
    public ResponseEntity<List<ItemRepresentationDto>> getAllItems(int page, int size) {
        return ResponseEntity
                .ok(itemService.findAll(page, size));
    }

    @PatchMapping("/{itemName}")
    public ResponseEntity<?> updateItem(@PathVariable String itemName,
                                        @Validated(UpdateAction.class) @RequestBody ItemCreateEditDto dto,
                                        BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            itemService.update(dto, itemName);

            return ResponseEntity
                    .noContent()
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<?> saveItem(@Validated(CreateAction.class) @RequestBody ItemCreateEditDto dto,
                                      BindingResult bindingResult,
                                      UriComponentsBuilder uriComponentsBuilder) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            var savedItem = itemService.save(dto);

            return ResponseEntity
                    .created(uriComponentsBuilder
                            .pathSegment("/{itemId}")
                            .build(Map.of("itemId", savedItem.getId())))
                    .body(savedItem);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Long> deleteItem(@PathVariable Long itemId) {
        return ResponseEntity
                .ok()
                .body(itemService.delete(itemId));
    }
}
