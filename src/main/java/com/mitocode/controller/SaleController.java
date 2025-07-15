package com.mitocode.controller;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.dto.SaleDTO;
import com.mitocode.model.Sale;
import com.mitocode.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAllSales() throws Exception{
        List<SaleDTO> list = service.readAll().stream().map(e-> mapper.map(e,SaleDTO.class)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        SaleDTO categoria = mapper.map(service.readById(id),SaleDTO.class);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.save(mapper.map(dto,Sale.class));
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(@Valid @RequestBody Sale category, @PathVariable("id") Integer id) throws Exception{
        Sale obj = service.update(category, id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    // Queries //
    @GetMapping("/resume")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume1() {
        return new ResponseEntity<>(service.callProcedure1(), HttpStatus.OK);
    }
    // Queries //
    @GetMapping("/resume2")
    public ResponseEntity<List<IProcedureDTO>> getSaleResume2() {
        return new ResponseEntity<>(service.callProcedure2(), HttpStatus.OK);
    }

    // Procedure //
    @GetMapping("/resume4")
    public ResponseEntity<Void> getSaleResume4() {
        service.callProcedure4();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // Procedure //
    @GetMapping("/mostExpensive")
    public ResponseEntity<SaleDTO> getMostExpensive() {
        SaleDTO dto =  mapper.map(service.getSaleMostExpensive() ,  SaleDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/bestSeller")
    public ResponseEntity<String> getbestSeller() {
        String userName =  service.getBestSellerName() ;
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }

    @GetMapping("/countBySeller")
    public ResponseEntity<Map<String,Long>> getCountSeller() {
        Map<String,Long> byUser =  service.getSalesCountBySeller() ;
        return new ResponseEntity<>(byUser, HttpStatus.OK);
    }
}
