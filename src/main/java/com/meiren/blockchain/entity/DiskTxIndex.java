package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.serializer.HashSerializer;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: DiskUTxOIndex
 * @Description: ${todo}
 * @date 2018/4/18 11:20
 */
public class DiskTxIndex {
	@JsonSerialize(using = HashSerializer.class)
	public byte[] txHash;
	//nFile
	public int nFile;
	//
	public int nBlockPos;
	//
	public int nTxOffset;
	//
	public int txSize;
}
