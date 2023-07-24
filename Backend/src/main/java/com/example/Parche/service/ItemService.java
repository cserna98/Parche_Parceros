package com.example.Parche.service;

import com.example.Parche.entity.Item;
import com.example.Parche.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item no encontrado"));
    }
    public Item findByName(String nombre) {
        return itemRepository.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Item no encontrado"));
    }


    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item itemDetails) {
        Item item = getItemById(id);
        item.setNombre(itemDetails.getNombre());
        item.setDescripcion(itemDetails.getDescripcion());
        item.setDia(itemDetails.getDia());
        item.setImg(itemDetails.getImg());
        item.setCosto(itemDetails.getCosto());
        item.setParche(itemDetails.getParche());
        item.setAsistente(itemDetails.getAsistente());
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
