package springbootlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootlearning.model.SubscriptionType;
import springbootlearning.service.SubscriptionTypeService;

import java.util.List;

@RestController
@RequestMapping("/subscriptions-type")
public class SubscriptionTypeController {

    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

    @GetMapping
    public ResponseEntity<List<SubscriptionType>> findAll() {
        List<SubscriptionType> subscriptionTypeList = subscriptionTypeService.findAll();
        if (subscriptionTypeList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionType> finfById(@PathVariable Long id) {
        SubscriptionType subscriptionType = subscriptionTypeService.findById(id);
        if (subscriptionType == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionType);
    }
}
