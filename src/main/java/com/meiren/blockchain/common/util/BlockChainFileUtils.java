package com.meiren.blockchain.common.util;

import java.io.*;

/**
 * @author jijiecong
 * @ClassName: BlockChainFileUtils
 * @Description: ${todo}
 * @date 2018/2/27 15:26
 */
public class BlockChainFileUtils {

	//生成文件路径
	private static String pathBlk = "D:\\meiren\\blocks";
	private static String pathBlkIndex = "D:\\meiren\\blocks\\index";

	//文件路径+名称
	private static String filenameTemp;

	/**
	 * 创建文件
	 *
	 * @param fileName    文件名称
	 * @param filein 文件内容
	 * @return 是否创建成功，成功则返回true
	 */
	public static boolean createFile(String path, String fileName, byte[] filein) {
		Boolean bool = false;
		filenameTemp = path + fileName + ".dat";//文件路径+名称+文件类型
		File file = new File(filenameTemp);
		try {
			//如果文件不存在，则创建新的文件
			if (!file.exists()) {
				file.createNewFile();
				bool = true;
				System.out.println("success create file,the file is " + filenameTemp);
				//创建文件成功后，写入内容到文件里
				writeFileContent(filenameTemp, filein);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bool;
	}

	/**
	 * 向文件中写入内容
	 *
	 * @param filepath 文件路径与名称
	 * @param filein   写入的内容
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFileContent(String filepath, byte[] filein) throws IOException {
		Boolean bool = false;
		String temp = "";

		FileOutputStream fos = null;
		try {
			File file = new File(filepath);//文件路径(包括文件名称)
			fos = new FileOutputStream(file);
			fos.write(filein);
			bool = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//不要忘记关闭
			if (fos != null) {
				fos.close();
			}
		}
		return bool;
	}

	/**
	 * 删除文件
	 *
	 * @param fileName 文件名称
	 * @return
	 */
	public static boolean delFile(String path, String fileName) {
		Boolean bool = false;
		filenameTemp = path + fileName + ".dat";
		File file = new File(filenameTemp);
		try {
			if (file.exists()) {
				file.delete();
				bool = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bool;
	}

	/**
	 * 读取文件
	 *
	 * @param filePath 文件名称
	 * @return
	 */
	public static byte[] readFiletoByteArray(String filePath){
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new FileInputStream(filePath);
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = in.read(buffer)) != -1) {
				out.write(buffer, 0, n);
			}
			return out.toByteArray();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			//不要忘记关闭
			try {
				if (in != null) {
					in.close();
				}

				if (out != null) {
					out.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
//		byte[] bs = new byte[] { 1, 2, 3, 4, 5 };
//		BlockChainFileUtils.createFile("blk0001", bs);
//		try {
//			byte[] result = BlockChainFileUtils.readFiletoByteArray("D:\\github\\cryptocurrency\\bitcoin-datastruct\\src\\test\\resources\\block-000000000000000000f061205567dc79c4e718209a568879d66132e016968ac6.dat");
//			System.out.println(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
			byte[] result = BlockChainFileUtils.readFiletoByteArray("D:\\meiren\\blk0001.dat");
			System.out.println(result);

	}

}
