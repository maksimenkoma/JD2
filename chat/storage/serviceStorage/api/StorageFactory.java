package by.it_academy.jd2.m_jd2_88_22.chat.storage.serviceStorage.api;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.serviceStorage.StorageService;

public class StorageFactory implements IStorageFactory {

    private static final StorageFactory instance = new StorageFactory();

    @Override
    public IStorageService getStorageService() {
        return StorageService.getInstance();
    }

    public static StorageFactory getInstance() {
        return instance;
    }
}
