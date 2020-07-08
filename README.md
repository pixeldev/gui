[![Codacy Badge](https://app.codacy.com/project/badge/Grade/45e77e2a28654fa09763d17ad7f98003)](https://www.codacy.com/gh/UnnamedWorks/UnnamedGUI?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=UnnamedWorks/UnnamedGUI&amp;utm_campaign=Badge_Grade)
# UnnamedGUI - MenuManager
A small, and very easy to use, library for creating menus at Spigot API!

## How to use
First we need register one listener for all menus.
Example:
```java
Bukkit.getServer().getPluginManager().registerEvents(new MenuListeners(), plugin);
```
Once we have registered the events, we can start creating our menus.
## ItemBuilder's
This library offers various tools for ease of creation, 
including various types of builders for different types of items,
such as potions, leather armor, fireworks, and soon new types.
So let's start by explaining each type.

##### ItemBuilder:
This is the basis of everything, here you'll have access to many
methods that can be used to facilitate the creation of an item.
Let's look at a simple but complete example:
```java
ItemStack itemStack = new ItemBuilder(Material.DIAMOND_SWORD, 2)
    .addEnchant(Enchantment.DURABILITY, 3)
    .addFlag(ItemFlag.HIDE_ENCHANTS)
    .name("Test")
    .lore(
            new LoreBuilder()
                    .addLine("Simple test %player%")
                    .replace("%player%", player.getName())
    ).build();
```
It's that easy to create an item, and you can do whatever you want with it.
As you can see there's a LoreBuilder object.
######LoreBuider:
This class is simply to facilitate the creation of a lore,
since it provides methods like `LoreBuilder#replace(replace, replacement);`
So you don't have to iterate, there's even the `LoreBuilder#colorize();` 
method, what it does is simply color your lore. This class is easy to use, such as this:
```java
List<String> loreBuilder = new LoreBuilder() //Here you can put any List<String>
    .addLines("&7This is a simple test for item menu!", "", "&bEnjoy %player%!")
    .replace("%player%", Player#getName())
    .colorize()
    .build();
```
Easy...
#####LeatherArmorBuilder:
This type will simply make creating colored leather armor easier to create,
there's an enum with some ready-made colors so you don't have to complicate using.
An example of how to use this builder is the following:
```java
ItemStack chestPlate = new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE)
    .rgbColor(LeatherColor.CYAN)
    .name("&bArmor test")
    .lore(
            new LoreBuilder()
                    .addLine("&7Simple test for leather armor!")
                    .addLine("")
                    .addLine("&bEnjoy!")
                    .colorize()
    )
    .addEnchant(Enchantment.DURABILITY, 3)
    .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
    .addFlag(ItemFlag.HIDE_ATTRIBUTES)
    .buildArmor();
```
**Remember that the material if or if it must be a leather armor**

As you can see there are the same methods as the normal ItemBuilder, 
and you can use it. The only thing that changes is to build the item 
you must put the final method `LeatherArmorBuilder#buildArmor();`

Also as I mentioned before there is the LeatherColor enum.

Although there's also a method like this `LeatherArmorBuilder#rgbColor(red, green, blue);`.
These values can be obtained at: https://www.rapidtables.com/web/color/RGB_Color.html

######LeatherColor:
Types:
    
    - CYAN
    - DARK_BLUE
    - LIGHT_BLUE
    - LIGHT_RED
    - DARK_RED
    - YELLOW
    - ORANGE
    - LIME
    - GREEN
    - PURPLE
    - PINK
    - BLACK
    - WHITE
    - DARK_GRAY
    - LIGHT_GRAY
    
#####PotionBuilder:
There's also the Builder for potions, it's very easy to use, 
but remember that the material must be some kind of potion.
Example:
```java
ItemStack potion = new PotionBuilder(Material.POTION)
    .addPotionAttribute(
            new PotionAttributes(PotionEffectType.POISON)
                    .duration(1000)
                    .amplifier(2)
    )
    .name("&bPotion test")
    .lore(
            new LoreBuilder()
                    .addLine("Simple test for potion builder!")
                    .addLine("")
                    .addLine("&bEnjoy!")
                    .colorize()
    )
    .buildPotion();
```

As you can see it's similar to the rest, but in this case
the method to create the item will be `PotionBuilder#buildPotion();`

#####FireworkBuilder:
Finally, there's a Builder for fireworks, which is very easy to use,
an example would be the following:
```java
ItemStack firework = new FireworkBuilder(Material.FIREWORK)
    .addAttribute(
            new FireworkAttributes()
                    .color(Color.BLUE)
                    .flicker(true)
                    .trail(true)
                    .colorFade(Color.LIME)
                    .type(FireworkEffect.Type.STAR)
    )
    .power(4)
    .buildFirework();
```
As you can see it is similar to the rest, but in this case
the method to create the item will be `FireworkBuilder#buildFirework();`