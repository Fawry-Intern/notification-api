package com.fawry.notificationapi.repository;

import com.fawry.kafka.events.enums.EventStatus;
import com.fawry.notificationapi.entities.FailedRegisterEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FailedRegisterEventRepository extends JpaRepository<FailedRegisterEvent, Long> {

    List<FailedRegisterEvent> findFailedRegisterEventByEventStatusIn(List<EventStatus> eventStatuses);
}
