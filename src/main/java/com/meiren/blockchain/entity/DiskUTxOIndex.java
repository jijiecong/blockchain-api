package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.serializer.HashSerializer;

/**
 * @author jijiecong   （这里替换为自己的名字）
 * @ClassName: DiskUTxOIndex
 * @Description: ${todo}
 * @date 2018/4/18 11:20
 */
public class DiskUTxOIndex {

	@JsonSerialize(using = HashSerializer.class)
	//block Hash
	public byte[] blockHash;

	@JsonSerialize(using = HashSerializer.class)
	//tx Hash
	public byte[] txHash;

	public Long txIndex;

	public Long outIndex;

	public byte[] receiver;
}
