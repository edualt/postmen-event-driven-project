package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.controllers.dtos.requests.CreatePackageRequest;
import com.school.eventdrivenproject.entities.Package;

public interface IPackageService {

    Package create(CreatePackageRequest request);

}
