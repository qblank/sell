package cn.qblank.springdemo.service;

import cn.qblank.springdemo.domain.ProductInfo;
import cn.qblank.springdemo.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     *加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStokck(List<CartDTO> cartDTOList);


    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
