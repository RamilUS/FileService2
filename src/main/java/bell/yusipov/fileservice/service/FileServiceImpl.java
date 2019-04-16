package bell.yusipov.fileservice.service;

import bell.yusipov.fileservice.exception.FileUploadException;
import bell.yusipov.fileservice.model.File;
import bell.yusipov.fileservice.repository.FileRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    private final FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String upload(MultipartFile uploadFile, String description) {
        logger.info("[FILE_SERVICE]: upload service running with description = " + description + " by FileService.upload");
        String report = "";
        if (StringUtils.isEmpty(description)) {
            logger.info("[FILE_SERVICE]: file description not found");
            throw new IllegalArgumentException("File description not found");
        }
        if (uploadFile == null) {
            logger.info("[FILE_SERVICE]: file not selected");
            throw new IllegalArgumentException("No file selected");
        }
        File file = new File();
        file.setDescription(description);
        file.setFileName(uploadFile.getOriginalFilename());
        file.setDownloadCount(0);
        try {
            file.setFileData(uploadFile.getBytes());
        } catch (IOException e) {
            logger.error("[FILE_SERVICE]: IOException while file.setFileData(uploadFile.getBytes())");
            throw new FileUploadException("File loading error", e);
        }
        //if (!report.equals("File loading error") ) {
        fileRepository.save(file);
        logger.info("[FILE_SERVICE]: file is uploaded");
        report = "file is uploaded";
        return report;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getByFileById(Integer fileId) {
        logger.info("[FILE_SERVICE]:Getting file with fileId = " + fileId + " by fileService.getByFileById");
        if (fileId == null) {
            logger.error("[FILE_SERVICE]: fileService.getByFileById has null parameter");
            throw new IllegalArgumentException("File Id cannot be null");
        }
        return fileRepository.getOne(fileId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<File> getFileList() {
        logger.info("[FILE_SERVICE]: Getting file list  by fileService.etFileList");
        return fileRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResponseEntity<ByteArrayResource> download(Integer fileId) {
        if (fileId == null) {
            logger.error("[FILE_SERVICE]: fileService.download has null parameter");
            throw new IllegalArgumentException("File id cannot be null ");
        }
        logger.info("[FILE_SERVICE]: downloading file " + fileRepository.getOne(fileId).getFileName());
        File file = fileRepository.getOne(fileId);
        byte[] data = file.getFileData();
        ByteArrayResource resource = new ByteArrayResource(data);
        Integer downloadCount = file.getDownloadCount();
        downloadCount++;
        fileRepository.update(downloadCount, fileId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(data.length)
                .body(resource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public String remove(Integer fileId) {
        logger.info("[FILE_SERVICE]: removing file with id " + fileId);
        if (fileId == null) {
            logger.error("[FILE_SERVICE]: fileService.remove has null parameter");
            throw new IllegalArgumentException("File id cannot be null ");
        }
        File file = fileRepository.getOne(fileId);
        String report = "File was deleted";
        fileRepository.delete(file);
        return report;
    }
}
