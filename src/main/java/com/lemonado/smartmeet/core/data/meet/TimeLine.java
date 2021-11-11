package com.lemonado.smartmeet.core.data.meet;

import java.time.LocalDateTime;
import java.util.Set;

public record TimeLine(
        LocalDateTime startTie,
        LocalDateTime endTime,
        Set<User> users) {

}
