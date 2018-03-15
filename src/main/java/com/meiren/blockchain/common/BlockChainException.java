package com.meiren.blockchain.common;

/**
 * Base exception for bitcoin app.
 * 
 * @author liaoxuefeng
 */
public class BlockChainException extends RuntimeException {

	public BlockChainException() {
	}

	public BlockChainException(String message) {
		super(message);
	}

	public BlockChainException(Throwable cause) {
		super(cause);
	}

	public BlockChainException(String message, Throwable cause) {
		super(message, cause);
	}
}
