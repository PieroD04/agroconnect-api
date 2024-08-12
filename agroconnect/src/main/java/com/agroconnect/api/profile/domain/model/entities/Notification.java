package com.agroconnect.api.profile.domain.model.entities;

import com.agroconnect.api.iam.domain.model.aggregates.User;
import com.agroconnect.api.profile.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Message is required")
    private String message;
    @NotNull(message = "Date is required")
    private Date sendAt;
    @NotNull(message = "Meeting URL is required")
    private String meetingUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notification() {}

    public Notification(CreateNotificationCommand command, User user) {
        this.title = command.title();
        this.message = command.message();
        this.sendAt = command.sendAt();
        this.meetingUrl = command.meetingUrl();
        this.user = user;
    }

    public Long getUserId() {
        return this.user.getId();
    }

}
