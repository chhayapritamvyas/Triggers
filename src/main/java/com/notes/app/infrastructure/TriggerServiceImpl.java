package com.notes.app.infrastructure;

import com.notes.app.domain.Trigger;
import com.notes.app.interfaces.TriggerService;
import com.notes.app.repositories.TriggerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TriggerServiceImpl implements TriggerService {

    @Autowired
    private TriggerRepository triggerRepository;

    @Override
    public Trigger addTriggerPoints(Trigger trigger) {
        log.info("In TriggerServiceImpl");
        return triggerRepository.save(trigger);
    }

    @Override
    public List<Trigger> getTriggers() {
        return triggerRepository.findAll();
    }

    @Override
    public Trigger updateTriggers(Trigger trigger) {
        return triggerRepository.save(trigger);
    }

    @Override
    public List<Trigger> saveTriggers(List<Trigger> triggers){
        return triggerRepository.saveAll(triggers);
    }
    @Override
    public void deleteTriggers(Trigger trigger) {
         triggerRepository.delete(trigger);
    }

    @Override
    public Trigger getTriggersById(String triggerId) {
        Optional<Trigger> optionalTrigger = triggerRepository.findById(Long.valueOf(triggerId));
        return optionalTrigger.orElseGet(Trigger::new);
    }
}
