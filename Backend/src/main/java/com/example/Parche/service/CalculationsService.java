package com.example.Parche.service;

import com.example.Parche.entity.Asistente;
import com.example.Parche.entity.Item;
import com.example.Parche.entity.Parche;
import com.example.Parche.repository.AsistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class CalculationsService {

    @Autowired
    private AsistenteRepository asistenteRepository;

    public double calculateTotalCost(Set<Item> items) {
        double totalCost = 0.0;
        for (Item item : items) {
            totalCost += item.getCosto();
        }
        return totalCost;
    }

    public double calculateTotalExpenses(Set<Asistente> asistentes) {
        double totalExpenses = 0.0;
        for (Asistente asistente : asistentes) {
            totalExpenses += asistente.getGasto();
        }
        return totalExpenses;
    }

    public double calculateTotalCostForParche(Parche parche) {
        Set<Item> items = parche.getItems(); // Supongamos que Parche tiene una lista de items.
        return calculateTotalCost(items);
    }

    public double calculateProfit(Parche parche) {
        double totalCost = calculateTotalCostForParche(parche);
        double totalExpenses = calculateTotalExpenses(parche.getAsistentes()); // Supongamos que Parche tiene una lista de asistentes.
        double totalIncome = parche.getGastoTotal();

        return totalIncome - (totalCost + totalExpenses);
    }



    public void calculateAmountOfEach(Parche parche){
        int totalConsumidores = parche.getAsistentes().size();
        for(Asistente asistente: parche.getAsistentes()){
            int deudaAsistente = 0;
            for (Item item : parche.getItems()){
                if(item.getNoConsume().contains(asistente)){
                    totalConsumidores = totalConsumidores - 1;
                }else {
                    int costoItem = Double.valueOf(item.getCosto()).intValue();
                    int valorItemCadaUno = costoItem / totalConsumidores;
                    deudaAsistente = deudaAsistente + valorItemCadaUno;
                }
            }
            System.out.print(deudaAsistente);
            asistente.setDebe((double) deudaAsistente);
            asistenteRepository.save(asistente);
        }

    };

}