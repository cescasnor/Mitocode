package com.mitocode.controller;

import com.mitocode.dto.ProviderDTO;
import com.mitocode.model.Provider;
import com.mitocode.service.IProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final IProviderService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAllProviders() throws Exception{
        List<ProviderDTO> list = service.readAll().stream().map(e-> mapper.map(e,ProviderDTO.class)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception{
        ProviderDTO categoria = mapper.map(service.readById(id),ProviderDTO.class);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(@Valid @RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.save(mapper.map(dto,Provider.class));
        return new ResponseEntity<>(mapper.map(obj, ProviderDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> update(@Valid @RequestBody Provider category, @PathVariable("id") Integer id) throws Exception{
        Provider obj = service.update(category, id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

}
