package com.ParcheParceros.Back.controller;

import com.ParcheParceros.Back.entity.Item;
import com.ParcheParceros.Back.service.ItemService;
import com.ParcheParceros.Back.service.ParcheService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    private ParcheService parcheService;

    private Item item;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("get/{itemId}")
    public Item getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item) {
        parcheService.agregarItem(item.getParche().getId(), item);
        return itemService.createItem(item);
    }

    @PutMapping("update/{itemId}")
    public Item updateItem(@PathVariable Long itemId, @RequestBody Item itemDetails) {
        parcheService.modificarItemDelParche(itemDetails.getParche().getId(),itemDetails.getId() ,itemDetails);
        return itemService.updateItem(itemId, itemDetails);
    }

    @DeleteMapping("delete/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long itemId) {
        parcheService.eliminarItem(itemService.getItemById(itemId).getParche().getId(),itemId);
        itemService.deleteItem(itemId);
    }
}
