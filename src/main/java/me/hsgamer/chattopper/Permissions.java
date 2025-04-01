package me.hsgamer.chattopper;

import io.github.projectunified.minelib.plugin.base.BasePlugin;
import io.github.projectunified.minelib.plugin.permission.PermissionComponent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions extends PermissionComponent {
    public static final Permission GET_TOP = new Permission("chattopper.gettop", PermissionDefault.TRUE);

    public Permissions(BasePlugin plugin) {
        super(plugin);
    }
}
