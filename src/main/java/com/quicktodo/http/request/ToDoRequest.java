package com.quicktodo.http.request;

import com.quicktodo.persistence.domain.Repeat;
import com.quicktodo.persistence.domain.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ToDoRequest {

    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Repeat repeat;
    private Boolean wholeDay;
    private List<WeekDay> daysToRepeatOn;

}
