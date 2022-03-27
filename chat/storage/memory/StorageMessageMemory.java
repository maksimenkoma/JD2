package by.it_academy.jd2.m_jd2_88_22.chat.storage.memory;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageMessageMemory implements IMessageStorage {

    private static final StorageMessageMemory instance = new StorageMessageMemory();

    private StorageUserMemory storageUserMemory;
    private Map<String, List<Message>> historyMessages = new HashMap<>();

    public StorageMessageMemory() {

        this.storageUserMemory = StorageUserMemory.getInstance();
    }

    @Override
    public void saveMessage(Message message, User userActive) {

        List<User> users = storageUserMemory.getListUser();

        for (User user : users) {

            if (user.getLogin().equals(message.getRecipient()) && !userActive.getLogin().equals(message.getRecipient())) {

                if (historyMessages.containsKey(message.getRecipient())) {

                    historyMessages.get(message.getRecipient()).add(new Message(userActive.getLogin(), message.getMessage(), LocalDateTime.now()));

                } else {
                    List<Message> message1 = new ArrayList<>();

                    message1.add(new Message(userActive.getLogin(), message.getMessage(), LocalDateTime.now()));

                    historyMessages.put(message.getRecipient(), message1);

                }


            }

        }
    }

    @Override
    public List<Message> getHistoryMessage(User userActive) {
        return getHistoryMessage(userActive,null);
    }



    @Override
    public List<Message> getHistoryMessage(User userActive, Pageable pageable) {
        List<Message> messages = new ArrayList<>();
        if (historyMessages.containsKey(userActive.getLogin())) {

            messages = historyMessages.get(userActive.getLogin());

        }
        return messages;
    }




    public static StorageMessageMemory getInstance() {
        return instance;
    }
}
