package com.common.model;

import com.common.utils.DateUtils;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
public class BaseDao {

    protected Long created;
    protected Long updated;

    @PrePersist
    protected void onCreate() {
        created =  DateUtils.getCurrentTimeInUTC();
        updated =  DateUtils.getCurrentTimeInUTC();
    }

    @PreUpdate
    protected void onUpdate() {
        updated =  DateUtils.getCurrentTimeInUTC();
    }


}
