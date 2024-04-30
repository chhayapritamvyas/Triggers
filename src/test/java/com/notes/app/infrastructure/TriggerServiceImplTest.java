package com.notes.app.infrastructure;

import com.notes.app.domain.Trigger;
import com.notes.app.repositories.TriggerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TriggerServiceImplTest {

    @InjectMocks
    private TriggerServiceImpl triggerService;
    @Mock
    TriggerRepository triggerRepository;

    @Test
    void addTriggerPoints() {
        Trigger trigger = getTrigger();
        Mockito.when(triggerRepository.save(trigger)).thenReturn(trigger);
        Trigger triggerPoints = triggerService.addTriggerPoints(trigger);

        assertThat(triggerPoints).isNotNull();
        assertThat(triggerPoints.getPointStatus()).isNull();
    }

    @Test
    void getTriggers() {
        Mockito.when(triggerRepository.findAll()).thenReturn(getTriggersList());
        List<Trigger> triggers = triggerService.getTriggers();

        assertThat(triggers).hasSizeGreaterThan(1);
    }

    private List<Trigger> getTriggersList() {
        List<Trigger> triggerList = new ArrayList<>();
        triggerList.add(getTrigger());
        triggerList.add(getTrigger());
        return triggerList;
    }

    @Test
    void updateTriggers() {
        Trigger updateTrigger = getTrigger();
        updateTrigger.setId('1');
        updateTrigger.setNotes("new notes");
        Mockito.when(triggerRepository.save(updateTrigger)).thenReturn(updateTrigger);
        Trigger trigger = triggerService.updateTriggers(updateTrigger);

        assertThat(trigger).isNotNull();
        assertThat(trigger.getNotes()).contains("notes");
    }

    @Test
    void saveTriggers() {
        List<Trigger> arrayList = new ArrayList<>();
        List<Trigger> triggerList = triggerService.saveTriggers(arrayList);

        assertThat(triggerList.size()).isNotNegative();
        Assertions.assertEquals(triggerList, arrayList);
    }

    @Test
    void deleteTriggers() {
        Trigger trigger = getTrigger();
        triggerService.deleteTriggers(trigger);

        Mockito.verify(triggerRepository, Mockito.times(1)).delete(any());
    }

    private static Trigger getTrigger() {
        return new Trigger();
    }

    @Test
    void getTriggersById() {
        Long id = 11L;
        Trigger trigger = getTrigger();
        Mockito.when(triggerRepository.findById(id)).thenReturn(Optional.of(trigger));
        Trigger triggersById = triggerService.getTriggersById(String.valueOf(id));

        assertThat(triggersById).isNotNull();
    }
}