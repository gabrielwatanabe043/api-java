package com.example.apivenda.repositories;

import com.example.apivenda.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    List<Venda> findByDataDaVendaBetween(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim);
}
