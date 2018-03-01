package com.meiren.blockchain.service;

import com.meiren.blockchain.entity.DiskBlockIndex;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: DiskBlockIndexService
 * @Description: ${todo}
 * @date 2018/2/27 16:58
 */
public interface DiskBlockIndexService {
	public void writeToDisk(DiskBlockIndex diskBlockIndex);

	public DiskBlockIndex readFromDisk();
}
