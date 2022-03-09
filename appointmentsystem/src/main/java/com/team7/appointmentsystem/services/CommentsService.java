package com.team7.appointmentsystem.services;

import com.team7.appointmentsystem.entity.Appointment;
import com.team7.appointmentsystem.entity.Comments;
import com.team7.appointmentsystem.exceptions.InternalServerException;
import com.team7.appointmentsystem.repository.AppointmentRepository;
import com.team7.appointmentsystem.repository.CommentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommentsService.class);

    public List<Comments> getLatestComments() {
        List<Comments> comments = commentsRepository.findTop5ByOrderByLocalDateTimeAsc();
        return comments;
    }

    public String giveReview(long appointmentId, Comments comment) {
        try {
            Appointment appointment = appointmentRepository.findById(appointmentId).get();
            Comments comments = new Comments(
                    appointment.getBusiness(),
                    appointment.getUsers(),
                    comment.getFeedback(),
                    comment.getRating()
            );
            System.out.println(comment);
            Comments comment1 = commentsRepository.save(comments);
            if (comment1 == null) {
                throw new InternalServerException("InternalServerException");
            }
            else{
                return "Review Sent Successfully!!!";
            }
        } catch (Exception e){
            logger.error(e.getMessage());
            return "Review not sent";
        }
    }
}
