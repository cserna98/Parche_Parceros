package com.example.Parche.controller;

import com.example.Parche.DTO.ItemDTO;
import com.example.Parche.entity.Asistente;
import com.example.Parche.entity.Item;
import com.example.Parche.entity.Parche;
import com.example.Parche.service.AsistenteService;
import com.example.Parche.service.ItemService;
import com.example.Parche.service.ParcheService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    private final AsistenteService asistenteService;
    private final ModelMapper modelMapper;
    private final ParcheService parcheService;

    public ItemController(ItemService itemService, AsistenteService asistenteService, ModelMapper modelMapper, ParcheService parcheService) {
        this.itemService = itemService;
        this.asistenteService = asistenteService;
        this.modelMapper = modelMapper;
        this.parcheService = parcheService;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);
        Parche parche = parcheService.getParcheByName(itemDTO.getNombreParche());
        item.setParche(parche);
        Asistente asistente = asistenteService.findByName(itemDTO.getNombreAsistente());
        item.setAsistente(asistente);
        parcheService.TotalCost();
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDetails) {
        Item item = modelMapper.map(itemDetails, Item.class);
        Parche parche = parcheService.getParcheByName(itemDetails.getNombreParche());
        item.setParche(parche);
        Asistente asistente = asistenteService.findByName(itemDetails.getNombreAsistente());
        item.setAsistente(asistente);
        parcheService.TotalCost();
        return itemService.updateItem(id, item);
    }
}