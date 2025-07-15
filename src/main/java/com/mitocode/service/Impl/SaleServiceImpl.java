package com.mitocode.service.Impl;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import com.mitocode.model.SaleDetail;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISaleRepo;
import com.mitocode.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    private final ISaleRepo saletRepo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return saletRepo;
    }


    @Override
    public List<ProcedureDTO> callProcedure1() {
        List<ProcedureDTO> list = new ArrayList<>();
        saletRepo.callProcedure1().forEach((e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityfn((Integer) e[1]);
            dto.setDatetimefn((String) e[0]);
            list.add(dto);
        }));
        return list;
    }

    @Override
    public List<IProcedureDTO> callProcedure2() {
        return saletRepo.callProcedure2();
    }

    @Override
    public List<IProcedureDTO> callProcedure3() {
        return saletRepo.callProcedure3();
    }
    @Override
    public void callProcedure4() {
        saletRepo.callProcedure4();
    }

    @Override
    public Sale getSaleMostExpensive() {
        return saletRepo.findAll()
                .stream()
                .max(Comparator.comparing(Sale::getTotal))
                .orElse(new Sale());
    }

    @Override
    public String getBestSellerName() {
        Map<String, Double> byUser = saletRepo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername() , summingDouble(Sale::getTotal)));

        return Collections.max(byUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }

    @Override
    public Map<String, Long> getSalesCountBySeller() {
        Map<String, Long> byUser = saletRepo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername() , counting()));

        return byUser;
    }

    @Override
    public Map<String, Double> getMostSellerProduct() {
        Stream<Sale> saleStream = saletRepo.findAll().stream();

        Stream<List<SaleDetail>> lsStream = saleStream.map(Sale::getDetails);

        Stream<SaleDetail> streamDetail = lsStream.flatMap(Collection::stream);
        Map<String, Double> byProduct = streamDetail.collect(groupingBy(d -> d.getProduct().getName() , summingDouble(SaleDetail::getQuantity)));

        return byProduct.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new
                ));
    }
}
