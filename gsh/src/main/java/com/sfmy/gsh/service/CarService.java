package com.sfmy.gsh.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.dao.CarProductDao;
import com.sfmy.gsh.entity.CarProduct;
import com.sfmy.gsh.entity.Product;
import com.sfmy.gsh.entity.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarService {

	@Resource
	private CarProductDao carProductDao;
	
	public void putCat(Integer userId, Integer productId, Integer productCnt) {
		CarProduct carProduct = carProductDao.findByProductIdAndUserId(productId,userId);
		if(carProduct==null){
			carProduct = new CarProduct();
			carProduct.setUser(new User(userId));
			carProduct.setProduct(new Product(productId));
			carProduct.setCount(productCnt);
			carProductDao.save(carProduct);
		}else{
			Integer oldCount = carProduct.getCount();
			carProduct.setCount(oldCount+productCnt);
			carProductDao.save(carProduct);
		}
	}

	public List<CarProduct> findCatProduct(Integer userId) {
		return carProductDao.findByUser(new User(userId));
	}

	public Integer countProductCnt(Integer userId) {
		Integer cnt = 0;
		List<Integer> productCnts = carProductDao.findProductCntByUser(new User(userId));
		if(CollectionUtils.isNotEmpty(productCnts)){
			for (Integer i : productCnts) {
				cnt+=i;
			}
		}
		return cnt;
	}

	public boolean deleteCarProduct(User user, Integer carProductId) {
		Integer deleteCnt = carProductDao.deleteByUserAndId(user,carProductId);
		return deleteCnt!=null&&deleteCnt>0;
	}
}
