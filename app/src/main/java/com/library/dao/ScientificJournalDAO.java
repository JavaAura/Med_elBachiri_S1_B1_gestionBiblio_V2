package com.library.dao;

import com.library.model.ScientificJournal;
import java.util.HashMap;

public interface ScientificJournalDAO {
    public HashMap<String, ScientificJournal> getAll();
    public ScientificJournal get(String id);
    public void create(ScientificJournal scientificJournal);
    public void delete(String id);
    public void update(ScientificJournal scientificJournal);
}
