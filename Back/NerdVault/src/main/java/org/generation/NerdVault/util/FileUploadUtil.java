package org.generation.NerdVault.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import org.generation.NerdVault.config.CustomProperties;
import org.generation.NerdVault.entities.Prodotto;

public class FileUploadUtil {
	
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		
		// Converto percorso stringa in un path
		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			// Creo cartella se non esiste dove salvare
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);	// Percorso file completo
			// Sovrascrive file se già presente con lo stesso nome
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}

	public static void deleteDir(Prodotto trovato) {
		try {
			String dir = CustomProperties.IMG_URL_PATH + "/" + trovato.getProdottoId();
			
			if (Files.exists(Paths.get(dir))) {
				FileUtils.deleteDirectory(new File(dir));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
