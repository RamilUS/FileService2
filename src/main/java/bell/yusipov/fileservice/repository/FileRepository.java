package bell.yusipov.fileservice.repository;

import bell.yusipov.fileservice.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Интерфейс работы с файлами в БД
 */
public interface FileRepository extends JpaRepository<File, Integer> {

    /**
     * Обновление счетчика скачиваний
     * @param id - индификатор файла
     */
    @Modifying(clearAutomatically = true)
    @Query("update File e set e.downloadCount=:downloadCount where  e.id = :id")
    void update(@Param("downloadCount") Integer downloadCount, @Param("id") Integer id);
}
