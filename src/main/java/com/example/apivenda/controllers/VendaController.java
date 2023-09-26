package com.example.apivenda.controllers;

import com.example.apivenda.models.Venda;
import com.example.apivenda.models.VendaDTO;
import com.example.apivenda.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @GetMapping
    public ResponseEntity<List<Venda>> getVenda(){
        List<Venda> listaDeVenda = vendaRepository.findAll();
        if(!listaDeVenda.isEmpty()){

            return ResponseEntity.ok().body(listaDeVenda);
        }
       return ResponseEntity.badRequest().build();

    }

    @GetMapping("/{dataInicio}/{dataFim}")
    public ResponseEntity<List<VendaDTO>> getLista(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<Venda> vendasFiltradas = vendaRepository.findByDataDaVendaBetween(dataInicio, dataFim);
        List<VendaDTO> listaDto = new ArrayList<>();

        long diff = ChronoUnit.DAYS.between(dataInicio, dataFim);
        int contadorVenda = 1;


        for(int i = 0; i < vendasFiltradas.size(); i++){
            if(listaDto.isEmpty()){
                VendaDTO vendaDTO = new VendaDTO();
                vendaDTO.setNome(vendasFiltradas.get(i).getVendedor().getNome());
                vendaDTO.setTotalVendas(1);
                vendaDTO.setMediaVenda((double) vendaDTO.getTotalVendas() /diff);
                listaDto.add(vendaDTO);

                continue;
            }
            boolean naoAchou = true;
            for(int j = 0; j < listaDto.size(); j++){
                if(listaDto.get(j).getNome().contains(vendasFiltradas.get(i).getVendedor().getNome())){
                    VendaDTO vendaDTO  = new VendaDTO();
                    vendaDTO.setNome(listaDto.get(j).getNome());
                    vendaDTO.setTotalVendas(listaDto.get(j).getTotalVendas() + 1);
                    vendaDTO.setMediaVenda((double) vendaDTO.getTotalVendas() / diff);
                    listaDto.set(j,vendaDTO);
                    naoAchou = false;
                }

            }

            if(naoAchou){
                VendaDTO vendaDTO = new VendaDTO();
                vendaDTO.setNome(vendasFiltradas.get(i).getVendedor().getNome());
                vendaDTO.setTotalVendas(1);
                vendaDTO.setMediaVenda((double) vendaDTO.getTotalVendas() /diff);
                listaDto.add(vendaDTO);
            }



        }


        return ResponseEntity.ok().body(listaDto);

    }



    @PostMapping
    public ResponseEntity<Venda> createVenda(@RequestBody Venda venda){



            vendaRepository.save(venda);
            return ResponseEntity.ok().body(venda);






    }
}
