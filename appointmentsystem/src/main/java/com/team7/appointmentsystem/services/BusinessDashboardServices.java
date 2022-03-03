package com.team7.appointmentsystem.services;


import com.team7.appointmentsystem.entity.*;
import com.team7.appointmentsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusinessDashboardServices {


    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoriesRepository categoriesRepository;
    @Autowired
    BusinessAddressRepository businessAddressRepository;
    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    BusinessWorkingHoursRepository businessWorkingHoursRepository;
    @Autowired
    ServicesRepository servicesRepository;

    public String registerBusiness(Long userid, Business business){

        try {
            Users users = userRepository.findById(userid).get();
            int id = business.getCategories().getCategoryId();
            Categories categories = categoriesRepository.findById(id).get();


            business.setUsers(users);
            business.setCreatedTime(LocalDateTime.now());
            business.setBusinessAddress(business.getBusinessAddress());
            business.setCategories(categories);
            businessAddressRepository.save(business.getBusinessAddress());
            businessRepository.save(business);
            List<BusinessWorkingHours> l1 = business.getWorkingHours();
            for (BusinessWorkingHours businessWorkingHours: l1) {
                businessWorkingHours.setBusinessHours(business);
                businessWorkingHoursRepository.save(businessWorkingHours);
            }
            return "business registered";
        }
        catch (Exception e){
            return "Business Not registered";
        }

    }
    public ResponseEntity<String> saveProfile( MultipartFile multipartFile, Long businessId) throws IOException
    {
        String profileImg = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Business business = businessRepository.findByBusinessId(businessId);
        business.setBusinessImages(profileImg);
        Business savedBusiness = businessRepository.save(business);
        String uploadDir = "./profile-image/" + savedBusiness.getBusinessid();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(profileImg);
            System.out.println(filePath.toFile().getAbsolutePath().toString()) ;
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e) {
            throw  new IOException("Could not save uploaded file:" + profileImg);
        }
        return ResponseEntity.ok("File uploaded Successfully");
    }


    public String addServices(Long businessid, Services service){

        try {
            Business business= businessRepository.findById(businessid).get();
            service.setBusiness(business);

            Services services=servicesRepository.save(service);
            // System.out.println(services);
            return "Service Added";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Unable to add service ";
        }

    }


    public String updateServices(Long businessid,Services updateService){

        try {
            Business business = businessRepository.findById(businessid).get();
            updateService.setBusiness(business);
            Services services = servicesRepository.save(updateService);
            return "Service updated";
        }
        catch (Exception e){
            e.printStackTrace();
            return "service cant be updated";
        }


    }


    public String deleteServices( Long businessid, Long serviceid){

        try {
            Services services=servicesRepository.findByServiceidAndBusinessBusinessid(serviceid,businessid);
            servicesRepository.delete(services);
            return "Service deleted";
        }
        catch (Exception e){
            e.printStackTrace();
            return "service cant be deleted";
        }

    }

}