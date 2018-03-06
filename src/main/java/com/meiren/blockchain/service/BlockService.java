package com.meiren.blockchain.service;

import com.meiren.blockchain.entity.Block;
import com.meiren.blockchain.entity.Store;

import java.io.IOException;

/**
 * @author jijiecong
 * @ClassName: BlockService
 * @Description: meiren blockChain blockService
 * @date 2018/2/27 10:51
 */
public interface BlockService {
	public void importBlockChain();

	public Block nextBlock(Store[] stores, byte[] prevHash);

	public void getLastestBlockHash();

	public void writeToDisk(Block block, int nFile);

	public Block readFromDisk(int nFile);

	public void init() throws IOException;
}
