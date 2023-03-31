package com.quicktodo.persistence.entity;

import com.quicktodo.persistence.domain.Repeat;
import com.quicktodo.persistence.domain.WeekDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TODOS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ToDo {

    @Id
    @Column(name = "TODO_ID")
    private long todoId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR(1000)")
    private String description;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "WHOLE_DAY")
    private Boolean wholeDay;

    @Column(name = "DONE")
    private Boolean done;
    
    @Column(name = "STARRED")
    private Boolean starred;

    @Column(name = "REPEAT")
    @Enumerated(EnumType.STRING)
    private Repeat repeat;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = WeekDay.class)
    private List<WeekDay> daysToRepeatOn;

    @ManyToOne
    private User owner;

}
