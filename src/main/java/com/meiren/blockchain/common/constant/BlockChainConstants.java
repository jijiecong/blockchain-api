package com.meiren.blockchain.common.constant;


import com.meiren.blockchain.common.util.HashUtils;

import java.math.BigInteger;

public final class BlockChainConstants {

	/**
	 * Magic number for bitcoin message.
	 */
	public static final int MAGIC = 0xd9b4bef9;

	/**
	 * Default port for bitcoin peer.
	 */
	public static final int PORT = 8333;

	/**
	 * Bitcoin protocol version.
	 */
	public static final int PROTOCOL_VERSION = 70014;

	/**
	 * Network services.
	 */
	public static final long NETWORK_SERVICES = 1L;

	/**
	 * Legal base58 characters.
	 */
	public static final String BASE58_CHARS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

	/**
	 * Network ID: 0x00 = main network.
	 */
	public static final byte NETWORK_ID = 0x00;
	public static final byte[] NETWORK_ID_ARRAY = { NETWORK_ID };

	/**
	 * Public key prefix: 0x04.
	 */
	public static final byte PUBLIC_KEY_PREFIX = 0x04;
	public static final byte[] PUBLIC_KEY_PREFIX_ARRAY = { PUBLIC_KEY_PREFIX };

	public static final byte[] PUBLIC_KEY_COMPRESSED_02 = { 0x02 };
	public static final byte[] PUBLIC_KEY_COMPRESSED_03 = { 0x03 };

	/**
	 * Private key prefix: 0x80.
	 */
	public static final byte PRIVATE_KEY_PREFIX = (byte) 0x80;
	public static final byte[] PRIVATE_KEY_PREFIX_ARRAY = { PRIVATE_KEY_PREFIX };

	public static final byte PRIVATE_KEY_SUFFIX = 0x01;
	public static final byte[] PRIVATE_KEY_SUFFIX_ARRAY = { PRIVATE_KEY_SUFFIX };

	public static final int MAINNET_BIP32_PUBLIC = 0x0488b21e;
	public static final int MAINNET_BIP32_PRIVATE = 0x0488ade4;

	public static final int TESTNET_BIP32_PUBLIC = 0x043587cf;
	public static final int TESTNET_BIP32_PRIVATE = 0x04358394;

	/**
	 * Minimum value of private key.
	 */
	public static final BigInteger MIN_PRIVATE_KEY = new BigInteger("ffffffffffffffff", 16);

	/**
	 * Maximum value of private key.
	 */
	public static final BigInteger MAX_PRIVATE_KEY = new BigInteger(
			"fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364140", 16);

	/**
	 * Random node id generated at startup.
	 */
	public static final long NODE_ID = (long) (Math.random() * Long.MAX_VALUE);

	/**
	 * Bitcoin client subversion.
	 */
	public static final String SUB_VERSION = "/Satoshi:0.7.2/";

	/**
	 * Genesis block hash as string.
	 */
	public static final String GENESIS_HASH = "9e4b498021782dc80d3b37abe44ec9cf325dbf1f939528323c35be2a158ccfc9";

	/**
	 * Genesis block hash as bytes.
	 */
	public static final byte[] GENESIS_HASH_BYTES = HashUtils.toBytesAsLittleEndian(GENESIS_HASH);

	/**
	 * Hardcoded genesis block data.
	 */
	public static final byte[] GENESIS_BLOCK_DATA = HashUtils.toBytesAsLittleEndian(
			"706f74736e6f6e6461095ad84acb34323130393838363838310b706f74736e6f6e6461090000000000000064010000000000000000000000000000000000000000000000000000000000000000000000000100000001010000007b1d00ffff5ad84acb7e87d2a8fdc8f37de5b950c4fb6837c27f7cfcb65367957a25074496214d9a64000000000000000000000000000000000000000000000000000000000000000000000001");

	/**
	 * Hash bytes as "00000000...0000"
	 */
	public static final String ZERO_HASH = "0000000000000000000000000000000000000000000000000000000000000000";

	/**
	 * Hash bytes as "00000000...0000"
	 */
	public static final byte[] ZERO_HASH_BYTES = HashUtils
			.toBytesAsLittleEndian("0000000000000000000000000000000000000000000000000000000000000000");

	/**
	 * Transaction version.
	 */
	public static final int TX_VERSION = 0x01;

	/**
	 * Signature type: SIGHASH_ALL.
	 */
	public static final int SIGHASH_ALL = 0x01;
}
