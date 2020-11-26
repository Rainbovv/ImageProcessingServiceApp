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

		System.out.println("started");

		File rootDir = new File(configurationProvider.getPath(fileFormat));

		if (!rootDir.exists() || !rootDir.isDirectory()) {
			System.err.println("ERROR!!! THE ROOT DIRECTORY DOESN'T EXIST!");
			return;
		}

		File[] lastFiles = {};

		while (true) {
			File[] files = rootDir.listFiles(configurationProvider.getFilter(fileFormat));

//			Arrays.stream(files).forEach(System.out::println);

//			lastFiles = new File(rootDir
//					.getPath()
//					.replace("original", "processed"))
//					.listFiles();
//
//			Arrays.stream(lastFiles).forEach(System.out::println);

//			System.out.println(Arrays.equals(lastFiles, files));

			if (files != null && !Arrays.deepEquals(lastFiles, files)) {

				File[] finalLastFiles = lastFiles;

				Arrays.asList(files).forEach(f -> {
					for (File file : finalLastFiles) {

						if (f.equals(file)) return;
					}
						AbstractProcessor processor = processors.get(fileFormat);
						if (processor != null)
							processor.transform(f.getName());
				});
				lastFiles = files;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
