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
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{parcheId}/items")
    public ResponseEntity<List<ItemDTO>> getItemsByParcheId(@PathVariable Long parcheId) {
        List<ItemDTO> items = itemService.getItemsByParcheId(parcheId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Long itemId) {
        try {
            itemService.deleteItem(itemId);
            return ResponseEntity.ok("Item eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el item");
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createItem(@RequestBody ItemDTO itemDTO) {
        try {
            System.out.println("entro a ty item");
            Item item = modelMapper.map(itemDTO, Item.class);
            Parche parche = parcheService.getParcheById(itemDTO.getIdParche());
            item.setParche(parche);
            Asistente asistente = asistenteService.findByName(itemDTO.getNombreAsistente());
            item.setAsistente(asistente);
            parcheService.TotalCost();

            // Guardar el item y obtener la respuesta
            Item newItem = itemService.createItem(item);
            System.out.println("item"  + newItem);
            // Return 201 Created con un mensaje de éxito
            return ResponseEntity.status(HttpStatus.CREATED).body("Item creado exitosamente con ID: " + newItem.getId());
        } catch (Exception e) {
            // Si ocurre una excepción, regresa una respuesta con un mensaje de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el item: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
      Item updateItem = itemService.updateItem(id,item);
        return updateItem;
    }
}