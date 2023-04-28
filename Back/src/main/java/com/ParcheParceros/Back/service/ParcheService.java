package com.ParcheParceros.Back.service;

import com.ParcheParceros.Back.entity.Item;
import com.ParcheParceros.Back.entity.Parche;
import com.ParcheParceros.Back.repository.ItemRepository;
import com.ParcheParceros.Back.repository.ParcheRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParcheService {

    private final ParcheRepository parcheRepository;

    private ItemRepository itemRepository;

    public ParcheService(ParcheRepository parcheRepository) {
        this.parcheRepository = parcheRepository;
    }

    public Parche crearParche(Parche parche) {
        return parcheRepository.save(parche);
    }

    public Parche obtenerParchePorId(Long id) {
        return parcheRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No se encontró el parche con el ID: " + id));
    }

    public List<Parche> obtenerTodosLosParches() {
        return parcheRepository.findAll();
    }

    public void eliminarParche(Parche parche) {
        parcheRepository.delete(parche);
    }


    public void agregarItem(Long parcheId, Item item){
        Parche parche = parcheRepository.findById(parcheId).orElseThrow(() -> new EntityNotFoundException("Parche no encontrado"));
        parche.getItems().add(item);
    }

    public void eliminarItem(Long parcheId, Long itemId){
        Parche parche = parcheRepository.findById(parcheId).orElseThrow(() -> new EntityNotFoundException("parche no encontrado"));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException("item no encontrado"));
        parche.getItems().remove(item);
        item.setParche(null);
        itemRepository.delete(item);
        parcheRepository.save(parche);
    }

    public void modificarItemDelParche(Long parcheId, Long itemId, Item nuevoItem) {
        Parche parche = parcheRepository.findById(parcheId).orElseThrow(() -> new EntityNotFoundException("parche no encontrado"));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException("item no encontrado"));
        nuevoItem.setId(itemId);
        nuevoItem.setParche(parche);
        itemRepository.save(nuevoItem);
        parche.getItems().remove(item);
        parche.getItems().add(nuevoItem);
        parcheRepository.save(parche);
    }



}
