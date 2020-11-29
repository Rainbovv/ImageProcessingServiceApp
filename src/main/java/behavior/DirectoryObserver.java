package behavior;


import config.ConfigurationProvider;
import processors.AbstractProcessor;
import processors.ImageProcessor;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

public class DirectoryObserver {

	private final ConfigurationProvider configurationProvider;
	private final HashMap<String, AbstractProcessor> processors;

	public DirectoryObserver() {
		this.configurationProvider = ConfigurationProvider.getInstance();
		processors = new HashMap<>();
		processors.put("jpeg", new ImageProcessor("jpeg"));
	}

	public void observe(String fileFormat) {

		System.out.println("\nOBSERVER STARTED...\n--------------------------------");

		File rootDir = new File(configurationProvider.getPath(fileFormat));

		if (!rootDir.exists() || !rootDir.isDirectory()) {
			System.err.println("ERROR!!! THE ROOT DIRECTORY DOESN'T EXIST!");
			return;
		}

		while (true) {
			File[] files = rootDir.listFiles(configurationProvider.getFilter(fileFormat));
			File[] lastFiles = new File(configurationProvider.getPath(fileFormat)
					.replace("original", "processed")).listFiles();

			assert files != null;
			if (!Arrays.deepEquals(lastFiles, files)) {

				assert lastFiles != null;
				Arrays.asList(files).forEach(f -> {
					for (File file : lastFiles) {

						if (f.getName().equals(file.getName())) return;
					}
					AbstractProcessor processor = processors.get(fileFormat);
					assert processor != null;
						processor.transform(f.getName());
				});
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
