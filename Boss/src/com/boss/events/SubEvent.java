package com.boss.events;

import org.bukkit.event.Listener;

public abstract class SubEvent implements Listener{

    public abstract String name();
    public abstract String info();

}
