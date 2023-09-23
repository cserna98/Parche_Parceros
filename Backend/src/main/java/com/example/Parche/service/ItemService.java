package com.example.Parche.service;

import com.example.Parche.DTO.ItemDTO;
import com.example.Parche.entity.Asistente;
import com.example.Parche.entity.Item;
import com.example.Parche.entity.Parche;
import com.example.Parche.repository.ItemRepository;
import com.example.Parche.repository.ParcheRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CalculationsService calculateService;

    @Autowired
    private AsistenteService asistenteService;
    @Autowired
    private ParcheRepository parcheRepository;


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
        try {
            Item newItem = itemRepository.save(item);

            // Actualizacion del gasto en objeto parche
            Parche parche = item.getParche();
            double costoTotalParche = calculateService.calculateTotalCostForParche(parche);
            parche.setGastoTotal(costoTotalParche);
            parcheRepository.save(parche);

            // Actualizacion de los gastos y los costos en objeto Asistente
            Asistente asistente = item.getAsistente();
            double gastoCadaUno = calculateService.calculateTotalCost(asistente.getItems());
            System.out.print(gastoCadaUno);
            asistente.setGasto(gastoCadaUno);
            calculateService.calculateAmountOfEach(parche);
            asistenteService.updateAsistente(asistente.getId(), asistente);

            return newItem;
        } catch (Exception e) {
            // Si ocurre una excepción, lógala y lanza una excepción personalizada si es necesario.
            // También puedes registrar detalles del error en tus logs.
            throw new RuntimeException("Error al crear el item: " + e.getMessage(), e);
        }
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
        calculateService.calculateAmountOfEach(item.getParche());

        // Actualizacion del gasto en objeto parche
        Parche parche = item.getParche();
        double costoTotalParche = calculateService.calculateTotalCostForParche(parche);
        parche.setGastoTotal(costoTotalParche);
        parcheRepository.save(parche);

        // Actualizacion de los gastos y los costos en objeto Asistente
        Asistente asistente = item.getAsistente();
        double gastoCadaUno = calculateService.calculateTotalCost(asistente.getItems());
        System.out.print(gastoCadaUno);
        asistente.setGasto(gastoCadaUno);
        calculateService.calculateAmountOfEach(parche);
        asistenteService.updateAsistente(asistente.getId(), asistente);
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {

        Item item = getItemById(id);
        // Actualizacion del gasto en objeto parche
        Parche parche = item.getParche();
        double costoTotalParche = calculateService.calculateTotalCostForParche(parche);
        parche.setGastoTotal(costoTotalParche);
        parcheRepository.save(parche);

        // Actualizacion de los gastos y los costos en objeto Asistente
        Asistente asistente = item.getAsistente();
        double gastoCadaUno = calculateService.calculateTotalCost(asistente.getItems());
        System.out.print(gastoCadaUno);
        asistente.setGasto(gastoCadaUno);
        calculateService.calculateAmountOfEach(parche);
        asistenteService.updateAsistente(asistente.getId(), asistente);
        itemRepository.deleteById(id);
    }
}
