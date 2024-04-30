package com.notes.app.interfaces;

import com.notes.app.domain.Trigger;

import java.util.List;

public interface TriggerService {

    Trigger addTriggerPoints(Trigger trigger);

    List<Trigger> getTriggers();

    Trigger updateTriggers(Trigger trigger);

    List<Trigger> saveTriggers(List<Trigger> triggers);

    void deleteTriggers(Trigger triggerId);

    Trigger getTriggersById(String triggerId);
}
