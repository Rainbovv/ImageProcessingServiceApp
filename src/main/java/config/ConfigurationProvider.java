package config;

import java.io.FileFilter;

public class ConfigurationProvider {

	private ConfigurationProvider() {}


	public String getPath(String fileFormat) {

		String path = null;

		if (fileFormat.equals("jpeg") || fileFormat.equals("jpg"))
			path = "images/original/";

		return path;
	}

	public FileFilter getFilter(String format) {

		FileFilter fileFilter = null;

		if (format.equals("jpeg") || format.equals("jpg")) {
			fileFilter = file -> {

				String fileName = file.getName();

				if (file.isDirectory() || (!fileName.contains(".jpeg") &&
						!fileName.contains(".jpg"))) {
					return false;
				}
				String format1 = fileName.substring(fileName.lastIndexOf('.'));

				return format1.equals(".jpeg") || format1.equals(".jpg");
			};
		}
		return fileFilter;
	}

	private static class SingletonHolder {

		private static final ConfigurationProvider INSTANCE = new ConfigurationProvider();
	}

	public static ConfigurationProvider getInstance() {

		return SingletonHolder.INSTANCE;
	}
}
