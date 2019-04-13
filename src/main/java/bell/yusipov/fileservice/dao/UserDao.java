package bell.yusipov.fileservice.dao;

import bell.yusipov.fileservice.model.User;

/**
 * Интерфейс  для работы с таблице пользователей
 */
public interface UserDao {
    /**
     * Получение пользователя по имени
     *
     * @param usrName имя пользователя
     * @return объект пользователя
     */
    User getUserByName(String usrName);
}
