package com.sfmy.gsh.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sfmy.gsh.constant.AdType;
import com.sfmy.gsh.dao.AdDao;
import com.sfmy.gsh.entity.Ad;
import com.sfmy.gsh.utils.MyOssUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdService {
	@Resource
	private AdDao adDao;
	
//	@PersistenceContext
//    private EntityManager em;

	public void saveAd(Ad ad) {
		adDao.save(ad);
	}

	public List<Ad> findAll() {
		return adDao.findAllByOrderByIsUseDesc();
	}

	public boolean delete(List<Integer> adIds) {
		if(CollectionUtils.isNotEmpty(adIds)){
			for (Integer adId : adIds) {
				Ad ad = adDao.findOne(adId);
				adDao.delete(adId);
				MyOssUtils.delete(ad.getOssKey());
			}
			return true;
		}
//		Integer deleteCnt = adDao.removeByIds(adIds);
//		if(deleteCnt!=null&&deleteCnt>0){
//			List<Ad> ads = adDao.findById(adIds);
//			for (Ad ad : ads) {
//				OssUtils.delete(ad.getOssKey());
//			}
//			return true;
//		}
		return false;
	}

	public List<Ad> findByIds(List<Integer> adIds) {
		return adDao.findById(adIds);
	}

	public List<Ad> show(List<Integer> adIds) {
		List<Ad> showAds = new ArrayList<Ad>();
		if(CollectionUtils.isNotEmpty(adIds)){
			for (Integer adId : adIds) {
				Ad ad = adDao.findOne(adId);
				ad.setIsUse(true);
				adDao.save(ad);
				showAds.add(ad);
			}
			adDao.updateIsUseNotInIds(false,adIds);
		}
		return showAds;
	}

	public List<Ad> findShowing() {
		return adDao.findByIsUse(true);
	}
	
	public List<Ad> findAd(AdType adType) {
		return adDao.findByAdType(adType);
	}
}
