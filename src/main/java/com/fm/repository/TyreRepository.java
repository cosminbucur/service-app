package com.fm.repository;

import com.fm.model.Tyre;

import java.util.List;

public interface TyreRepository {

    Tyre save(Tyre tyre);

    List<Tyre> findAll();
}
