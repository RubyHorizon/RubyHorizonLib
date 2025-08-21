package team.rubyhorizon.lib.wrappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class BukkitTasksHolder {

    private final Plugin plugin;

    private final List<TasksListener> tasksListener = new ArrayList<>();

    public BukkitTasksHolder(Plugin plugin) {
        this.plugin = plugin;
    }

    public TasksListener createListener() {
        var listener = new TasksListener();
        tasksListener.add(listener);
        return listener;
    }

    public void cancelAllTasks() {
        for (TasksListener listener: tasksListener) {
            listener.cancelAllTasks();
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class TasksListener {
        private final List<BukkitTask> tasks = new ArrayList<>();

        public BukkitTask runTask(Runnable runnable) {
            var task = Bukkit.getScheduler().runTask(plugin, runnable);
            tasks.add(task);
            return task;
        }

        public BukkitTask runTaskAsync(Runnable runnable) {
            var task = Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
            tasks.add(task);
            return task;
        }

        public BukkitTask runDelayingTask(Runnable runnable, long delay) {
            var task = Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
            tasks.add(task);
            return task;
        }

        public BukkitTask runDelayingTaskAsync(Runnable runnable, long delay) {
            var task = Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
            tasks.add(task);
            return task;
        }

        public BukkitTask runRepeatingTask(Runnable runnable, long delay, long period) {
            var task = Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, period);
            tasks.add(task);
            return task;
        }

        public BukkitTask runRepeatingTaskAsync(Runnable runnable, long delay, long period) {
            var task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, period);
            tasks.add(task);
            return task;
        }

        public void cancelAllTasks() {
            for (BukkitTask task: tasks) {
                task.cancel();
            }
        }
    }
}
