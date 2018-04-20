package com.meiren.blockchain.service;

import com.meiren.blockchain.entity.Block;
import com.meiren.blockchain.entity.DiskTxIndex;
import com.meiren.blockchain.entity.Transaction;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: DiskTxIndexService
 * @Description: ${todo}
 * @date 2018/4/19 14:42
 */
public interface DiskTxIndexService {

	public void writeToDiskBlock(Block block, int nFile, int nBlockPos);

	public void writeToDisk(DiskTxIndex diskTxIndex);

	public DiskTxIndex findByTxHash(String txHash);
}
