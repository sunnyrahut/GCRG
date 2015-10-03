package co.sunny.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPConnection {

	public static long lastMod;

	public FTPClient getConnection(String server, int port, String user,
			String password, String directory) {
		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.connect(server, port);
			boolean success = ftpClient.login(user, password);
			if (success) {
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				ftpClient.changeWorkingDirectory(directory);
				System.out.println("FTP Connection established..");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FTP connection not established..");
		}
		return ftpClient;
	}

	public ArrayList<FTPFile> getFTPFiles(FTPClient ftpClient) {
		try {
			ArrayList<FTPFile> choices = new ArrayList<FTPFile>();
			if (ftpClient.isConnected()) {
				Date date = new Date(lastMod);
				System.out.println("Retreiving files after.." + date);
				FTPFile[] files = ftpClient.listFiles();
				DateFormat dateFormater = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				long latestModified = lastMod;
				for (FTPFile file : files) {
					String details = file.getName();
					if (file.isDirectory()) {
						details = "[" + details + "]";
					}
					details += "\t\t" + file.getSize();
					details += "\t\t"
							+ dateFormater
									.format(file.getTimestamp().getTime());
					System.out.println(details);
					if (file.getTimestamp().getTime().getTime() > lastMod) {
						choices.add(file);
						if (file.getTimestamp().getTime().getTime() > latestModified) {
							latestModified = file.getTimestamp()
									.getTimeInMillis();
						}
					}
				}
				lastMod = latestModified;
				return choices;
			} else {
				System.out.println("Login Failed..");
				return null;
			}
		} catch (IOException ex) {
			System.out.println("Something is wrong!!");
			ex.printStackTrace();
			return null;
		}
	}
}
