package by.it_academy.jd2.m_jd2_88_22.chat.storage.db.api;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.demo.model.Pageable;

import java.util.List;

public interface IAuditUsersDB {


    Long create (Audit audit);
    List<Audit> read(Pageable pageable);
}
