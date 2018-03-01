package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.serializer.HashSerializer;

import java.io.Serializable;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: DiskBlockIndex
 * @Description: 用于将block索引保存到磁盘
 * @date 2018/2/27 16:35
 */
public class DiskBlockIndex extends BlockIndex implements Serializable {

	@JsonSerialize(using = HashSerializer.class)
	//prev block Hash
	public byte[] prevHash;

	@JsonSerialize(using = HashSerializer.class)
	//next block hash
	public byte[] nextHash;

	public byte[] getPrevHash() {
		return pprev == null? getBlockHash(pprev) : null;
	}

	public byte[] getNextHash() {
		return pnext == null? getBlockHash(pnext) : null;
	}

	private byte[] getBlockHash(BlockIndex blockIndex) {
		return blockIndex.pHashBlock;
	}
}
