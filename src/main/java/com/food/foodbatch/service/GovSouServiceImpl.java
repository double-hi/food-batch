package com.food.foodbatch.service;

import com.food.foodbatch.dao.GovSouMapper;
import com.food.foodbatch.model.GovSouEntityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GovSouServiceImpl implements GovSouService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GovSouMapper govSouMapper;

	@Override
	public void batchReplaceTPermit(List<GovSouEntityVo> permitInfos) {
		for (GovSouEntityVo entity : permitInfos) {
			if (entity.getValidityTo() != null) {
				entity.setValidityTo(entity.getValidityTo().replace('-', '/'));
			}
			if (govSouMapper.updateTPermit(entity) <= 0) {
				govSouMapper.insertTPermit(entity);
			}
		}
	}

	@Override
	@Transactional
	public Integer flagFailure() {
		return govSouMapper.flagFailure();
	}

}
