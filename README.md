# **UnnamedGUI - Menu Creator**

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d288974ef2734b50bd34a800b2161c70)](https://app.codacy.com/gh/UnnamedWorks/UnnamedGUI?utm_source=github.com&utm_medium=referral&utm_content=UnnamedWorks/UnnamedGUI&utm_campaign=Badge_Grade_Dashboard)

_A small, and very easy to use, library for creating menus at Spigot API!_

## **Maven Dependency**
You only need to copy and paste this at your pom.xml, and that will be done!
````xml
<repository>
    <id>unnamed-releases</id>
    <url>https://repo.unnamed.team/repository/unnamed-releases/</url>
</repository>
````
````xml
<dependency>
    <groupId>team.unnamed.gui</groupId>
    <artifactId>gui-core</artifactId>
    <version>2.2.0-SNAPSHOT</version>
</dependency>
````

If you see that the version contains "SNAPSHOT", just use the following repository:
`````xml
<repository>
    <id>unnamed-snapshots</id>
    <url>https://repo.unnamed.team/repository/unnamed-snapshots/</url>
</repository>
`````

## **How to use**
Remember that you can also visit the [example module](https://github.com/unnamed/gui/tree/master/example/src/main/java/team/unnamed/gui/example).

First we need register one listener for all menus. Example:

````java
Bukkit.getPluginManager().registerEvents(new GUIListeners(), plugin);
````

## **MenuAPI**
Creating menus with our api is too simple, we are going to explain everything a little,
so that you can get an idea of the new functionalities and systems that were re-made.

````java
GUIBuilder guiBuilder = GUIBuilder.newBuilder("MenuTest", 3);
````
This is the basic structure to create a GUIBuilder, which will be used to place 
all the attributes it can have.

Now let's see how we can add an item to the menu, it's quite simple, let's do it.

````java
guiBuilder
    .addItem(
        ItemClickable.builder(13)
            .setItemStack(new ItemStack(Material.ENDER_PEARL))
            .setAction(event -> {
                System.out.print("Just testing...")

                return true;
            })
            .build()
    );
````

### **Other events**
There are also 2 more methods to execute an action when opening the inventory,
and another to execute an action when closing the inventory, which are:

````java
guiBuilder
    .openEvent(
            event -> {
                Player player = (Player) event.getPlayer();

                player.sendMessage("Opening...!");
                
                return false;
            }
    )
    .closeEvent(
            event -> {
                Player player = (Player) event.getPlayer();

                player.sendMessage("Closing...!");
            }
    );
````

### **Finally**
With this simple way you can create your menus easily and quickly.
Now you will ask yourself, how can I get the inventory I create, it is easy, 
simply with the **build()** method that the MenuBuilder has.

````java
Inventory inventory = guiBuilder.build();
````
