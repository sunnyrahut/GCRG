package co.sunny.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class CopyFile {
	public static boolean copyNoGapFile(FTPFile sourceFile,
			FileOutputStream destFile, FTPClient ftpClient) {
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
	}

	public static boolean copyMeteoFile(FTPFile sourceFile,
			FileOutputStream destFile, FTPClient ftpClient) {
		try {
			if (sourceFile.getName().startsWith("ATQ_CR23X_met_")) {
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
	}
}