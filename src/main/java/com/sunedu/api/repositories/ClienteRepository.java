package com.sunedu.api.repositories;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.sunedu.api.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,BigInteger> {
    
    @Query(value = "SELECT o FROM Cliente o WHERE o.Dni=?1")
    Optional<Cliente>  findByDni(BigInteger Dni);

    /*@Query(value = "SELECT DATE_TRUNC('month', o.Fecha) AS fecha_emision_to_month, SUM(o.montoTotal) as montototal FROM Factura o GROUP BY DATE_TRUNC('month', o.fechaEmision)")
    List<Map<String, Object>> querySumaTotalByMonth();*/

    @Query(value = "SELECT CAST(o.Dni AS text) AS dniReceptor FROM Cliente o GROUP BY o.Dni")
    List<Map<String, Object>> querySumaTotalByReceptor();

    @Query(value = "SELECT o.Fecha AS fecha_emision_to_month, COUNT(o.idCliente) as facturas FROM Cliente o GROUP BY  o.Fecha ")
    List<Map<String, Object>> querySumaTotalByMonthInscripciones();



}
