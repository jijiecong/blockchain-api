package com.meiren.blockchain.service;

import com.meiren.blockchain.entity.Block;
import com.meiren.blockchain.entity.DiskUTxOIndex;
import com.meiren.blockchain.entity.TxIn;

import java.util.List;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: DiskUTxOIndexService
 * @Description: ${todo}
 * @date 2018/4/18 11:18
 */
public interface DiskUTxOIndexService {
	public void writeToDisk(DiskUTxOIndex diskUTxOIndex);

	public DiskUTxOIndex readFromDisk(TxIn txIn);

	public List<DiskUTxOIndex> readFromDiskByReceiver(byte[] receiver);

	public void writeToDiskBlock(Block block);

	public void removeFromDisk(TxIn txIn);

	public void removeFromBlock(Block block);
}
