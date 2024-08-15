package com.agroconnect.api.appointment.domain.model.aggregates;

import com.agroconnect.api.appointment.domain.model.commands.CreateAppointmentCommand;
import com.agroconnect.api.appointment.domain.model.commands.UpdateAppointmentCommand;
import com.agroconnect.api.appointment.domain.model.valueobjects.AppointmentStatus;
import com.agroconnect.api.profile.domain.model.entities.Advisor;
import com.agroconnect.api.profile.domain.model.entities.Farmer;
import com.agroconnect.api.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {
    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must be in the future")
    private LocalDate scheduledDate;

    @NotNull(message = "Starting time is required")
    @JsonFormat(pattern = "HH:mm:ss")
    private String startTime;

    @NotNull(message = "Ending time is required")
    @JsonFormat(pattern = "HH:mm:ss")
    private String endTime;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    public Appointment() {
    }

    public Appointment(CreateAppointmentCommand command, Advisor advisor, Farmer farmer) {
        this.scheduledDate = command.scheduledDate();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.status = AppointmentStatus.valueOf(command.status().toUpperCase());
        this.farmer = farmer;
        this.advisor = advisor;
    }

    public Appointment update(UpdateAppointmentCommand command){
        this.scheduledDate = command.scheduledDate();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.status = AppointmentStatus.valueOf(command.status().toUpperCase());
        return this;
    }

    public Long getAdvisorId() {
        return advisor.getId();
    }

    public Long getFarmerId() {
        return farmer.getId();
    }

    public String getAppointmentStatus() {
        return status.toString();
    }
}
