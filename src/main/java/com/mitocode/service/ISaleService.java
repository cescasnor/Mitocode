package com.mitocode.service;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;

import java.util.List;
import java.util.Map;

public interface ISaleService extends ICRUD<Sale,Integer> {

    List<ProcedureDTO> callProcedure1();
    List<IProcedureDTO> callProcedure2();
    List<IProcedureDTO> callProcedure3();
    void callProcedure4();

    //Obtener la venta mayor
    Sale getSaleMostExpensive(); // Obtener venta mayor
    String getBestSellerName(); // Obtener el nombre del mejor vendedor

    Map<String, Long> getSalesCountBySeller(); // Contar cantidad de ventas por vendedor
    Map<String, Double> getMostSellerProduct(); // Contar cantidad de ventas por vendedor

}
