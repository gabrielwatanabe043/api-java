package com.example.apivenda.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class VendaDTO {

    private String nome;
    private Integer totalVendas;
    private double mediaVenda;

    public VendaDTO( Venda venda){
        this.nome = venda.getVendedor().getNome();
    }

}
