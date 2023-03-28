package com.school.eventdrivenproject.services;

import com.school.eventdrivenproject.controllers.dtos.requests.CreatePackageRequest;
import com.school.eventdrivenproject.entities.Package;
import com.school.eventdrivenproject.repositories.IPackageRepository;
import com.school.eventdrivenproject.services.interfaces.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageServiceImpl implements IPackageService {

    @Autowired
    private IPackageRepository repository;

    @Override
    public Package create(CreatePackageRequest request) {
        Package packageResponse = from(request);
        return repository.save(packageResponse);
    }

    private Package from(CreatePackageRequest request){
        Package packageResponse = new Package();
        packageResponse.setHeight(request.getHeight());
        packageResponse.setWidth(request.getWidth());
        packageResponse.setWeight(request.getWeight());
        return packageResponse;
    }
}
