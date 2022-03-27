package by.it_academy.jd2.m_jd2_88_22.chat.storage.memory;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageAuditMemory implements IAuditStorage {

    private static final StorageAuditMemory instance = new StorageAuditMemory();


    private List<Audit> audits = new ArrayList<>();

    @Override
    public void saveAudit(Audit audit) {

        this.audits.add(audit);

    }

    @Override
    public List<Audit> readAudit() {
        return readAudit(null);
    }


    @Override
    public List<Audit> readAudit(Pageable pageable) {


        if (pageable != null) {
            return this.audits.stream()
                    .skip((long) (pageable.getPage() - 1) * pageable.getSize())
                    .limit(pageable.getSize())
                    .collect(Collectors.toList());
        }
        return this.audits;
    }

    public static StorageAuditMemory getInstance() {
        return instance;
    }
}
