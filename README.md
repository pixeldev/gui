# **UnnamedGUI - Menu Creator**

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d288974ef2734b50bd34a800b2161c70)](https://app.codacy.com/gh/UnnamedWorks/UnnamedGUI?utm_source=github.com&utm_medium=referral&utm_content=UnnamedWorks/UnnamedGUI&utm_campaign=Badge_Grade_Dashboard)

_A small, and very easy to use, library for creating menus at Spigot API!_

## **Maven Dependency**
You only need to copy and paste this at your pom.xml, and that will be done!
````xml
<repository>
    <id>unnamed-repo</id>
    <url>https://repo.unnamed.team/repository/unnamed-releases/</url>
</repository>
````
````xml
<dependency>
    <groupId>team.unnamed.gui</groupId>
    <artifactId>gui-core</artifactId>
    <version>1.3.1</version>
</dependency>
````

## **How to use**
First we need register one listener for all menus. Example:

````java
Bukkit.getPluginManager().registerEvents(new MenuListeners(), plugin);
````

## **MenuAPI**
Creating menus with our api is too simple, we are going to explain everything a little,
so that you can get an idea of ​​the new functionalities and systems that were re-made.

````java
MenuBuilder menuBuilder = MenuBuilder.newBuilder("MenuTest", 3);
````
This is the basic structure to create a MenuBuilder, which will be used to place 
all the attributes it can have.

Now let's see how we can add an item to the menu, it's quite simple, let's do it.

````java
menuBuilder
    .addItem(
        new DefaultItemClickable(
            13,
            new ItemStack(Material.ENDER_PEARL),
            click -> {
                System.out.print("Just testing...")

                return true;
            }
        )
    );
````

This is the method to be able to add an item to the menu, you need to pass a **DefaultItemClickable**
which needs 3 parameters in its constructor, the first one is the slot where it'll be put,
the second one will be an _ItemStack_, and the third one is the action it will execute. This is a
simple _FunctionalInterface_, which has a method that receives an **InventoryClickEvent**, so you 
can access methods of that event. It's a method with a boolean return value, that's, 
when you return true the event will be canceled, and vice versa.

### **Other events**
There are also 2 more methods to execute an action when opening the inventory,
and another to execute an action when closing the inventory, which are:

````java
menuBuilder
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
Inventory inventory = menuBuilder.build();
````