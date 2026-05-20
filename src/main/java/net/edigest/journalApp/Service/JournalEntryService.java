package net.edigest.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.edigest.journalApp.entity.JournalEntry;
import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

        @Autowired
        private JournalEntryRepository journalEntryRepository;

        @Autowired
        private UserService userService;


    public void saveEntry(JournalEntry journalEntry, String userName){
            try {
                User user = userService.findByUserName(userName);

                if (user == null) {
                    throw new RuntimeException("User not found");
                }
                journalEntry.setDate(LocalDateTime.now());
                JournalEntry saved = journalEntryRepository.save(journalEntry);
                user.getJournalEntries().add(saved);
                userService.saveUser(user);
            }catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException("An error occurred while saving entry." , e);
            }
    }

    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }



    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){

        return journalEntryRepository.findById(id);
    }

    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
            return removed;
        }catch(Exception e){
            log.error("Error occurred while deleting entry.", e);
            throw new RuntimeException("An error occurred while deleting the entry." , e);
        }

    }


    }

