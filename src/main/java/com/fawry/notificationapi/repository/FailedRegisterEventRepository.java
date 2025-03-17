package com.fawry.notificationapi.repository;

import com.fawry.kafka.events.enums.EventStatus;
import com.fawry.notificationapi.model.FailedRegisterEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FailedRegisterEventRepository extends JpaRepository<FailedRegisterEvent, Long> {

    List<FailedRegisterEvent> findFailedRegisterEventByEventStatusIn(List<EventStatus> eventStatuses);
}
