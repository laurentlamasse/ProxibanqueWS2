package com.gtm.proxibanquews.dao;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gtm.proxibanquews.domaine.Virement;


@Repository("virementDao")
public interface IVirementDao extends JpaRepository<Virement, Integer>{
}
