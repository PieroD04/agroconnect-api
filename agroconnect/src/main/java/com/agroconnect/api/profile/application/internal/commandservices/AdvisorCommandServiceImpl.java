package com.agroconnect.api.profile.application.internal.commandservices;

import com.agroconnect.api.profile.application.internal.outboundservices.acl.ExternalUserService;
import com.agroconnect.api.profile.domain.exceptions.AdvisorNotFoundException;
import com.agroconnect.api.profile.domain.exceptions.SameUserException;
import com.agroconnect.api.profile.domain.exceptions.UserNotFoundException;
import com.agroconnect.api.profile.domain.model.commands.CreateAdvisorCommand;
import com.agroconnect.api.profile.domain.model.commands.DeleteAdvisorCommand;
import com.agroconnect.api.profile.domain.model.commands.UpdateAdvisorCommand;
import com.agroconnect.api.profile.domain.model.entities.Advisor;
import com.agroconnect.api.profile.domain.services.AdvisorCommandService;
import com.agroconnect.api.profile.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvisorCommandServiceImpl implements AdvisorCommandService {
    private final AdvisorRepository advisorRepository;
    private final ExternalUserService externalUserService;

    public AdvisorCommandServiceImpl(AdvisorRepository advisorRepository, ExternalUserService externalUserService) {
        this.advisorRepository = advisorRepository;
        this.externalUserService = externalUserService;
    }

    @Override
    public Long handle(CreateAdvisorCommand command) {
        var user = externalUserService.fetchUserById(command.userId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(command.userId());
        }
        var sameUser = advisorRepository.findByUser_Id(command.userId());
        if (sameUser.isPresent()) {
            throw new SameUserException(command.userId());
        }
        Advisor advisor = new Advisor(command, user.get());
        advisorRepository.save(advisor);
        return advisor.getId();
    }

    @Override
    public Optional<Advisor> handle(UpdateAdvisorCommand command) {
        var advisor = advisorRepository.findById(command.id());
        if (advisor.isEmpty()) {
            return Optional.empty();
        }
        var advisorToUpdate = advisor.get();
        Advisor updatedAdvisor = advisorRepository.save(advisorToUpdate.update(command));
        return Optional.of(updatedAdvisor);
    }

    @Override
    public void handle(DeleteAdvisorCommand command) {
        var advisor = advisorRepository.findById(command.id());
        if (advisor.isEmpty()) {
            throw new AdvisorNotFoundException(command.id());
        }
        advisorRepository.delete(advisor.get());
    }
}
