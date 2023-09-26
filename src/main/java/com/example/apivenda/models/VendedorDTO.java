package com.example.apivenda.models;

import java.util.List;

public record VendedorDTO(Integer id, String nome, List<Venda> vendas) {

    public VendedorDTO(Vendedor vendedor){
        this(vendedor.getId(), vendedor.getNome(), vendedor.getListaVendas());
    }
}
