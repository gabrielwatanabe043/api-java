package com.example.apivenda.controllers;

import com.example.apivenda.models.Vendedor;
import com.example.apivenda.models.VendedorDTO;
import com.example.apivenda.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    @Autowired
    private VendedorRepository vendedorRepository;


    @GetMapping
    public ResponseEntity<List<VendedorDTO>> createVendedor(){
        List<VendedorDTO> vendedorDTOS =  vendedorRepository.findAll().stream().map(VendedorDTO::new).toList();
        if(!vendedorDTOS.isEmpty()){
            return ResponseEntity.ok().body(vendedorDTOS);
        }
       return ResponseEntity.badRequest().build();

    }



    @PostMapping
    public ResponseEntity<Vendedor> createVendedor(@RequestBody Vendedor vendedor){
        vendedorRepository.save(vendedor);
        return ResponseEntity.ok().body(vendedor);


    }
}
