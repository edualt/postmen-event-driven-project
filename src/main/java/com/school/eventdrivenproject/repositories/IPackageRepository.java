package com.school.eventdrivenproject.repositories;

import com.school.eventdrivenproject.entities.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPackageRepository extends JpaRepository<Package, Long> {
}
