//package in.akntech.main.services.impl;
//
//import java.awt.image.ImagingOpException;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Random;
//import java.util.UUID;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import in.akntech.main.services.FileService;
//
//public class FileServiceImpl implements FileService {
//
//	@Override
//	public String uploadImage(String path, MultipartFile file) throws ImagingOpException {
//			//File name
//			String name = file.getOriginalFilename();
//		
////			Random access generate file
//			String randomID = UUID.randomUUID().toString();
//			String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
//			
////			FullPath
//			String filePath = path + File.separator + fileName1;
//			
////			Create folder if not exist
//			File f = new File(path);
//			if(!f.exists()) {
//				f.mkdir();
//			}
//			
////			File Copy
//			Files.copy(file.getInputStream(), Paths.get(filePath));
//			
//			
//		return name;  
//	}
//
//	@Override
//	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
//		
//		String fullPath = path + File.separator + fileName;
//		InputStream isInputStream = new FileInputStream(fullPath);
//		
//		return is;
//	}
//	
//}
