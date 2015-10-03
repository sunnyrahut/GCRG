package co.sunny.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class CopyFile {
	public static boolean copyFile(FTPFile sourceFile,
			FileOutputStream destFile, FTPClient ftpClient) {
		// if (!destFile.exists()) {
		// destFile.createNewFile();
		// }
		//
		// FileChannel source = null;
		// FileChannel destination = null;
		// try {
		try {
			if (sourceFile.getName().startsWith("TOB1_ATQ_ts_data_")
					&& sourceFile.getName().length() == 36) {
				boolean success = ftpClient.retrieveFile(sourceFile.getName(),
						destFile);
				if (success) {
					System.out.println("Copy successful!!"
							+ sourceFile.getName());
					return true;
				} else {
					return false;
				}
			} else {
				System.out
						.println("Copy unsuccessful!!" + sourceFile.getName());
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// source = new FileInputStream(sourceFile).getChannel();
		// destination = new FileOutputStream(destFile).getChannel();
		// destination.transferFrom(source, 0, source.size());
		// } finally {
		// if (source != null) {
		// source.close();
		// }
		// if (destination != null) {
		// destination.close();
		// }
		// }
	}
}