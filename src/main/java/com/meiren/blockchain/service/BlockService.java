package com.meiren.blockchain.service;

import com.meiren.blockchain.entity.Block;
import com.meiren.blockchain.entity.Store;
import com.meiren.blockchain.entity.Transaction;

import java.io.IOException;

/**
 * @author jijiecong
 * @ClassName: BlockService
 * @Description: meiren blockChain blockService
 * @date 2018/2/27 10:51
 */
public interface BlockService {
	public void importBlockChain();

	public Block nextBlock(Transaction[] transactions, byte[] prevHash);

	public void getLastestBlockHash();

	public void writeToDisk(Block block, int nFile, Boolean append);

	public Block readFromDisk(int nFile, int begin, int end);

	public void init() throws IOException;

	public Block readFromDiskBySize(int nFile, int begin, int size);

	public void sendTransaction(Transaction transaction);

}
