package com.logica3.ship;

import com.logica3.events.Event;
import com.logica3.planet.Planet;

public record Report (
        Planet planet,
        Event event,
        Room[][] roomsBeforeDamage,
        Room[][] roomsAfterDamage
) {
}
