package com.sandydev.order.service;

import com.sandydev.order.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.data.mongodb.core.query.Query;


@Service
public class SequenceService {

    @Autowired
    private MongoOperations mongoOperations;

    public int generateNextOrderId() {

        Sequence sequence = mongoOperations.findById("sequence", Sequence.class);

        if (sequence == null) {
            sequence = new Sequence();
            sequence.setId(1);
            sequence.setSequence(1);
        } else {
            sequence.setSequence(sequence.getSequence() + 1);
        }

        mongoOperations.save(sequence);

        return sequence.getSequence();
    }
}
