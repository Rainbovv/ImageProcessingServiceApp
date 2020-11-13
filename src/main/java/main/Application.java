package main;

import com.twelvemonkeys.image.ResampleOp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

public class Application {

	public static void main(String[] args) throws InterruptedException{

		System.out.println("STARTING");

		// Observer design pattern
			// - target / observable
		File rootDir = new File("images/original");
		if (!rootDir.exists() || !rootDir.isDirectory()) {
			System.err.println("ERROR!!! THE ROOT DIRECTORY DOESN'T EXIST!");
			return;
		}

		FileFilter fileFilter = new FileFilter() {
			@Override
			public boolean accept(File pathname) {

				String path = pathname.getPath();

				if (pathname.isDirectory() ||
						(!path.contains(".jpeg") && !path.contains(".jpg"))) {
					return false;
				}
				String format = path.substring(path.lastIndexOf('.'));

				return	format.equals(".jpeg") ||
			            format.equals(".jpg");
			}
		};

		// watch loop
		File[] lastFiles = {};

		while (true) {
			File[] files = rootDir.listFiles(fileFilter);

			if (files != null && !Arrays.deepEquals(lastFiles, files)) {

				File[] finalLastFiles = lastFiles;

				Arrays.asList(files).forEach(f -> {
					for (File file : finalLastFiles) {

						if (f.equals(file)) return;
					}
					try {
						resample(f);

					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				lastFiles = files;
			}
			Thread.sleep(5000);
		}




			//			// detect if something has changed
			//
			//			System.out.println(Arrays.mismatch(lastFiles, files));
			//			if (files.length != lastFiles.length) {
			//
			//				System.out.println("-----------------------");
			//				System.out.println("FOUND FILES: ");
			//				for (File file : files) {
			//					System.out.println(">> " + file);
			//				}
			//
			//				lastFiles = files;
			//			}
			//			Thread.sleep(100);
			//		}

	}

	public static void resample(File file) throws IOException {


		///////////////////////////////////////////////////////////////////////////
		// Transformer / Processor
		BufferedImage bio = ImageIO.read(file);


		BufferedImageOp resampler = new ResampleOp(200, 200, ResampleOp.FILTER_LANCZOS);

		BufferedImage bir = resampler.filter(bio, null);

		System.out.println("FILE PROCESSED -> " + file.getPath());

		ImageIO.write(bir, "jpeg", new File(file.getPath().replace("original","processed")));
	}
}
