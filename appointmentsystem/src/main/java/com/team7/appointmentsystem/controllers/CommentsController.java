package com.team7.appointmentsystem.controllers;

import com.team7.appointmentsystem.entity.Comments;
import com.team7.appointmentsystem.models.StrObject;
import com.team7.appointmentsystem.repository.CommentsRepository;
import com.team7.appointmentsystem.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Transactional
    @PostMapping("user/giveReview/{appointmentId}")
    public ResponseEntity<StrObject> giveReview(@PathVariable long appointmentId, @RequestBody Comments comment){
        String msg = commentsService.giveReview(appointmentId, comment);
        StrObject object = new StrObject(msg);
        return ResponseEntity.ok(object);
    }

}