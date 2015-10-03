//package co.sunny.utils;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.util.ArrayList;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//
//public class LatestFiles {
//	public static long lastMod = Long.MIN_VALUE;
//
//	public static ArrayList<File> lastFilesModified(FTPFile[] files) {
////		File[] files = fl.listFiles(new FileFilter() {
////			public boolean accept(File file) {
////				return file.isFile();
////			}
//		// });
//		ArrayList<File> choices = new ArrayList<File>();
//		long latestModified = lastMod;
//		for (File file : files) {
//			if (file.lastModified() > lastMod) {
//				choices.add(file);
//				if (file.lastModified() > latestModified) {
//					latestModified = file.lastModified();
//				}
//			}
//		}
//		lastMod = latestModified;
//		return choices;
//	}
// }
