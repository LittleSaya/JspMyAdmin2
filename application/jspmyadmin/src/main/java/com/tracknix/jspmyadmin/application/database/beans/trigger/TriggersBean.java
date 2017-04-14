package com.tracknix.jspmyadmin.application.database.beans.trigger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tracknix.jspmyadmin.framework.web.utils.Bean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Yugandhar Gangu
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TriggersBean extends Bean {

    private static final long serialVersionUID = 1L;

    private List<TriggerInfo> trigger_list;
    private String[] triggers;
    private String[] msgs;
}
