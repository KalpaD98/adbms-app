package com.adbms.app.Services;

import com.adbms.app.models.Sku.Sku;
import com.adbms.app.repositories.SkuRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class SkuService {
    @Autowired
    private SkuRepository skuRepository;

    @Transactional(readOnly = true)
    public List<Sku> getAllSku(){
        return skuRepository.findAll();
    };

    @Transactional
    public Sku save(Sku sku) {
        return skuRepository.save(sku);
    }

    @Transactional(readOnly = true)
    public boolean existById(int id) {
        return skuRepository.existsById(String.valueOf(id));

    }

//    @Transactional(readOnly = true)
//    public Optional<Sku> getSkuById(int id) {
//        // TODO Auto-generated method stub
//        return skuRepository.findById(id);
//    }
//
//
//    public void delete(int id) {
//        // TODO Auto-generated method stub
//        skuRepository.deleteById(id);
//
//    }

}
