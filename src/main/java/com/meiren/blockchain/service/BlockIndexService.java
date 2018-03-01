package com.meiren.blockchain.service;

import com.meiren.blockchain.entity.BlockIndex;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: BlockIndexService
 * @Description: ${todo}
 * @date 2018/2/27 16:58
 */
public interface BlockIndexService {
	public void init();

	public BlockIndex getLastestBlockIndex();
}
