package com.ParcheParceros.Back.service;

import com.ParcheParceros.Back.entity.Item;
import com.ParcheParceros.Back.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Item not found with id: " + itemId));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long itemId, Item itemDetails) {
        Item item = getItemById(itemId);
        item.setTitulo(itemDetails.getTitulo());
        item.setValor(itemDetails.getValor());
        item.setAsistente(itemDetails.getAsistente());
        item.setParche(itemDetails.getParche());
        return itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        Item item = getItemById(itemId);
        itemRepository.delete(item);
    }
}
