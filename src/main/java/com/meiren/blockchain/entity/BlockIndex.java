package com.meiren.blockchain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meiren.blockchain.common.serializer.HashSerializer;
import com.meiren.blockchain.common.serializer.TimestampSerializer;

/**
 * @author jijiecong
 * @ClassName: BlockIndex
 * @Description: 加载block索引，实例只在内存中
 * @date 2018/2/27 16:33
 */
public class BlockIndex {
	// current block hash
	@JsonSerialize(using = HashSerializer.class)
	public byte[] pHashBlock;
	//prev BlockIndex
	public BlockIndex pprev;
	//next BlockIndex
	public BlockIndex pnext;
	//nFile
	public int nFile;
	//
	public int nBlockPos;
	// block height
	public int nHeight;
	//
	public int nBlockSize;

	// block header
	/**
	 * int32, block version information (note, this is signed)
	 */
	public int version;

	/**
	 * 32 bytes, The hash value of the previous block this particular block
	 * references
	 */
	@JsonSerialize(using = HashSerializer.class)
	public byte[] prevHash;

	/**
	 * 32 bytes, The reference to a Merkle tree collection which is a hash of
	 * all transactions related to this block
	 */
	@JsonSerialize(using = HashSerializer.class)
	public byte[] merkleHash;

	/**
	 * uint32, A timestamp recording when this block was created (Will overflow
	 * in 2106)
	 */
	@JsonSerialize(using = TimestampSerializer.class)
	public long timestamp;

	/**
	 * uint32, The calculated difficulty target being used for this block
	 */
	public long bits;

	/**
	 * uint32, The nonce used to generate this block to allow variations of the
	 * header and compute different hashes
	 */
	public long nonce;

	public byte[] getBlockHash() {
		return pHashBlock;
	}
}
