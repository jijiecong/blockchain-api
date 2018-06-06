package com.meiren.blockchain.service;

import com.meiren.blockchain.entity.UTxO;

import java.util.List;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: UTxOService
 * @Description: ${todo}
 * @date 2018/4/23 15:05
 */
public interface UTxOService {

	public List<UTxO> findByReceiver(String receiver);
}
