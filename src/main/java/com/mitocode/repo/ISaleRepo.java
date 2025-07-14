package com.mitocode.repo;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.model.Sale;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale,Integer> {

    @Query(value = "select * from fn_sales()", nativeQuery = true)
    List<Object[]> callProcedure1();
    // [2, 06/11/2023]

    @Query(value = "select * from fn_sales()", nativeQuery = true)
    List<IProcedureDTO> callProcedure2();

    @Query(value = "Sale.fn_sales", nativeQuery = true)
    List<IProcedureDTO> callProcedure3();

}
