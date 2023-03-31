package com.quicktodo.utility.validation;

import com.quicktodo.utility.exception.ValidationException;
import com.quicktodo.http.request.ToDoRequest;
import com.quicktodo.persistence.domain.Repeat;
import com.quicktodo.persistence.domain.WeekDay;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class ToDoRequestValidator {

    public void validate(final ToDoRequest request) {
        validateMandatoryFields(request);
        validateDates(request.getWholeDay(), request.getStartDate(), request.getEndDate());
        validateRepeat(request.getRepeat(), request.getDaysToRepeatOn());
    }

    public void validateDates(final Boolean wholeDay, final LocalDateTime startDate, final LocalDateTime endDate) {
        if(wholeDay && (startDate != null || endDate != null)) {
            throw new ValidationException("'startDate' nor 'endDate' can be defined when 'wholeDay'=true");
        }
        if(!wholeDay && (startDate == null || endDate == null)) {
            throw new ValidationException("'startDate' and 'endDate' must be defined when 'wholeDay'=false");
        }
        if(Objects.requireNonNull(startDate).isBefore(LocalDateTime.now()) || startDate.isAfter(endDate)) {
            throw new ValidationException("Invalid dates provided");
        }
    }

    public void validateRepeat(final Repeat repeat, final List<WeekDay> days) {
        if(repeat != Repeat.CUSTOM && !days.isEmpty()) {
            throw new ValidationException("'daysToRepeatOn' are only allowed when 'repeat'=CUSTOM");
        }
        if(repeat == Repeat.CUSTOM && days.isEmpty()) {
            throw new ValidationException("'daysToRepeatOn' are mandatory when 'repeat'=CUSTOM");
        }
    }

    public void validateMandatoryFields(final ToDoRequest request) {
        if(request.getName() == null || request.getWholeDay() == null || request.getRepeat() == null) {
            throw new ValidationException("Fields 'name', 'wholeDay' and 'repeat' are mandatory");
        }
    }

}
