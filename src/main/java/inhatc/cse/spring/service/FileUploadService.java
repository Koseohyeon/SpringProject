package inhatc.cse.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    // 파일 저장 경로
    private static final String UPLOAD_DIR = "src/main/resources/static/images";

    public String uploadFile(MultipartFile file) throws IOException {
        // 파일명을 클린(clean)하게 가져옵니다.
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // 파일을 저장할 경로를 설정합니다.
        Path uploadPath = Paths.get(UPLOAD_DIR);

        // 디렉토리가 존재하지 않으면 생성합니다.
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 파일을 실제 경로에 복사합니다.
        try {
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("파일 업로드에 실패하였습니다.", e);
        }
        // 파일의 저장된 경로를 반환합니다.
        return "/images/" + fileName;
    }
}

