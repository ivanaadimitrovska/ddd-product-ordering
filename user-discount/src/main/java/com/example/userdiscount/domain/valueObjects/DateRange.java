package com.example.userdiscount.domain.valueObjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Embeddable
@Getter
public class DateRange implements ValueObject {

    @Column(insertable = false, updatable = false)
    private Date startDate;
    @Column(insertable = false, updatable = false)
    private Date endDate;

    public DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected DateRange() {
        this.startDate = null;
        this.endDate = null;
    }

//    public DateRange(Date startDateCreated, Date endDateCreated) {
//    }

//    public boolean contains(LocalDate date) {
//            return !date.isBefore(startDate) && !date.isAfter(endDate);
//        }
//
//
//    }
}

