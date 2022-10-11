package com.sunedu.api.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name="Cliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idCliente;
    private BigInteger Dni;
    private String Nombres;
    private String Apellidos;
    private String celular;
    private String Fecha;
    private String Banco;
    private String rubro_empresa;
      
}
