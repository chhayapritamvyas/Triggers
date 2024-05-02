package com.notes.app.application;

import com.notes.app.domain.Trigger;
import com.notes.app.interfaces.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TriggerController {

    @Autowired
    private TriggerService triggerService;

    @PostMapping
    public ResponseEntity<String> addTriggerPoints(@RequestBody Trigger trigger) {
        Trigger triggerPoints = triggerService.addTriggerPoints(trigger);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added trigger points successfully" + triggerPoints);
    }

    @PostMapping("/triggers")
    public ResponseEntity<String> saveTriggers(@RequestBody List<Trigger> triggerList){
        List<Trigger> triggers = triggerService.saveTriggers(triggerList);
        return ResponseEntity.ok("Saved Triggers + " + triggers);
    }

    @GetMapping
    public List<Trigger> getTriggerPoints() {
       return triggerService.getTriggers();
    }

    @GetMapping("/triggers/{triggerId}")
    public Trigger getTriggersById(@PathVariable String triggerId){
        return triggerService.getTriggersById(triggerId);
    }

    @PutMapping("/triggers/update")
    public String updateTriggers(@RequestBody Trigger trigger){
        Trigger updatedTriggers = triggerService.updateTriggers(trigger);
        return "Updated Triggers successfully " + updatedTriggers;
    }

    @DeleteMapping("/triggers/delete")
    public ResponseEntity<String> deleteTriggers(@RequestBody Trigger trigger){
        triggerService.deleteTriggers(trigger);
        return ResponseEntity.ok().body("Deleted Triggers successfully");
    }
}
