# BukkitTasksHolder

This class was created to avoid the need to use 
*org.bukkit.plugin.Plugin* in classes that do not require it.

This class was created for the delimited use of 
org.bukkit.plugin.Plugin in Scheduler, 
and also provides additional functionality 
in the form of completing all tasks registered in this class.

```java
TasksHolder tasksHolder = new TasksHolder(yourPluginInsntace);
```

Additional usage:
```java
TasksHolder.TasksListener listener = tasksHolder.createListener();

BukkitTask runnable1 = listener.runTask(runnable);
BukkitTask runnable2 = listener.runTaskAsync(runnable);
BukkitTask runnable3 = listener.runDelayingTask(runnable, delay);
BukkitTask runnable4 = listener.runDelayingTaskAsync(runnable, delay);
BukkitTask runnable5 = listener.runRepeatingTask(runnable, delay, period);
BukkitTask runnable6 = listener.runRepeatingTaskAsync(runnable, delay, period);

// Stops all active tasks in this TasksHolder.TasksListener
listener.cancelAllTasks();

// Stops all active tasks in this TasksHolder
tasksHolder.cancelAllTasks();
```

[Back to information page](../info.md)