package com.ispc.destinosapp.repository;

import com.ispc.destinosapp.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDestinationDao extends JpaRepository<Destination, Long> {

    List<Destination> findAllByLocation_NameIs(String name);

}
