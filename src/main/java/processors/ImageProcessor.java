package processors;

import com.twelvemonkeys.image.ResampleOp;
import config.ConfigurationProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

public class ImageProcessor extends AbstractProcessor{

	String fileFormat;

	private ConfigurationProvider configurationProvider;

	public ImageProcessor(String fileFormat) {
		this.configurationProvider = ConfigurationProvider.getInstance();
		this.fileFormat = fileFormat;
	}

	public void transform(String fileName) {

		String path = configurationProvider.getPath(this.fileFormat);

		try {
			BufferedImage bio = ImageIO.read(new File(path + fileName));
			BufferedImageOp resampler = new ResampleOp(200, 200, ResampleOp.FILTER_LANCZOS);
			BufferedImage bir = resampler.filter(bio, null);

			System.out.println("FILE PROCESSED -> " + fileName);

			ImageIO.write(bir, this.fileFormat,
					new File(path
							.replace("original", "processed") + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
