package com.example.Parche.service;

import com.example.Parche.DTO.ItemDTO;
import com.example.Parche.entity.Item;
import com.example.Parche.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;


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

    public List<ItemDTO> getItemsByParcheId(Long parcheId) {
        List<Item> items = itemRepository.findByParcheId(parcheId);

        // Convertir objetos Item a objetos ItemDTO usando ModelMapper
        List<ItemDTO> itemsDTO = items.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());

        return itemsDTO;
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
