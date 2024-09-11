package com.library.dao;

import com.library.model.Magazine;

import java.util.HashMap;

public interface MagazineDAO {
    public HashMap<String, Magazine> getAll();
    public Magazine get(String id);
    public void create(Magazine magazine);
    public void delete(Magazine magazine);
    public void update(Magazine magazine);
}
